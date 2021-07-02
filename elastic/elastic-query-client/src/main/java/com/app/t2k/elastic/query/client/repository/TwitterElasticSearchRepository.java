package com.app.t2k.elastic.query.client.repository;

import com.app.t2k.elastic.model.index.impl.TwitterIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author t0k02w6 on 02/07/21
 * @project event-driven-microservice
 */
@Repository
public interface TwitterElasticSearchRepository extends ElasticsearchRepository<TwitterIndexModel, String> {
    List<TwitterIndexModel> findByText(String text);


}
