package org.macross.shopping_mall;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.macross.shopping_mall.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    void TestElasticSearch() throws IOException {
        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("test");
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);

        //判断索引是否存在
//		GetIndexRequest index = new GetIndexRequest("test");
//		boolean exists = client.indices().exists(index, RequestOptions.DEFAULT);
//		System.out.println(exists);
//
//		//删除索引
//		DeleteIndexRequest delete = new DeleteIndexRequest("test");
//		AcknowledgedResponse response = client.indices().delete(delete, RequestOptions.DEFAULT);
//		System.out.println(response.isAcknowledged());
    }

    @Test
    void TestAddToDocument() throws IOException {
        IndexRequest indexRequest = new IndexRequest("test");

        User user = new User();
        user.setName("你好");
        user.setPhone("17820973060");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        indexRequest.id("2");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));

        indexRequest.source(json, XContentType.JSON);

        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index.toString());
        System.out.println(index.status());
    }

    @Test
    void TestIfExist() throws IOException {
        GetRequest getRequest = new GetRequest("test", "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));

        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void TestGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("test", "1");
        GetResponse documentFields = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSource());
        System.out.println(documentFields);
    }

    @Test
    void UpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("test", "2");
        updateRequest.timeout(TimeValue.timeValueSeconds(1));

        User user = new User();
        user.setName("haha");
        user.setPhone("17820973060");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        updateRequest.doc(json, XContentType.JSON);

        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    @Test
    void DeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("test", "2");
        deleteRequest.timeout(TimeValue.timeValueSeconds(1));

        DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    @Test
    void TestBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> list = new ArrayList<>();
        User user = new User();
        user.setName("2132");
        list.add(user);

        User user2 = new User();
        user2.setName("213112");
        list.add(user2);

        ObjectMapper objectMapper = new ObjectMapper();
        for (User data : list) {
            bulkRequest.add(new
                    IndexRequest("test")
                    .source(objectMapper.writeValueAsString(data), XContentType.JSON));
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());
    }

    @Test
    void TestSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.highlighter();
//        searchSourceBuilder.from();
//        searchSourceBuilder.size();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "macross");

        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(10, TimeUnit.SECONDS));

        searchRequest.source(searchSourceBuilder);

        ObjectMapper objectMapper = new ObjectMapper();
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().toString());
        System.out.println("=============");
        for ( SearchHit documentFields :searchResponse.getHits().getHits()){
            System.out.println(documentFields.getSourceAsString());
            System.out.println(documentFields.getIndex());
            System.out.println(documentFields.getId());
        }
    }
}
