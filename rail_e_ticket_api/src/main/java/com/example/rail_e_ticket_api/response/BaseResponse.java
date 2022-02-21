package com.example.rail_e_ticket_api.response;

import com.example.rail_e_ticket_api.constants.ResponseConstants;

public abstract class BaseResponse {
    protected static final ApiResponse SUCCESS =
            new ApiResponse(ResponseConstants.SUCCESS, 0);
    protected static final ApiResponse NOT_FOUND =
            new ApiResponse(ResponseConstants.NOT_FOUND, -100);
    protected static ApiResponse LIST =
            new ApiResponse(ResponseConstants.LIST, 1);
}
