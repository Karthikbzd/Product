package com.product.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Order {

	private int orderId;
	private List<Product> products;
	private String customerName;

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", products=" + products + ", customerName=" + customerName + "]";
	}

}
