package fr.lernejo.search.api;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
class ElasticController {
    private final RestHighLevelClient client;
    ElasticController(RestHighLevelClient client) {
        this.client = client;
    }
    @GetMapping("/api/games")
    public List<Object> getGames(@RequestParam(name = "query") String query) throws IOException {
        SearchRequest request = new SearchRequest().source(SearchSourceBuilder.searchSource().query(new QueryStringQueryBuilder(query)));
        SearchResponse response =  client.search(request, RequestOptions.DEFAULT);
        List<Object> result = new ArrayList();
        for (SearchHit hit : response.getHits()){
            result.add(hit.getSourceAsMap());
        }
        return result;
    }

}
