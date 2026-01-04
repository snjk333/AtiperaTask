package com.oleksandr.atiperatask;

import java.util.List;

public record RepositoryResponse(
        String repositoryName,
        String ownerLogin,
        List<Branch> branchList
) {
}
