package com.oleksandr.atiperatask;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final GitHubClient client;

    public List<RepositoryResponse> getUserRepos(String username) {
        var githubRepsonsesList = client.getUserRepos(username);
        githubRepsonsesList = githubRepsonsesList
                                            .stream()
                                            .filter(elem -> elem.fork() == false)
                                            .toList();
        List<RepositoryResponse> repositoryResponseList = new ArrayList<>();
        for( var el : githubRepsonsesList){

            var branches = client.getBranchInfoByRepo(username, el.name());

            RepositoryResponse response = new RepositoryResponse(
                    el.name(),
                    username,
                    branches
            );
            repositoryResponseList.add(response);
        }
        return repositoryResponseList;
    }
}
