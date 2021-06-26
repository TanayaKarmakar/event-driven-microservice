package com.app.t2k.elastic.index.client.respository;

import com.app.t2k.elastic.model.index.impl.TwitterIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author t0k02w6 on 26/06/21
 * @project event-driven-microservice
 */
@Repository
public interface TwitterElasticsearchIndexRepository extends ElasticsearchRepository<TwitterIndexModel, String> {
}
