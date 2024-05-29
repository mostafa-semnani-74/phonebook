package ir.mostafa.semnani.phonebook.config.interceptor;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import ir.mostafa.semnani.phonebook.config.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppRateLimitInterceptor implements HandlerInterceptor {

    private final BucketService bucketService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        try {
            String apiKey = request.getHeader("X-api-key");
            if (apiKey == null || apiKey.isEmpty()) {
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Header: X-api-key");
                return false;
            }

            Bucket tokenBucket = bucketService.resolveBucket(apiKey);
            ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);
            if (probe.isConsumed()) {
                response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
                return true;
            } else {
                long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
                response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill));
                response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(),
                        "You have exhausted your API Request Quota");
                return false;
            }
        } catch (Exception e) {
            log.error("error in AppRateLimitInterceptor : ", e);
            return false;
        }
    }

}
