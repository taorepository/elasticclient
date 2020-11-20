//package com.example.elastic.config;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author: wtt
// * @create: 2020/11/20
// */
//@Configuration
//public class RestClientConfig {
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("192.168.133.11", 9200, "http")
//                        ));
//
//        return client;
//    }
//}
