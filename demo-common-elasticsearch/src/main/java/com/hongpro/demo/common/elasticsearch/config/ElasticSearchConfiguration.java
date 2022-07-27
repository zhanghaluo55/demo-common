package com.hongpro.demo.common.elasticsearch.config;

import javax.annotation.Resource;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;

/**
 * @description: 自动配置注入restHighLevelClient
 * @author: tracy
 * @createTime: 2022/7/22
 */
@Configuration
@ComponentScan("com.hongpro.demo.common.elasticsearch")
public class ElasticSearchConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_ES_HOST = "127.0.0.1:9200";

    @Resource
    private ElasticsearchProperties elasticsearchProperties;

    /**
     * 这个close是调用RestHighLevelClient中的close
     */
    @Bean(destroyMethod = "close")
    @Scope("singleton")
    public RestClient createInstance() {
        RestClient client = null;
        String host = elasticsearchProperties.getHost();
        String username = elasticsearchProperties.getUsername();
        String password = elasticsearchProperties.getPassword();
        Integer maxConnectTotal = elasticsearchProperties.getMaxConnectTotal();
        Integer maxConnectPerRoute = elasticsearchProperties.getMaxConnectPerRoute();
        Integer connectionRequestTimeoutMillis = elasticsearchProperties.getConnectionRequestTimeoutMillis();
        Integer socketTimeoutMillis = elasticsearchProperties.getSocketTimeoutMillis();
        Integer connectTimeoutMillis = elasticsearchProperties.getConnectTimeoutMillis();
        Long strategy = elasticsearchProperties.getKeepAliveStrategy();
        try {
            if (StringUtils.isEmpty(host)) {
                host = DEFAULT_ES_HOST;
            }
            String[] hosts = host.split(",");
            HttpHost[] httpHosts = new HttpHost[hosts.length];
            for (int i = 0; i < httpHosts.length; i++) {
                String h = hosts[i];
                httpHosts[i] = new HttpHost(h.split(":")[0], Integer.parseInt(h.split(":")[1]), "http");
            }

            RestClientBuilder builder = RestClient.builder(httpHosts);
            builder.setRequestConfigCallback(requestConfigBuilder -> {
                requestConfigBuilder.setConnectTimeout(connectTimeoutMillis);
                requestConfigBuilder.setSocketTimeout(socketTimeoutMillis);
                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeoutMillis);
                return requestConfigBuilder;
            });

            if (!StringUtils.isEmpty(username)) {
                final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(username, password));

                builder.setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.disableAuthCaching();
                    httpClientBuilder.setMaxConnTotal(maxConnectTotal);
                    httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    if (strategy > 0) {
                        httpClientBuilder.setKeepAliveStrategy((httpResponse, httpContext) -> strategy);
                    }
                    return httpClientBuilder;
                });
            } else {
                builder.setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.disableAuthCaching();
                    httpClientBuilder.setMaxConnTotal(maxConnectTotal);
                    httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                    if (strategy > 0) {
                        httpClientBuilder.setKeepAliveStrategy((httpResponse, httpContext) -> strategy);
                    }
                    return httpClientBuilder;
                });
            }
            client = builder.build();
        } catch (Exception e) {
            logger.error("create RestHighLevelClient error", e);
            return null;
        }
        return client;
    }
}
