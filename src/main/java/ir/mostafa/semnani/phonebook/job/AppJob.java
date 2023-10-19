package ir.mostafa.semnani.phonebook.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppJob {
    @Scheduled(cron = "0 * * * * *") // runs every minute
    private void doDummyJob() {
        log.info("dummy job is running");
    }

}
