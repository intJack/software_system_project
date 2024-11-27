package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
* carousel表格存储轮播图的url地址
* 可以返回 指定前n张轮播图
* */
public interface CarouselMapper {
    List<Carousel> SelectTopNCarousels(@Param("TOPN") int n);
}
