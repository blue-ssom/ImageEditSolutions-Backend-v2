package com.example.api_server.global;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomSessionListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("세션 생성됨: {}", se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("세션 만료됨: {}", se.getSession().getId());
    }
}
