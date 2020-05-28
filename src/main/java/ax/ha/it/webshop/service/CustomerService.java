package ax.ha.it.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ax.ha.it.webshop.model.Country;
import ax.ha.it.webshop.model.Customer;
import ax.ha.it.webshop.repository.CountryRepository;
import ax.ha.it.webshop.repository.CustomerRepository;

@Transactional(readOnly=true)
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerDao;

	@Autowired
	private CountryRepository countryDao;

	public List<Customer> findAllCustomers() {
		return customerDao.findAll();
	}

	public List<Customer> findCustomer(String firstname, String lastname) {
		return customerDao.findByFirstnameAndLastnameLike(firstname, lastname);
	}

	@Transactional
	//Country must have been saved
	public Customer saveCustomer(Customer customer, Country country){
		country = countryDao.findOne(country.getCode());
		if(null != country ){
			customer.setCountry(country);
			return customerDao.save(customer);
		}
		return null;
	}

	@Transactional
	public Customer updateCustomer(Long id, String firstName, String lastName, int postcode,
			String street, String city, String email, int phone){
		Customer storedCustomer = customerDao.findOne(id);
		if(storedCustomer != null){
			if(firstName != null){
				storedCustomer.setFirstname(firstName);
			}
			if(lastName != null){
				storedCustomer.setLastname(lastName);
			}
			if(street != null){
				storedCustomer.setStreet(street);
			}
			if(city != null ){
				storedCustomer.setCity(city);
			}
			if(email != null ){
				storedCustomer.setEmail(email);
			}
			storedCustomer.setPhone(phone);
			storedCustomer.setPostcode(postcode);
			return customerDao.save(storedCustomer);
		}
		return null;
	}

}
