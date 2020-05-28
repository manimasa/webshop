package ax.ha.it.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import ax.ha.it.webshop.model.OrderProduct;

public  interface OrderProductRepository extends JpaRepository<OrderProduct, Long>  {

}
