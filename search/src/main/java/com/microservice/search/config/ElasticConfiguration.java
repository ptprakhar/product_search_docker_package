	package com.microservice.search.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * class handles configuration related to elastic 
 * @author prakhar
 *
 */

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.microservice.search.repository")
public class ElasticConfiguration  {
	
	@Value("${elastic.host}")
	private String elasticHost;
	
	@Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()  
            .connectedTo(elasticHost)
            .build();

        return RestClients.create(clientConfiguration).rest();                         
    }
	
	@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
	
	
}
