package com.oleksandr.atiperatask;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
