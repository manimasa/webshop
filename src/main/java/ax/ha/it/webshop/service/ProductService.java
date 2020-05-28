package ax.ha.it.webshop.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ax.ha.it.webshop.model.Product;
import ax.ha.it.webshop.repository.ProductRepository;

@Transactional(readOnly=true)
@Service
public class ProductService {

	@Autowired
	private ProductRepository productDao;

	@Transactional
	public Product saveProduct(Product product) {
		return productDao.save(product);
	}

	@Transactional
	public void deleteProduct(Product product) {
		productDao.delete(product);
	}

	public List<Product> findAllProducts() {
		return productDao.findAll();
	}

	public Product findProductWithId(Long id) {
		return productDao.findOne(id);
	}

	@Transactional
	public Product updatePrice(Product product, BigDecimal price){
		Product storedProduct = productDao.findOne(product.getId());
		if(storedProduct != null){
			storedProduct.setPrice(price);
			productDao.save(storedProduct);
		}
		return storedProduct;
	}
}
