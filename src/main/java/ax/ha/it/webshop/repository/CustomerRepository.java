package ax.ha.it.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ax.ha.it.webshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByFirstnameAndLastnameLike(String firstname, String lastname);	
}

