package com.restDemo.tool;

import com.restDemo.apiResponse.ApiResponse;
import com.restDemo.apiResponse.Cursor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DataBaseResult<T extends Serializable> {

    private List<T> result;
    private Integer totalPages;
    private Integer currentPage;
    private Integer totalRecords;
    private Integer perPage;


    public DataBaseResult(List<T> result){
        this.result = result;
    }

    public DataBaseResult(List<T> result,Integer totalPages,Integer currentPage,Integer totalRecords,Integer perPage){
        this.result=result;
        this.totalPages=totalPages;
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
        this.perPage = perPage;
    }

    public Cursor getCursor(){
        Cursor cursor = new Cursor();
        cursor.setCurrentPage(this.currentPage);
        cursor.setTotalPages(this.totalPages);
        cursor.setTotalRecords(this.totalRecords);
        cursor.setPerPage(this.perPage);
        return cursor;
    }

}
