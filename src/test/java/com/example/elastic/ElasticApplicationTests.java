package com.example.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.security.RefreshPolicy;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    @Test
    void testCreateIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.117.9", 9200, "http"),new HttpHost("192.168.117.10", 9200, "http"),new HttpHost("192.168.117.11", 9200, "http")));
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("xc_course");
        createIndexRequest.settings(Settings.builder().put("number_of_shards", "1").put("number_of_replicas", "0"));
        createIndexRequest.mapping("{\n" +
                "  \"properties\": {\n" +
                "    \"name\": {\n" +
                "      \"type\": \"text\",\n" +
                "      \"analyzer\": \"ik_max_word\",\n" +
                "      \"search_analyzer\": \"ik_smart\"\n" +
                "    },\n" +
                "    \"description\": {\n" +
                "      \"type\": \"text\",\n" +
                "      \"analyzer\": \"ik_max_word\",\n" +
                "      \"search_analyzer\": \"ik_smart\"\n" +
                "    },\n" +
                "    \"studymodel\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"price\": {\n" +
                "      \"type\": \"float\"\n" +
                "    },\n" +
                "    \"timestamp\": {\n" +
                "      \"type\": \"date\",\n" +
                "      \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "    }\n" +
                "  }\n" +
                "}", XContentType.JSON);
        IndicesClient indices = client.indices();
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    @Test
    public void testIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.117.9", 9200, "http"),new HttpHost("192.168.117.10", 9200, "http"),new HttpHost("192.168.117.11", 9200, "http")));
        //文档内容
        //准备json数据
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "spring cloud实战");
        jsonMap.put("description", "本课程主要从四个章节进行讲解： 1.微服务架构入门 2.spring cloud 基础入门 3.实战Spring Boot 4.注册中心eureka。");
        jsonMap.put("studymodel", "201001");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonMap.put("timestamp", dateFormat.format(new Date()));
        jsonMap.put("price", 5.6f);
        IndexRequest indexRequest = new IndexRequest("xc_course").id("1").source(jsonMap);
        client.index(indexRequest, RequestOptions.DEFAULT);
        client.close();

    }

    @Test
    public void testGetDoc() throws IOException {
        //查询请求对象
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.117.9", 9200, "http"),new HttpHost("192.168.117.10", 9200, "http"),new HttpHost("192.168.117.11", 9200, "http")));
        GetRequest getRequest = new GetRequest(
                "xc_course",
                "1");

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        //得到文档的内容
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(sourceAsMap);
    }
}
