package RESTAPI.demo.repository;

import RESTAPI.demo.models.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Component
public class EmployeeRepository implements employeeinterface {
    @Autowired
            private ObjectMapper objectMapper;
    RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

    @Override
    public List<Employee> findallemployeefromelasticsearch() {
        SearchRequest searchRequest=new SearchRequest();
        searchRequest.indices("posts1");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        List<Employee> employeelist=new ArrayList<>();
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse.getHits().getTotalHits().value>0){
                SearchHit [] searchHits=searchResponse.getHits().getHits();
                for(SearchHit hits: searchHits){
                    Map<String,Object> map=hits.getSourceAsMap();
                    employeelist.add(objectMapper.convertValue(map,Employee.class));
                }
            }
        }
        catch (Exception e){
           e.printStackTrace();
        }
        return employeelist;
    }
    //    @Query("{'name': ?0}")
//    List<Optional<Employee>> findByName(String name);
}
