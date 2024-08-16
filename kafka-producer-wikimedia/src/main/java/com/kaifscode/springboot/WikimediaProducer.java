package com.kaifscode.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service

public class WikimediaProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(WikimediaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia-recentchange";
        EventHandler eventHandler = new WikimediaChangeHandler(kafkaTemplate, topic);
        String URL = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(URL));
        EventSource eventSource = builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }



    }



