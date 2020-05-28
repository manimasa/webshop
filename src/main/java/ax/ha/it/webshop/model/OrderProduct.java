package ax.ha.it.webshop.model;




import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderProduct")
public class OrderProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", referencedColumnName = "id")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "orderID", referencedColumnName = "id")
	private Order order;

	
	@Column(nullable=false)
	private int amount;
	
	@Column(nullable=false)
	private BigDecimal price;

	public void setOrder(Order order){
		this.order = order;
		order.addOrderProduct(this);
	}

	public void setProduct(Product product){
		this.product = product;
		product.addOrderProduct(this);
		this.price = product.getPrice();
	}

	public void setAmount(int amount){
		if( amount > 0){
			this.amount = amount;	
		}
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}

	public Order getOrder(){
		return order;
	}

	public Product getProduct(){
		return product;
	}

	public int getAmount(){
		return amount;
	}
	
	public Long getId(){
		return id;
	}
}
