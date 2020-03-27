package com.cmb.jiantao.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KfkProducer {
    public void sendMsg(ProducerRecord record) {
        KafkaProducer producer = KafkaConnectUtil.getKafkaProducer();
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println("Success--->" + recordMetadata.offset());
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}
