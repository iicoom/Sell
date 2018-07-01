package com.icoom.sell.repository;

import com.icoom.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}

// 在ProductCategoryRepository右击 Go To test
// create new test
