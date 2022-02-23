package com.example.rail_e_ticket_api.response.base;

import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.util.interfaces.ResponseConstants;

public abstract class BaseResponse {
    public static final ApiResponse SUCCESS =
            new ApiResponse(ResponseConstants.SUCCESS, 0);
    protected static final ApiResponse NOT_FOUND =
            new ApiResponse(ResponseConstants.NOT_FOUND, -100);
}
