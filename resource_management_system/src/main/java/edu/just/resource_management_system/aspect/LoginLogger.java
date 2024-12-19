package edu.just.resource_management_system.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class LoginLogger {
    private static final Logger logger = LoggerFactory.getLogger(LoginLogger.class);

    @Pointcut("execution(public org.springframework.http.ResponseEntity edu.just.resource_management_system.controller.AdminController.login(..))")
    public void loginPointcut() {}

    @AfterReturning(pointcut = "loginPointcut()", returning = "response")
    public void loginAdvice(Object response) {
        if (response instanceof ResponseEntity) {
            ResponseEntity<?> res = (ResponseEntity<?>) response;
            if (res.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> body = (Map<String, Object>) res.getBody();
                if (body != null && body.containsKey("userName")) {
                    String userName = (String) body.get("userName");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                    String formatDate = simpleDateFormat.format(new Date());
                    logger.info("User '{}' logged in at {}", userName, formatDate);
                }
            }
        }
    }
}
