package com.tlf.framework.adapters.out.integration;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlf.application.ports.out.LoadDeploymentOutPort;
import com.tlf.domain.Deployment;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GetDeploymentByApplicationRestAdapter implements LoadDeploymentOutPort {


    List<Deployment> results;
    private final DeploymentMapper deploymentMapper;

    public List<Deployment> loadDeployment(String application, String minDate, String maxDate){

        try {
            Client client = new PreBuiltTransportClient(
                    Settings.builder().put("client.transport.sniff", true)
                            .put("cluster.name", "docker-cluster").build())
                                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.43.102"), 9300));

            // create the search request
            SearchRequest searchRequest = new SearchRequest("deployments");
            searchRequest.types("application");


            SearchResponse response = client.prepareSearch("deployments")
                    .setTypes("application")
                    .setQuery(QueryBuilders.boolQuery()
                            .filter(QueryBuilders.matchQuery ("application", application).operator(Operator.AND))
                            .filter(QueryBuilders.rangeQuery("date").gte(minDate).lte(maxDate)))
                    .execute()
                    .actionGet();
            List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
                        results = new ArrayList<>();

            searchHits.forEach(
                    hit ->  {
                        System.out.println(hit.getSourceAsString());

                        String source = hit.getSourceAsString();
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            DeploymentJSONEntity deploymentJSONEntity = objectMapper.readValue(source, DeploymentJSONEntity.class);
                            results.add(deploymentMapper.mapToDomainEntity(deploymentJSONEntity));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                    });

        } catch (Exception e){
            System.out.println("test");
        }
        return results;

    }

}
