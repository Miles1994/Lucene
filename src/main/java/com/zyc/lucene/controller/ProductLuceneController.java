package com.zyc.lucene.controller;

import com.zyc.lucene.entity.Product;
import com.zyc.lucene.service.ProductLuceneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 * @Title: ProductLuceneController
 * @ProjectName cmfz
 * @Date 2019-01-02--18:55
 */
@RestController
@RequestMapping("/pro")
public class ProductLuceneController {
    @Resource
    private ProductLuceneService ps;

    /**
     * 添加
     */
    @RequestMapping("add")
    @ResponseBody
    public String addIndex(Product product, MultipartFile file) {
        try {
            String id = UUID.randomUUID().toString();
            product.setId(id);
            String fileName = file.getOriginalFilename();
            String filePath = "E:\\MyIdeaProject\\lucene\\src\\main\\webapp\\image";
            String url = filePath + "/" + fileName;
            product.setUrl(fileName);
            ps.addIndex(product);
            File dest = new File(url);
            file.transferTo(dest);
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 查询
     */
    @RequestMapping("search")
    @ResponseBody
    public List<Product> search(String keyWord) {
        List<Product> products = ps.queryIndex(keyWord);
        return products;
    }
}
