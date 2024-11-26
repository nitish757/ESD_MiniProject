package com.nitish.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record EmployeeRequest(
        @NotNull(message = "Employee should be present")
        @NotEmpty(message = "Employee should be present")
        @NotBlank(message = "Employee should be present")
//        @JsonProperty("firstName")
        String firstName,

//        @JsonProperty("lastName")
        String lastName,

        @NotNull(message = "Employee email is required")
        @Email(message = "Email must be in correct format")
//        @JsonProperty("email")
        String email,

        @NotNull(message = "Password should be present")
        @NotEmpty(message = "Password should be present")
        @NotBlank(message = "Password should be present")
        @Size(min = 6, max = 12)
//        @JsonProperty("password")
        String password,

//        @JsonProperty("title")
        String title,

//        @JsonProperty("photograph_path")
        String photographPath,

//        @JsonProperty("department")
        int department
) {

}
