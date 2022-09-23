package com.yash.pms.dao;

import com.yash.pms.exception.ProductAlreadyExistException;
import com.yash.pms.exception.ProductNotFoundException;
import com.yash.pms.model.Product;

public interface IProductDao {
   public void addProduct(Product p) throws ProductAlreadyExistException;
   public void deleteProduct(int id) throws ProductNotFoundException;
   public Product[] getAllProduct();
   public Product updateProduct(int id, Product p);
   public Product getProductById(int id);
}
