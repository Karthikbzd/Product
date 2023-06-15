package com.product.response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.product.ProductConstants;
import com.product.model.Order;

@Component
public class Response {

	public Map<String, Object> setResponse(boolean status, Optional<Integer> id) {
		Map<String, Object> response = new LinkedHashMap<>();
		if (id.isPresent()) {
			response.put(ProductConstants.MSG, "ID Sholud Not be 0.");
		} else {
			response.put(ProductConstants.STATUS, status);
			if (!status)
				response.put(ProductConstants.MSG, ProductConstants.NOT_FOUND);
		}
		return response;
	}

	public Map<String, Object> setResponse(boolean status, List<Order> orders) {
		Map<String, Object> response = new LinkedHashMap<>();
		if (!orders.isEmpty()) {
			response.put(ProductConstants.OBJECT, orders);
			response.put(ProductConstants.STATUS, status);
		} else {
			response.put(ProductConstants.STATUS, status);
			if (!status)
				response.put(ProductConstants.MSG, ProductConstants.NOT_FOUND);
		}
		return response;
	}

}
