package ir.mostafa.semnani.phonebook.config;

import ir.mostafa.semnani.phonebook.config.interceptor.AppRateLimitInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AppWebMvcConfig implements WebMvcConfigurer {
    private final AppRateLimitInterceptor appRateLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appRateLimitInterceptor)
                .addPathPatterns("/api/v1/**");
    }

}
