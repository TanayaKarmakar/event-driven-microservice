package com.app.t2k.elastic.index.client.service;

import com.app.t2k.elastic.model.index.IndexModel;

import java.util.List;

/**
 * @author t0k02w6 on 26/06/21
 * @project event-driven-microservice
 */
public interface ElasticIndexClient<T extends IndexModel> {
    List<String> save(List<T> documents);
}
