import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;

public class sample {
    public static void main(String []args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//    CreateIndexRequest request = new CreateIndexRequest("sampleindex1");
//    request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 2));
//    CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//    System.out.println("response id 1: " + createIndexResponse.index());
        IndexRequest request1 = new IndexRequest("posts1");
        request1.id("1");
        String jsonString = "{" +
                "\"EmployeeName\":\"Sneh\"," +
                "\"EmployeeDesignation\":\"2013-01-30\"," +
                "\"EmployeeTeam\":\"trying out Elasticsearch\"," +
                "\"Experience\":\"2013-01-30\"" +
                "}";
        request1.source(jsonString, XContentType.JSON);
        IndexResponse indexResponse = client.index(request1, RequestOptions.DEFAULT);
        System.out.println("response id 2: "+indexResponse.getId());
        System.out.println("response name 2: "+indexResponse.getResult().name());

    }
}
