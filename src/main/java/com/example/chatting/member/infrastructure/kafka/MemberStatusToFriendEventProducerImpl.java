package com.example.chatting.member.infrastructure.kafka;

import com.example.chatting.config.kafka.KafkaConfig;
import com.example.chatting.member.domain.event.MemberStatusToFriendEvent;
import com.example.chatting.member.domain.event.MemberStatusToFriendEventProducer;
import com.example.chatting.member.domain.event.enums.MemberStatusConst;
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
public class MemberStatusToFriendEventProducerImpl implements MemberStatusToFriendEventProducer {

    private final JsonConverter jsonConverter;
    private KafkaProducer<String, String> producer;

    @Autowired
    private KafkaConfig kafkaConfig;

    @PostConstruct
    public void init() {
        this.producer = new KafkaProducer<>(kafkaConfig.kafkaProducerConfig());
    }

    public void sendMemberStatusToFriendEvent(MemberStatusToFriendEvent event) {
        String eventJson = jsonConverter.convertToJson(event);
        ProducerRecord<String, String> record = new ProducerRecord<>(MemberStatusConst.MEMBER_STATUS_TO_FRIEND_TOPIC, eventJson);
        producer.send(record);
    }

    @PreDestroy
    public void close() {
        producer.flush();
        producer.close();
    }
}
