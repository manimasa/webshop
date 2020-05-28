package ax.ha.it.webshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private Long code;
	
	@Column
	private String name;
	
	
	@OneToMany(mappedBy="customerCountry", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Customer> customers = new HashSet<Customer>();
	
	public void setCode(Long code){
		this.code = code;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void setCustomers(Set<Customer> customer){
		this.customers = customer;
	}
	
	public void addCustomer(Customer customer){
		if(!customers.contains(customer)){
			customers.add(customer);
		}
	}
	
	public Set<Customer> getCustomers(){
		return customers;
	}
	
	public Long getCode(){
		return code;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return "code: " + code + "name: " + name;
	}
}
