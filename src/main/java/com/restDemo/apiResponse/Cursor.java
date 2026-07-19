package com.restDemo.apiResponse;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor


public class Cursor {
    private Integer totalPages;
    private Integer currentPage;
    private Integer totalRecords;
    private Integer perPage;

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalRecords() {
        return this.totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getPerPage() {
        return this.perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
}

