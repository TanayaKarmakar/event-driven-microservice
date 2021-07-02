package com.app.t2k.elastic.query.client.exception;

/**
 * @author t0k02w6 on 02/07/21
 * @project event-driven-microservice
 */
public class ElasticQueryClientException extends RuntimeException{
    public ElasticQueryClientException() {
        super();
    }

    public ElasticQueryClientException(String message) {
        super(message);
    }

    public ElasticQueryClientException(String message, Throwable t) {
        super(message, t);
    }
}
