package com.icoom.sell.repository;

import com.icoom.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findById(1).get();
        System.out.println(productCategory.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

}

// 添加 注解@RunWith  @SpringBootTest

// Inferred type 'S' for type parameter 'S' is not within its bound;
// 1、springboot 版本问题，将 2.0.1 版本换成 1.5.4 版本。
//
// 2、将girlRepository.findOne(id); 改为 girlRepository.findById(id).orElse(null); 或
//
//girlRepository.findById(id).get();

// 在findOneTest 右击运行
