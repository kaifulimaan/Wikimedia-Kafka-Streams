package com.kaifscode.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaChangeHandler implements EventHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(WikimediaChangeHandler.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangeHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    private String topic;

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("event-data -> %s",messageEvent.getData()));
        kafkaTemplate.send(topic, messageEvent.getData());


    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
