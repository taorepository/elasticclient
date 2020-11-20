package com.example.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.security.RefreshPolicy;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class ElasticApplicationTests {

//    @Autowired
//    private Client client;

    RestHighLevelClient highLevelClient;

//    @Test
//    void contextLoads() throws ExecutionException, InterruptedException {
//        String jsonString = "{" +
//                "\"user\":\"kimchy\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//                "}";
//        IndexRequest request = new IndexRequest("spring-data", "elasticsearch", "1")
//                .source(jsonString, XContentType.JSON)
//                .setRefreshPolicy(RefreshPolicy.IMMEDIATE.getValue());
//
//        ActionFuture<IndexResponse> index = client.index(request);
//        IndexResponse indexResponse = index.get();
//        System.out.println(indexResponse.getResult());
//    }
    @Test
    void test2() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.133.11", 9200, "http")
                ));

        IndexRequest request = new IndexRequest("spring-data", "elasticsearch2", "2")
                .source(singletonMap("feature", "high-level-rest-client"))
                .setRefreshPolicy(RefreshPolicy.IMMEDIATE.getValue());
        client.index(request, RequestOptions.DEFAULT);
    }

    Map<String,String> singletonMap(String a,String b){
        Map<String, String> d = new HashMap<>();
        d.put(a,b);
        return d;
    }


}
