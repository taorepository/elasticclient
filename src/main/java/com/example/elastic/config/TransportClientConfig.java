//package com.example.elastic.config;
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
///**
// * @author: wtt
// * @create: 2020/11/20
// */
//@Configuration
//public class TransportClientConfig extends ElasticsearchConfigurationSupport {
//    @Bean
//    public Client elasticsearchClient() throws UnknownHostException {
//        Settings settings = Settings.builder().put("cluster.name", "tao-cluster").build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.133.11"), 9300));
//        return client;
//    }
//
//    @Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
//    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
//        return new ElasticsearchTemplate(elasticsearchClient());
//    }
//
//
//}
