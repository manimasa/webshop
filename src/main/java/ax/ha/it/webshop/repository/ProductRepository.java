package ax.ha.it.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ax.ha.it.webshop.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
