package com.nitish.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeResponse(
//        @JsonProperty("firstName")
        String firstName,
//        @JsonProperty("lastName")
        String lastName,
//        @JsonProperty("email")
        String email,
//        @JsonProperty("title")
        String title

) {
}
