package com.zyc.lucene.service;

import com.zyc.lucene.Indexdao.ProductLuceneDao;
import com.zyc.lucene.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @Title: ProductLuceneServiceImpl
 * @ProjectName cmfz
 * @Date 2019-01-02--18:54
 */
@Service
@Transactional
public class ProductLuceneServiceImpl implements ProductLuceneService {
    @Resource
    private ProductLuceneDao productLuceneDao;

    @Override
    public void addIndex(Product product) {
        try {
            productLuceneDao.addIndex(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> queryIndex(String keyWord) {
        List<Product> products = productLuceneDao.searchIndex(keyWord);
        for (Product product : products) {
            System.out.println("这是service");
            System.out.println(product);
        }
        return products;
    }
}
