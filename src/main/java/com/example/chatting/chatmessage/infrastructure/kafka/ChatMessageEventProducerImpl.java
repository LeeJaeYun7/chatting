package com.example.chatting.chatmessage.infrastructure.kafka;

import com.example.chatting.chatmessage.domain.event.ChatMessageEvent;
import com.example.chatting.chatmessage.domain.event.ChatMessageEventProducer;
import com.example.chatting.chatmessage.domain.event.enums.ChatMessageConst;
import com.example.chatting.shared.utils.JsonConverter;
import com.example.chatting.websocket.dao.WebSocketIpDAO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@RequiredArgsConstructor
public class ChatMessageEventProducerImpl implements ChatMessageEventProducer {

    private final JsonConverter jsonConverter;
    private final WebSocketIpDAO webSocketIpDAO;
    private KafkaProducer<String, String> producer;
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String TOPIC_NAME = ChatMessageConst.CHAT_MESSAGE_TOPIC;

    @PostConstruct
    public void init() {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());

        this.producer = new KafkaProducer<>(configs);
    }

    public void sendChatMessageEvent(ChatMessageEvent event) {
        String webSocketIp = webSocketIpDAO.getWebSocketIp(event.getSenderId());
        String eventJson = jsonConverter.convertToJson(event);

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, webSocketIp, eventJson);
        producer.send(record);
    }

    @PreDestroy
    public void close() {
        producer.flush();
        producer.close();
    }
}
