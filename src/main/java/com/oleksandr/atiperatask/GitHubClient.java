package com.oleksandr.atiperatask;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GitHubClient {

    private final RestClient githubRestClient;

    public List<GithubResponse> getUserRepos(String username) {
        return githubRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/{username}/repos")
                        .build(username))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}


/*
https://api.github.com/users/USERNAME/repos
https://api.github.com/repos/USERNAME/REPONAME/branches
 */