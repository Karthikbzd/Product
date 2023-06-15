package com.product.implementation;

import java.util.List;

import com.product.model.Product;

public interface ProductImp {

	public List<Product> getProduct();

	public boolean addProducts(List<Product> productList);

	public boolean updateProduct(Product product);

	public boolean deleteProduct(int productId);

}
