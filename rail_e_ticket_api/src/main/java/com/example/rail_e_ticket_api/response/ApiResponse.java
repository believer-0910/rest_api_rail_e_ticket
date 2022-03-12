package com.example.rail_e_ticket_api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"message", "status_code", "data"})
public class ApiResponse {
    private String message;
    @JsonProperty("status_code")
    private int statusCode;
    private Object data;
    private boolean success;

    public ApiResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ApiResponse(String message, int statusCode, Object data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public ApiResponse(String message, int statusCode, boolean success) {
        this.message = message;
        this.statusCode = statusCode;
        this.success = success;
    }
}
