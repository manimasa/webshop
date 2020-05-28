package ax.ha.it.webshop.model;

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

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable=false, length=50)		
	private String firstname;

	@Column(nullable=false, length=50)		
	private String lastname;

	@Column(nullable=false, length=50)		
	private String street;

	@Column(nullable=false, length=50)		
	private int postcode;

	@Column(nullable=false, length=50)		
	private String city;

	@Column(nullable=false, length=50)		
	private String email;

	@Column		
	private int phone;

	@ManyToOne(fetch = FetchType.EAGER)
	private Country customerCountry;

	@OneToMany(mappedBy="orderCustomer", cascade = CascadeType.ALL, 
			orphanRemoval = true, fetch = FetchType.LAZY)
	Set<Order> orders = new HashSet<Order>();

	public Long getID(){
		return id;
	}

	public String getFirstname(){
		return firstname;
	}

	public String getLastname(){
		return lastname;
	}

	public String getStreet(){
		return street;
	}

	public int getPostcode(){
		return postcode;
	}

	public String getCity(){
		return city;
	}

	public String getEmail(){
		return email;
	}

	public int getPhone(){
		return phone;
	}

	public Country getCountry(){
		return customerCountry;
	}

	public Set<Order> getOrders(){
		return orders;
	}

	public void setOrders(Set<Order> orders){
		this.orders = orders;
	}

	public void addOrder(Order order){
		if(!orders.contains(order)){
			orders.add(order);
		}
	}

	public void setID(Long id){
		this.id = id;
	}

	public void setCountry(Country country){
		customerCountry = country;
		country.addCustomer(this);
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public void setStreet(String street){
		this.street = street;
	}
	public void setPostcode(int postcode){
		this.postcode = postcode;
	}

	public void setCity(String city){
		this.city = city;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setPhone(int phone){
		this.phone = phone;
	}

	@Override
	public String toString(){
		return "Customer Id:"+ id + "firstName:" + firstname + "lastname " + lastname + "street" + street +
				"postcode: " + postcode + "city" + city + "email" + email + "phone" + phone;
	}



}
