package com.murca.productdatabase.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRrpository extends JpaRepository<Products,Integer> {
 public Long countById(Integer id);
}
