package com.fcctech.tournament.payload.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PageResponse<T> {
    private Paging paging;
    private List<T> result;

    @Getter
    @Setter
    public static class Paging {
        private int page;
        private int size;
        private Long total;
    }

    public static <T> PageResponse<T> build(int size, int page, long total, List<T> result) {
        PageResponse response = new PageResponse();
        response.paging = new Paging();
        response.paging.page = page;
        response.paging.size = size;
        response.paging.total = total;
        response.result = result;
        return response;
    }
}
