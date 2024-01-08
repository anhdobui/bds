package com.example.bds.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedResponseDTO<T> {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalItems;
    private List<T> listResult;

}
