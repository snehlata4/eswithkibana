package RESTAPI.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication
//@EnableSwagger2
public class RestApiDemoApplication {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//    CreateIndexRequest request = new CreateIndexRequest("sampleindex1");
//    request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 2));
//    CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//    System.out.println("response id 1: " + createIndexResponse.index());
		IndexRequest request1 = new IndexRequest("posts1");
		request1.id("1");
		String jsonString = "{" +
				"\"id\":\"1\"," +
				"\"EmployeeName\":\"Sneh\"," +
				"\"EmployeeDesignation\":\"SE1\"," +
				"\"EmployeeTeam\":\"trying out Elasticsearch\"," +
				"\"Experience\":\"1\"" +
				"}";
		request1.source(jsonString, XContentType.JSON);
		IndexResponse indexResponse = client.index(request1, RequestOptions.DEFAULT);
		System.out.println("response id 2: "+indexResponse.getId());
		System.out.println("response name 2: "+indexResponse.getResult().name());
		SpringApplication.run(RestApiDemoApplication.class, args);
	}
}
