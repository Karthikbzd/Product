package com.product.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.product.implementation.ProductImp;
import com.product.model.Product;

@Service("ProductImp")
public class ProductDataImp implements ProductImp {

	private static final Logger log = LogManager.getLogger(ProductDataImp.class);

	static List<Product> products = new ArrayList<>();

	static {
		int i = 1;
		products.add(new Product(i++, "Boost", 95));
		products.add(new Product(i++, "Soap", 46));
		products.add(new Product(i++, "Glass", 10));
		products.add(new Product(i++, "Brush", 20));
		products.add(new Product(i++, "Shampoo", 1.50));
	}

	@Override
	public List<Product> getProduct() {
		return products;
	}

	@Override
	public boolean addProducts(List<Product> productList) {
		log.info(products);
		boolean status = products.addAll(productList);
		log.info(products);
		return status;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean status[] = { false };
		products.stream().forEach(x -> {
			if (x.getProductId() == product.getProductId()) {
				x.setName(product.getName());
				x.setPrice(product.getPrice());
				status[0] = true;
			}
		});
		return status[0];
	}

	@Override
	public boolean deleteProduct(int productId) {
		return products.removeIf(x -> x.getProductId() == productId);
	}

}
