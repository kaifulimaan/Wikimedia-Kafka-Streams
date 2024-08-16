package com.kaifscode.springboot;

import com.kaifscode.springboot.entity.WikimediaData;
import com.kaifscode.springboot.repository.WikimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerDb {
    private WikimediaRepository repository;

    public KafkaConsumerDb(WikimediaRepository repository) {
        this.repository = repository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerDb.class);

    @KafkaListener(topics = "wikimedia-recentchange", groupId = "myGroup")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("Message received -> %s", eventMessage));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        repository.save(wikimediaData);

    }
}

