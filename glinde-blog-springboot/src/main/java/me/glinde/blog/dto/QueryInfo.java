package me.glinde.blog.dto;

import lombok.Data;

@Data
public class QueryInfo {

    private String query = "";
    private int pageCurrent = 1;
    private int pageSize = 6;

}
