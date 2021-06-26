package com.app.t2k.elastic.index.client.service.impl;

import com.app.t2k.elastic.index.client.service.ElasticIndexClient;
import com.app.t2k.elastic.index.client.util.ElasticClientUtil;
import com.app.t2k.elastic.model.index.impl.TwitterIndexModel;
import com.app.t2k.kafka.to.elastic.config.ElasticConfigData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author t0k02w6 on 26/06/21
 * @project event-driven-microservice
 */
@Service
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "elastic-config.is-repository", havingValue = "false", matchIfMissing = true)
public class TwitterElasticIndexClient implements ElasticIndexClient<TwitterIndexModel> {
    private final ElasticConfigData elasticConfigData;
    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticClientUtil<TwitterIndexModel> elasticClientUtil;

    @Override
    public List<String> save(List<TwitterIndexModel> documents) {
        List<IndexQuery> indexQueries = elasticClientUtil.getIndexQueries(documents);
        List<IndexedObjectInformation> documentIdObjects = elasticsearchOperations.bulkIndex(
                indexQueries,
                IndexCoordinates.of(elasticConfigData.getIndexName())
        );
        List<String> documentIds = documentIdObjects.stream().map(IndexedObjectInformation::getId).collect(Collectors.toList());
        log.info("Documents indexed successfully with type: {} and ids: {}", TwitterIndexModel.class.getName(), documentIds);
        return documentIds;
    }
}
