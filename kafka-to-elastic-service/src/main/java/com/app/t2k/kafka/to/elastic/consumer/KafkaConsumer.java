package com.app.t2k.kafka.to.elastic.consumer;

import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;
import java.util.List;

/**
 * @author t0k02w6 on 24/06/21
 * @project event-driven-microservice
 */
public interface KafkaConsumer<K extends Serializable, V extends SpecificRecordBase> {
    void receive(List<V> messages, List<Integer> keys, List<Integer> partitions, List<Long> offsets);
}
