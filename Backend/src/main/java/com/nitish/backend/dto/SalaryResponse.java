package com.nitish.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public record SalaryResponse(
        @JsonProperty("paymentDate")
        @JsonFormat(pattern="dd-MM-yyyy")
        LocalDateTime paymentDate,
        @JsonProperty("salary")
        int salary,
       @JsonProperty("description")
        String description
) {
}
