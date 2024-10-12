package my.example.service;

import java.util.List;

import my.example.model.Product;

public interface ProductServiceable {
	
	public List<Product> getProducts();
	public List<Product> getProducts(int size);
	public List<Product> getClonedProducts(int size) ;
}
