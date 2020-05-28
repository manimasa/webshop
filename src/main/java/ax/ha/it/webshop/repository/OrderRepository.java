package ax.ha.it.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import ax.ha.it.webshop.model.Order;



public interface OrderRepository extends JpaRepository<Order, Long>  {

}
