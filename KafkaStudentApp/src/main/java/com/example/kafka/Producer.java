package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.util.Properties;
import java.util.concurrent.Future;

public class Producer {
    public static void main(String[] args) {
        String bootstrapServers = "localhost:9092";  // Change if needed
        String topic = "test-topic";

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            for (int i = 1; i <= 10; i++) {
                String key = "Key" + i;
                String value = "Message " + i;
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
                Future<RecordMetadata> future = producer.send(record);
                RecordMetadata metadata = future.get();  // Wait for ack
                System.out.printf("Sent record(key=%s value=%s) " + 
                                  "meta(partition=%d, offset=%d)\n",
                                  key, value, metadata.partition(), metadata.offset());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
