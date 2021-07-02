package com.app.t2k.elastic.query.client.service;

import com.app.t2k.elastic.model.index.IndexModel;

import java.util.List;

/**
 * @author t0k02w6 on 02/07/21
 * @project event-driven-microservice
 */
public interface ElasticQueryClient<T extends IndexModel> {
    T getIndexModelById(String id);

    List<T> getIndexModelByText(String text);

    List<T> getAllIndexModels();
}
