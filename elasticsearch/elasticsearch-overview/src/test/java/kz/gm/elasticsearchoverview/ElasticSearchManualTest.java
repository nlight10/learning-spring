package kz.gm.elasticsearchoverview;

import com.alibaba.fastjson.JSON;
import kz.gm.elasticsearchoverview.models.Person;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Daddy: GM
 * BirthDate: 15.07.2021
 * https://www.baeldung.com/elasticsearch-java
 * testing on Docker: docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
 */
public class ElasticSearchManualTest {

    private RestHighLevelClient client = null;

    @BeforeEach
    public void setUp() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        client = RestClients.create(clientConfiguration).rest();
    }

    @Test
    public void givenJsonString_whenJavaObject_thenIndexDocument() throws IOException {
        String jsonObject = "{\"age\":20,\"dateOfBirth\":1471466076564,\"fullName\":\"John Doe\"}";
        IndexRequest request = new IndexRequest("people");
        request.source(jsonObject, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        String index = response.getIndex();
        long version = response.getVersion();

        System.out.println("response.getResult():      " + response.getResult());
        System.out.println("response.getPrimaryTerm(): " + response.getPrimaryTerm());
        System.out.println("response.getId():          " + response.getId());
        System.out.println("response.getSeqNo():       " + response.getSeqNo());
        System.out.println("response.getShardId():     " + response.getShardId());
        System.out.println("response.getShardInfo():   " + response.getShardInfo());

        assertEquals(DocWriteResponse.Result.CREATED, response.getResult());
        assertEquals(1, version);
        assertEquals("people", index);
    }

    @Test
    public void givenContentBuilder_whenHelpers_thanIndexJson() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("fullName", "Test")
                .field("salary", "11500")
                .field("age", "10")
                .endObject();

        IndexRequest indexRequest = new IndexRequest("people");
        indexRequest.source(builder);

        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

        assertEquals(DocWriteResponse.Result.CREATED, response.getResult());
    }

    @Test
    public void givenSearchRequest_whenMatchAll_thenReturnAllResults() throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        // The results returned by the search() method are called Hits, each Hit refers to a JSON document matching a search request.
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits()
                .getHits();
        List<Person> results = Arrays.stream(searchHits)
                .map(hit -> JSON.parseObject(hit.getSourceAsString(), Person.class))
                .collect(Collectors.toList());

        results.forEach(System.out::println);
    }

    @Test
    public void givenSearchParameters_thenReturnResults() throws Exception {
        SearchSourceBuilder builder = new SearchSourceBuilder().postFilter(QueryBuilders.rangeQuery("age")
                .from(5)
                .to(15));

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        builder = new SearchSourceBuilder().postFilter(QueryBuilders.simpleQueryStringQuery("+John -Doe OR Janette"));

        searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);

        SearchResponse response2 = client.search(searchRequest, RequestOptions.DEFAULT);

        builder = new SearchSourceBuilder().postFilter(QueryBuilders.matchQuery("John", "Name*"));
        searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);

        SearchResponse response3 = client.search(searchRequest, RequestOptions.DEFAULT);

        response2.getHits();
        response3.getHits();

        final List<Person> results = Stream.of(response.getHits()
                        .getHits(),
                response2.getHits()
                        .getHits(),
                response3.getHits()
                        .getHits())
                .flatMap(Arrays::stream)
                .map(hit -> JSON.parseObject(hit.getSourceAsString(), Person.class))
                .collect(Collectors.toList());

        results.forEach(System.out::println);
    }

    @Test // creating new one and then deleting
    public void givenDocumentId_whenJavaObject_thenDeleteDocument() throws Exception {
        String jsonObject = "{\"age\":10,\"dateOfBirth\":1471466076564,\"fullName\":\"Johan Doe\"}";
        IndexRequest indexRequest = new IndexRequest("people");
        indexRequest.source(jsonObject, XContentType.JSON);

        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        String id = response.getId();
        System.out.println("id: " + id);

        GetRequest getRequest = new GetRequest("people");
        getRequest.id(id);

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("getResponse.getSourceAsString(): " + getResponse.getSourceAsString());

        DeleteRequest deleteRequest = new DeleteRequest("people");
        deleteRequest.id(id);

        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println("==============================================================");
        System.out.println("deleteResponse.getIndex():       " + deleteResponse.getIndex());
        System.out.println("deleteResponse.getVersion():     " + deleteResponse.getVersion());
        System.out.println("deleteResponse.getType():        " + deleteResponse.getType());
        System.out.println("deleteResponse.getResult():      " + deleteResponse.getResult());
        System.out.println("deleteResponse.getPrimaryTerm(): " + deleteResponse.getPrimaryTerm());
        System.out.println("deleteResponse.getId():          " + deleteResponse.getId());
        System.out.println("deleteResponse.getSeqNo():       " + deleteResponse.getSeqNo());
        System.out.println("deleteResponse.getShardId():     " + deleteResponse.getShardId());
        System.out.println("deleteResponse.getShardInfo():   " + deleteResponse.getShardInfo());

        assertEquals(DocWriteResponse.Result.DELETED, deleteResponse.getResult());
    }

}
