package com.nitish.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeDetailsResponse(
        @JsonProperty("firstName")
        String firstName,
        @JsonProperty("lastName")
        String lastName,
        @JsonProperty("email")
        String email,
        @JsonProperty("title")
        String title,
        @JsonProperty("department")
        String department
) {
}
