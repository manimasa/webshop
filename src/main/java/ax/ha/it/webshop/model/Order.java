package ax.ha.it.webshop.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Customer orderCustomer;	
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, 
			orphanRemoval = true, fetch = FetchType.EAGER)
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
	
	public void setID(Long id){
		this.id = id;
	}
	
	public void setCustomer(Customer customer){
		orderCustomer = customer;
		customer.addOrder(this);
	}
		
	public Long getID(){
		return id;
	}
	
	public Customer getCustomer(){
		return orderCustomer;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
}
