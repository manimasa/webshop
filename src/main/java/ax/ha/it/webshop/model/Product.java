package ax.ha.it.webshop.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)	
	private String description;
	
	@Column(nullable=false)	
	private BigDecimal price;

	@Column(nullable=false)	
	private int stock;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<OrderProduct> orderProduct = new HashSet<OrderProduct>();
	
	public Set<OrderProduct> getOrderProduct(){
		return orderProduct;
	}
	
	public void setOrderProduct(Set<OrderProduct> productOrders){
		this.orderProduct= productOrders;
	}
	
	public void addOrderProduct(OrderProduct productOrder){
			this.orderProduct.add(productOrder);
		}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
