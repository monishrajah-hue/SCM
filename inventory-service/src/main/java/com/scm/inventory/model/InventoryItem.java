package com.scm.inventory.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
public class InventoryItem {
    @Id
    private String id;
    private String productId;
    private int stockQuantity;
    
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getId() {
		return id;
	}
	public String getProductId() {
		return productId;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	
}