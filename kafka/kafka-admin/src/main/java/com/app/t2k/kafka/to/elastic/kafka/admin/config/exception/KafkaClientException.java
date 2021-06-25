package com.app.t2k.kafka.to.elastic.kafka.admin.config.exception;

/**
 * @author t0k02w6 on 21/06/21
 * @project event-driven-microservice
 */
public class KafkaClientException extends RuntimeException{
    public KafkaClientException() {

    }

    public KafkaClientException(String message) {
        super(message);
    }

    public KafkaClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
