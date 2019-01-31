package cn.lsu.chicken.room.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {
    Integer page;
    Integer size;
    Long total;
    Integer totalPage;
    List<T> data;
}
