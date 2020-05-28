package ax.ha.it.webshop.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ax.ha.it.webshop.model.Customer;
import ax.ha.it.webshop.model.Order;
import ax.ha.it.webshop.model.OrderProduct;
import ax.ha.it.webshop.repository.CustomerRepository;
import ax.ha.it.webshop.repository.OrderProductRepository;
import ax.ha.it.webshop.repository.OrderRepository;



@Transactional(readOnly=true)
@Service
public class OrderService {
	@Autowired
	private OrderRepository orderDao;

	@Autowired
	private CustomerRepository customerDao;

	@Autowired
	private OrderProductRepository orderProductDao;

	public List<Order> findAllOrders() {
		return orderDao.findAll();
	}

	public Set<Order> findAndFetchWithEntityAccess(Long customerID){
		Customer customer = customerDao.findOne(customerID);
		Set<Order> orders = customer.getOrders();
		orders.size();
		return orders;
	}

	@Transactional
	public Order saveOrder(Order order, Customer customer){
		customer = customerDao.findOne(customer.getID());
		if(customer != null){
			order.setCustomer(customer);
		}
		return orderDao.save(order);
	}

	@Transactional
	//orderProduct is saved and so is order and product
	public void addOrderProdcut(Order order, OrderProduct orderProduct){
		order = orderDao.findOne(order.getID());
		if(order != null){
			orderProduct = orderProductDao.findOne(orderProduct.getId());
			if(orderProduct != null){
				order.addOrderProduct(orderProduct);
			}
		}				
	}

}
