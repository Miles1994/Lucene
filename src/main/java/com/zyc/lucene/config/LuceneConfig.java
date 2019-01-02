package com.zyc.lucene.config;

import com.zyc.lucene.Indexdao.ProductLuceneDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @Title: LuceneConfig
 * @ProjectName cmfz
 * @Date 2019-01-02--19:53
 */
@Configuration
public class LuceneConfig {
    @Bean
    public ProductLuceneDao getProductLuceneDao() {
        return new ProductLuceneDao();
    }
}
