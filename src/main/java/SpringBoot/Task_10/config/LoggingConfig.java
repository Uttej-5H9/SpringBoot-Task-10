package SpringBoot.Task_10.config;


import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);

    @PostConstruct
    public void init() {
        logger.info("LoggingConfig initialized — custom logging setup is active.");
    }
}

