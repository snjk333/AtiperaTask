package com.oleksandr.atiperatask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {

    private String name;
    private String sha;

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("commit")
    private void unpackCommit(Map<String, Object> commit) {
        this.sha = (String) commit.get("sha");
    }
}