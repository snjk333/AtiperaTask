package com.oleksandr.atiperatask;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/repository")
public class RepositoryController {

    private final RepositoryService service;

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryResponse>> getUserReposByName(@PathVariable String username){
        return ResponseEntity.ok(service.getUserRepos(username));
    }
}
