package ax.ha.it.webshop.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ax.ha.it.webshop.model.Order;
import ax.ha.it.webshop.model.OrderProduct;
import ax.ha.it.webshop.model.Product;
import ax.ha.it.webshop.repository.OrderProductRepository;
import ax.ha.it.webshop.repository.OrderRepository;
import ax.ha.it.webshop.repository.ProductRepository;


@Transactional(readOnly=true)
@Service
public class OrderProductService {
	@Autowired
	private OrderProductRepository orderProductDao;

	@Autowired
	private OrderRepository orderDao;

	@Autowired
	private ProductRepository productDao;

	public List<OrderProduct> findAllOrders() {
		return orderProductDao.findAll();
	}

	@Transactional
	public OrderProduct saveOrderProduct(OrderProduct orderProduct, Order order, Product product, int amount){
		order = orderDao.findOne(order.getID());
		if(order != null){
			orderProduct.setOrder(order);
		}
		product = productDao.findOne(product.getId());
		if(product != null){
			orderProduct.setProduct(product);
		}
		orderProduct.setAmount(amount);
		product.setStock(product.getStock()- amount);
		return orderProductDao.save(orderProduct);
	}

}
