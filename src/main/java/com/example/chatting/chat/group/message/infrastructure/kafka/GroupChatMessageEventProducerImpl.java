package com.example.chatting.chat.group.message.infrastructure.kafka;

import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEvent;
import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEventProducer;
import com.example.chatting.config.kafka.KafkaConfig;
import com.example.chatting.chat.group.message.infrastructure.kafka.enums.GroupChatMessageConst;
import com.example.chatting.shared.utils.JsonConverter;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupChatMessageEventProducerImpl implements GroupChatMessageEventProducer {

    private final JsonConverter jsonConverter;
    private KafkaProducer<String, String> producer;

    @Autowired
    private KafkaConfig kafkaConfig;

    @PostConstruct
    public void init() {
        this.producer = new KafkaProducer<>(kafkaConfig.kafkaProducerConfig());
    }

    public void sendGroupChatMessageEvent(GroupChatMessageEvent event) {
        String eventJson = jsonConverter.convertToJson(event);
        String senderId = event.getSenderId();



        ProducerRecord<String, String> record = new ProducerRecord<>(GroupChatMessageConst.CHAT_MESSAGE_TOPIC, senderId, eventJson);
        producer.send(record);
    }

    @PreDestroy
    public void close() {
        producer.flush();
        producer.close();
    }
}
