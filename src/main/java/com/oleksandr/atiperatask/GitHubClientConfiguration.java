package com.oleksandr.atiperatask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
public class GitHubClientConfiguration {

    @Value("${github.api.url:https://api.github.com}")
    private String githubApiUrl;

    @Bean
    public RestClient githubRestClient() {
        return RestClient.builder()
                .baseUrl(githubApiUrl)
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .defaultHeader(HttpHeaders.USER_AGENT, "Atipera-APP_Kulbit")
                .build();
    }
}


