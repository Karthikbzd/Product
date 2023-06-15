package com.product.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.implementation.ProductImp;
import com.product.model.Order;
import com.product.model.Product;
import com.product.response.Response;

@RestController
@RequestMapping(value = "/ProdCtl")
public class ProductController {

	private static final Logger log = LogManager.getLogger(ProductController.class);

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${ord_url}")
	String ordURL;

	@Autowired
	ProductImp productImp;

	@Autowired
	Response response;

	Map<String, Object> resMap;

	@GetMapping("/getProducts")
	public ResponseEntity<?> getProducts() {
		List<Product> products = null;
		products = productImp.getProduct();
		log.info(products);
		return ResponseEntity.ok(products);
	}

	@PostMapping(value = "/addProducts")//, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProducts(@RequestBody List<Product> productList) {
		log.info(productList);
		boolean status = false;
		if (null != productList && productList.isEmpty()) {
			resMap = response.setResponse(status, Optional.empty());
			return ResponseEntity.ok(resMap);
		} else {
			status = productImp.addProducts(productList);
			resMap = response.setResponse(status, Optional.of(null));
		}
		log.info(status);
		return ResponseEntity.ok(resMap);
	}

	@PutMapping(value = "/updateProduct")//, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		log.info(product);
		boolean status = false;
		if (product.getProductId() == 0) {
			resMap = response.setResponse(status, Optional.empty());
			return ResponseEntity.ok(resMap);
		} else {
			status = productImp.updateProduct(product);
			resMap = response.setResponse(status, Optional.of(null));
		}

		return ResponseEntity.ok(resMap);
	}

	@DeleteMapping(value = "/deleteProduct/{id}")//, method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") int productId) {
		log.info(""+productId);
		boolean status = false;
		if (productId == 0) {
			resMap = response.setResponse(status, Optional.empty());
			return ResponseEntity.ok(resMap);
		} else {
			status = productImp.deleteProduct(productId);
			resMap = response.setResponse(status, Optional.of(null));
		}
		log.info(status);
		return ResponseEntity.ok(resMap);
	}
	
	@GetMapping("/getOrders")
	public ResponseEntity<?> getOrders() {
		log.info("getOrders");
		resMap = (Map<String, Object>) restTemplate.getForObject(ordURL+"/OrderCtl/getOrders",Object.class);
		return ResponseEntity.ok(resMap);
	}
	
	@PostMapping("/placeOrders")
	public ResponseEntity<?> placeOrders(@RequestBody Order order) {
		resMap = restTemplate.postForObject(ordURL+"/OrderCtl/placeOrders",order, Map.class);
		return ResponseEntity.ok(resMap);
	}

	@GetMapping("/getOrder/{id}")
	public ResponseEntity<?> getOrderByOrderId(@PathVariable(value = "id") int orderId) {
		resMap = restTemplate.getForObject(ordURL+"/OrderCtl/getOrder/"+orderId, Map.class );
		return ResponseEntity.ok(resMap);
	}
}