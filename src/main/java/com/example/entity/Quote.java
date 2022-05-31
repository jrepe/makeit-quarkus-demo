package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jure.repe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(@JsonProperty("q") String quote, @JsonProperty("a") String author) {}
