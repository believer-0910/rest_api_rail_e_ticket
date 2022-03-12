package com.example.rail_e_ticket_api.service.base;

import com.example.rail_e_ticket_api.response.ApiResponse;

import java.util.UUID;

public interface BaseService <T>{
    ApiResponse add(T t);
    ApiResponse getById(Long id);
    ApiResponse getList();
    ApiResponse updateById(Long id, T t);
    ApiResponse deleteById(Long id);
}
