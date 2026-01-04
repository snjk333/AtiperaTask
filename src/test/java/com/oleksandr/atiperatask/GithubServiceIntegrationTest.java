package com.oleksandr.atiperatask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableWireMock(@ConfigureWireMock(name = "github", baseUrlProperties = "github.api.url"))
class GithubServiceIntegrationTest {

    @Autowired
    GitHubClient client;

    @Test
    void shouldReturnUserRepos() {
        stubFor(get(urlPathMatching("/users/kulbit/repos"))
                .willReturn(okJson("""
                    [
                        {"name": "test-repo", "fork": false}
                    ]
                """)));

        var result = client.getUserRepos("kulbit");

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().name()).isEqualTo("test-repo");
    }

    @Test
    void shouldReturnBranchInfo() {
        stubFor(get(urlPathMatching("/repos/kulbit/test-repo/branches"))
                .willReturn(okJson("""
                    [
                        {"name": "main", "sha": "1111"},
                        {"name": "dev", "sha": "2222"}
                    ]
                """)));

        var branches = client.getBranchInfoByRepo("kulbit", "test-repo");

        assertThat(branches).hasSize(2);
        assertThat(branches.getFirst().getName()).isEqualTo("main");
    }

    @Test
    void shouldThrowExceptionWhenRepoNotFound() {
        stubFor(get(urlPathMatching("/users/unknown-user/repos"))
                .willReturn(aResponse().withStatus(404)));

        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> client.getUserRepos("unknown-user")
        ).isInstanceOf(org.springframework.web.client.HttpClientErrorException.NotFound.class);
    }
}