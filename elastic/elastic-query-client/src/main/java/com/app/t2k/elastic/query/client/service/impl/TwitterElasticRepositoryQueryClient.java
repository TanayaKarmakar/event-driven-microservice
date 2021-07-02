package com.app.t2k.elastic.query.client.service.impl;

import com.app.t2k.common.util.CollectionsUtil;
import com.app.t2k.elastic.model.index.impl.TwitterIndexModel;
import com.app.t2k.elastic.query.client.exception.ElasticQueryClientException;
import com.app.t2k.elastic.query.client.repository.TwitterElasticSearchRepository;
import com.app.t2k.elastic.query.client.service.ElasticQueryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author t0k02w6 on 02/07/21
 * @project event-driven-microservice
 */
@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterElasticRepositoryQueryClient implements ElasticQueryClient<TwitterIndexModel> {
    private final TwitterElasticSearchRepository twitterElasticSearchRepository;


    @Override
    public TwitterIndexModel getIndexModelById(String id) {
        Optional<TwitterIndexModel> searchResult = twitterElasticSearchRepository.findById(id);
        log.info("Document with id {} retrieved successfully",
                searchResult.orElseThrow(() ->
                        new ElasticQueryClientException("No document found at elasticsearch with id " + id)).getId());

        return searchResult.get();
    }

    @Override
    public List<TwitterIndexModel> getIndexModelByText(String text) {
        List<TwitterIndexModel> searchResult = twitterElasticSearchRepository.findByText(text);
        log.info("{} of documents with text {} retrieved successfully ", searchResult.size(), text);
        return searchResult;
    }

    @Override
    public List<TwitterIndexModel> getAllIndexModels() {
        List <TwitterIndexModel> searchResult = CollectionsUtil.getInstance()
                .getListFromIterable(twitterElasticSearchRepository.findAll());
        log.info("{} number of documents retrieved successfully", searchResult.size());
        return searchResult;
    }
}
