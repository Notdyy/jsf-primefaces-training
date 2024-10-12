package my.example.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String code;
	private String name;
	private String description;
	private String image;
	private double price;
	private String category;
	private int quantity;
	private InventoryStatus inventoryStatus;
	private int rating;
	private List<Order> orders;

	// Copy Constructor
	public Product(Product other) {
		this.id = other.id;
		this.code = other.code;
		this.name = other.name;
		this.description = other.description;
		this.image = other.image;
		this.price = other.price;
		this.category = other.category;
		this.quantity = other.quantity;
		this.inventoryStatus = other.inventoryStatus;
		this.rating = other.rating;
		this.orders = other.orders;
	}
}
