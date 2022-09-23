package com.yash.pms.main;

import java.util.Arrays;
import java.util.Scanner;

import com.yash.pms.exception.ProductAlreadyExistException;
import com.yash.pms.exception.ProductNotFoundException;
import com.yash.pms.model.Product;
import com.yash.pms.service.ProductService;

public class Main {
	public static void main(String[] args) {
		ProductService service =  new ProductService();
		Scanner sc = new Scanner(System.in);
		boolean flage = true;
		do {
			System.out.println("Press 1 to add prodcut");
			System.out.println("Press 2 to delete prodcut");
			System.out.println("Press 3 to update prodcut");
			System.out.println("Press 4 to read prodcut");
			System.out.println("Press 5 to exit");
			System.out.println("Enter the Number");
			int option = sc.nextInt();
			switch (option) {
			case 1: {
				System.out.println("Enter the id");
				int id = sc.nextInt();
				System.out.println("Enter the pName");
				String pName = sc.next();
				System.out.println("Enter the price");
				double price = sc.nextDouble();
	            try {
					service.addProduct(new Product(id, pName, price));
				} catch (ProductAlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flage = false;
					break;
				}
				break;
			}
			case 2: {
				System.out.println("Enter the id");
				int id = sc.nextInt();
				try {
					service.deleteProduct(id);
				} catch (ProductNotFoundException e) {
					e.printStackTrace();
					flage = false;
					break;
				}
				System.out.println("deleted successfully");
				break;
			}
			case 3: {
				System.out.println("Enter the id of that product which you want to update");
				int id = sc.nextInt();
                System.out.println("Press a to update product name");
                System.out.println("Press b to update product price");
                String op = sc.next();
                switch (op) {
				case "a": {
				   System.out.println("Enter the new product name");
				   String pName = sc.next();
				   Product p = service.getProductById(id);
				   p.setpName(pName);
				   service.updateProduct(id, p);
				   break;
				}
				case "b": {
					   System.out.println("Enter the new product price");
					   double price = sc.nextDouble();
					   Product p = service.getProductById(id);
					   p.setPrice(price);
					   service.updateProduct(id, p);
				   break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + op);
				}
				break;
			}
			case 4: {
				System.out.println(Arrays.toString(service.getAllProduct()));
				break;
			}
			case 5: {
				flage = false;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
		}while(flage);
	}
}
