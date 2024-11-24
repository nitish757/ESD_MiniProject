package com.nitish.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record SalaryResponse(
        @JsonProperty("payment_date")
        Date paymentDate,
        @JsonProperty("salary")
        int salary,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName
) {
}
