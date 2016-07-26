package com.marvin.old.logger;

/**
 *
 * @author Dr.Who
 */
public class Logger {
    
    protected static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Logger.class.getName());

    public Logger() {
    }
    
    public void log(Object obj){
        LOGGER.info(obj != null ? obj.toString() : "null");
    }
    
}
