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

	@Override
	public Product clone() {
		return Product.builder().id(this.id)
								.code(this.code)
								.name(this.name)
								.description(this.description)
								.image(this.image)
								.price(this.price)
								.category(this.category)
								.quantity(this.quantity)
								.inventoryStatus(this.inventoryStatus)
								.rating(this.rating)
								.orders(this.orders)
								.build();
	}
}