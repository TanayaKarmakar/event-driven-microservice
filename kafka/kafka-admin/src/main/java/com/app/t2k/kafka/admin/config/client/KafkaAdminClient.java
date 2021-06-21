package com.app.t2k.kafka.admin.config.client;

import com.app.t2k.config.KafkaConfigData;
import com.app.t2k.config.RetryConfigData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * @author t0k02w6 on 21/06/21
 * @project event-driven-microservice
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaAdminClient {
    private final KafkaConfigData kafkaConfigData;
    private final RetryConfigData retryConfigData;
    private final AdminClient adminClient;
    private final RetryTemplate retryTemplate;
}
