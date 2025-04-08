package com.example.chatting.chat.oneonone.message.infrastructure.kafka;

import com.example.chatting.chat.oneonone.message.domain.event.ChatMessageEvent;
import com.example.chatting.chat.oneonone.message.domain.event.ChatMessageEventProducer;
import com.example.chatting.chat.oneonone.message.infrastructure.kafka.enums.ChatMessageConst;
import com.example.chatting.config.kafka.KafkaConfig;
import com.example.chatting.shared.utils.JsonConverter;
import com.example.chatting.websocket.dao.WebSocketIpDAO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageEventProducerImpl implements ChatMessageEventProducer {

    private final JsonConverter jsonConverter;
    private final WebSocketIpDAO webSocketIpDAO;
    private KafkaProducer<String, String> producer;

    @Autowired
    private KafkaConfig kafkaConfig;

    @PostConstruct
    public void init() {
        this.producer = new KafkaProducer<>(kafkaConfig.kafkaOneOnOneChatProducerConfig());
    }

    public void sendChatMessageEvent(ChatMessageEvent event) {
        String webSocketIp = webSocketIpDAO.getWebSocketIp(event.getSenderId());
        String eventJson = jsonConverter.convertToJson(event);

        ProducerRecord<String, String> record = new ProducerRecord<>(ChatMessageConst.CHAT_MESSAGE_TOPIC, webSocketIp, eventJson);
        producer.send(record);
    }

    @PreDestroy
    public void close() {
        producer.flush();
        producer.close();
    }
}
