package com.app.t2k.elastic.index.client.util;

import com.app.t2k.elastic.model.index.IndexModel;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author t0k02w6 on 26/06/21
 * @project event-driven-microservice
 */
@Component
public class ElasticClientUtil<T extends IndexModel> {
    public List<IndexQuery> getIndexQueries(List<T> documents) {
        return documents.stream()
                .map(document -> new IndexQueryBuilder()
                .withId(document.getId())
                .withObject(document)
                .build()).collect(Collectors.toList());
    }
}
