package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.order.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {


    /**
     * Creating multiple kafka topic
     *
     *   @Bean
     *   public NewTopic topic1() {
     *     return TopicBuilder.name("thing1")
     *         .partitions(10)
     *         .replicas(3)
     *         .compact()
     *         .build();
     *   }
     *
     *   @Bean
     *   public NewTopic topic2() {
     *     return TopicBuilder.name("thing2")
     *         .partitions(10)
     *         .replicas(3)
     *         .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
     *         .build();
     *   }
     *
     * */

//    @KafkaListener(
//            topics = "${spring.kafka.topic.name}",
//            groupId = "${spring.kafka.consumer.group-id}"
//    )
//    public void consume(OrderEvent orderEvent){
//        System.out.println(orderEvent.getOrder().getName());
//    }

    /**
     * Order : consume
     *
     * @KafkaListener( topics = "${spring.kafka.topic.name}",
     * groupId = "${spring.kafka.consumer.group-id}"
     * )
     * public void consume(OrderEvent orderEvent){
     * System.out.println(orderEvent.getOrder().getOrderId());
     * System.out.println(orderEvent.getOrder().getName());
     * System.out.println(orderEvent.getOrder().getQty());
     * }
     */

}
