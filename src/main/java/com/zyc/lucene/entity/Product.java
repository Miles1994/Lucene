package com.zyc.lucene.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @Title: Product
 * @ProjectName cmfz
 * @Date 2019-01-02--16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private String id;
    private String name;
    private String description;
    private String url;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String address;
}
