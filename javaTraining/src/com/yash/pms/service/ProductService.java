package com.yash.pms.service;

import java.util.Arrays;

import com.yash.pms.dao.IProductDao;
import com.yash.pms.exception.ProductAlreadyExistException;
import com.yash.pms.exception.ProductNotFoundException;
import com.yash.pms.model.Product;

public class ProductService implements IProductDao{
    Product product[] = new Product[0];
	int size;
	int capacity;
    int count = 0;
    
    public ProductService() {
    	product = new Product[2];
        size=0;
        capacity=2;
    }
    
    public void ensureCapacity(int minCapacity){
    	Product p[] = new Product[capacity*minCapacity];
        for (int i=0; i < capacity; i++){
            p[i] = product[i];
        }
        product = p;
        capacity = capacity * minCapacity;
    }
    
	@Override
	public void addProduct(Product p) throws ProductAlreadyExistException {
		for(int i=0;i<size; i++) {
			if(p.getId() == product[i].getId()) {
				throw new ProductAlreadyExistException("This product id is already exist");
			}
		}
		if (size == capacity){
            ensureCapacity(2); 
        }
        product[size] = p;
        size++;
	}

	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		int index = 0;
		if(size == 0) {
			throw new ProductNotFoundException("Product list is empty");
		}
		for(int i=0; i<size; i++) {
			if(product[i].getId()==id) {
				index=i;
			}
		}
		if(size>0) {
			for(int i=index; i<size-1; i++) {
				product[i] = product[i+1];
			}
			product[size-1] = null;
			size--;
		}
	}

	@Override
	public Product[] getAllProduct() {
		Product p[] = new Product[size];
		for(int i=0; i<size; i++) {
           p[i] = product[i];
		}
		return p;
	}

	@Override
	public Product updateProduct(int id, Product p) {
		for(int i=0; i<size; i++) {
			if(product[i].getId() == id) {
				product[i] = p;
			}
		}
		return null;
	}

	@Override
	public Product getProductById(int id) {
		Product p = new Product();
		for(int i=0; i<size; i++) {
			if(product[i].getId() == id) {
				p = product[i];
			}
		}
		return p;
	}

}
