package com.zyc.lucene.service;


import com.zyc.lucene.entity.Product;

import java.util.List;


/**
 * @author Administrator
 * @Title: ProductLuceneService
 * @ProjectName cmfz
 * @Date 2019-01-02--18:52
 */
public interface ProductLuceneService {
    /**
     * 添加
     *
     * @param product
     */
    void addIndex(Product product);

    /**
     * 查询
     */
    List<Product> queryIndex(String keyWord);
}
