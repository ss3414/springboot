package com.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product extends Model<Product> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String category;

    private Float price;

    private String area;

    private String code;

    private Integer score;

}
