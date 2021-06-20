package com.app.t2k.service.exception;

/**
 * @author t0k02w6 on 20/06/21
 * @project event-driven-microservice
 */
public class TwitterToKafkaException extends RuntimeException{
    public TwitterToKafkaException() {
        super();
    }

    public TwitterToKafkaException(String message) {
        super(message);
    }

    public TwitterToKafkaException(String message, Throwable cause) {
        super(message, cause);
    }
}
