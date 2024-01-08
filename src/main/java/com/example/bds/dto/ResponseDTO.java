package com.example.bds.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO<T> {
    private String message;
    private T data;
}
