package org.example.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    protected void logAction(String action) {
        logger.info("Performing action: {}", action);
    }
}
