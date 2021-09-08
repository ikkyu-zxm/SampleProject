package xyz.ikkyu.order;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author by xinming
 * @Classname TestKafka
 * @Description
 * @Date 2021/8/20 11:07
 */
public class TestKafka {


    @Test
    public void sendMessage() throws ExecutionException, InterruptedException {
        String topic = "ikkyu_13";
        Properties p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        //kafka  持久化数据的MQ  数据-> byte[]，不会对数据进行干预，双方要约定编解码
        //kafka是一个app：：使用零拷贝  sendfile 系统调用实现快速数据消费
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.ACKS_CONFIG, "-1");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(p);

        //现在的producer就是一个提供者，面向的其实是broker，虽然在使用的时候我们期望把数据打入topic

        /*
        msb-items
        2partition
        三种商品，每种商品有线性的3个ID
        相同的商品最好去到一个分区里
         */


        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "1" , "Hello 1");
        producer.send(producerRecord).get();
        ProducerRecord<String, String> producerRecord2 = new ProducerRecord<>(topic, "2" , "Hello 2");
        producer.send(producerRecord2).get();
//        while(true){
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j <3; j++) {
//                    ProducerRecord<String, String> record = new ProducerRecord<>(topic, "item"+j,"val" + i);
//                    Future<RecordMetadata> send = producer
//                            .send(record);
//
//                    RecordMetadata rm = send.get();
//                    int partition = rm.partition();
//                    long offset = rm.offset();
//                    System.out.println("key: "+ record.key()+" val: "+record.value()+" partition: "+partition + " offset: "+offset);
//
//                }
//            }
//        }
    }


    Properties getConsumerProperties() {
        //基础配置
        Properties p = new Properties();
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());


        //KAKFA IS MQ  IS STORAGE
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");//第一次启动，米有offset
        /**
         *         "What to do when there is no initial offset in Kafka or if the current offset
         *         does not exist any more on the server
         *         (e.g. because that data has been deleted):
         *         <ul>
         *             <li>earliest: automatically reset the offset to the earliest offset
         *             <li>latest: automatically reset the offset to the latest offset</li>
         *             <li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li><li>anything else: throw exception to the consumer.</li>
         *         </ul>";
         */
        p.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");//自动提交时异步提交，丢数据&&重复数据
        return p;
    }


    @Test
    public void consumerMessage() {

        Properties p = getConsumerProperties();
        //消费的细节
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"OOXX_013");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);
        kafkaConsumer.subscribe(Arrays.asList("ikkyu_13"));
//        while (true) {
//            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
//
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.println("-----------------");
//                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
//                System.out.println();
//            }
//        }
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
        int a = 0;
        while (a < 2) {
           records = kafkaConsumer.poll(Duration.ofMillis(100));

            if (!records.isEmpty()) {
                Set<TopicPartition> partitions = records.partitions();
                for (TopicPartition partition : partitions) {
                    a += records.records(partition).size();
                }
            }
        }
        System.out.println("-------------------------------------------------");

        Iterator<ConsumerRecord<String, String>> iterator = records.iterator();

        ConsumerRecord<String, String> next1 = null;
        while (iterator.hasNext()) {
            next1 = iterator.next();
        }
        System.out.println("-----------------");
        System.out.printf("offset = %d, value = %s", next1.offset(), next1.value());
        System.out.println();

        TopicPartition partition = new TopicPartition("ikkyu_13", next1.partition());
        OffsetAndMetadata offset = new OffsetAndMetadata(next1.offset());
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        offsets.put(partition, offset);
        kafkaConsumer.commitSync(offsets);

        System.out.println("结束");
    }


    @Test
    public void test() {
        Properties p = getConsumerProperties();
        //消费的细节
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"OOXX_013");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);
        kafkaConsumer.subscribe(Arrays.asList("ikkyu_13"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                System.out.println("-----------------");
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println();
            }
        }
    }
}
