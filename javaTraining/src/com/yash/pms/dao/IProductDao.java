package com.yash.pms.dao;
/*
 * data access object interface contain all the method operation which you can perform on data
 */
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
