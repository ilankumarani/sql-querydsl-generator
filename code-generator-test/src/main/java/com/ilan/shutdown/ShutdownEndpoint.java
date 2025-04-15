package com.ilan.shutdown;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ShutdownEndpoint implements ApplicationContextAware {

    private ConfigurableApplicationContext context;

    public ShutdownDescriptor shutdown() {
        if (this.context == null) {
            return ShutdownDescriptor.NO_CONTEXT;
        }
        try {
            return ShutdownDescriptor.DEFAULT;
        }
        finally {
            Thread thread = new Thread(this::performShutdown);
            thread.setContextClassLoader(getClass().getClassLoader());
            thread.start();
        }
    }

    private void performShutdown() {
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        this.context.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (context instanceof ConfigurableApplicationContext) {
            this.context = (ConfigurableApplicationContext) context;
        }
    }

    /**
     * Description of the shutdown.
     */
    public static class ShutdownDescriptor {

        private static final ShutdownDescriptor DEFAULT = new ShutdownDescriptor("Shutting down, bye...");

        private static final ShutdownDescriptor NO_CONTEXT = new ShutdownDescriptor("No context to shutdown.");

        private final String message;

        ShutdownDescriptor(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

    }
}
