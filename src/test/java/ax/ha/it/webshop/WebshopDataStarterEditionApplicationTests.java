package ax.ha.it.webshop;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ax.ha.it.webshop.model.Country;
import ax.ha.it.webshop.model.Customer;
import ax.ha.it.webshop.model.Order;
import ax.ha.it.webshop.model.OrderProduct;
import ax.ha.it.webshop.model.Product;
import ax.ha.it.webshop.service.CountryService;
import ax.ha.it.webshop.service.CustomerService;
import ax.ha.it.webshop.service.OrderProductService;
import ax.ha.it.webshop.service.OrderService;
import ax.ha.it.webshop.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebshopDataStarterEditionApplication.class)
public class WebshopDataStarterEditionApplicationTests {

	@Autowired
	ProductService productService;

	@Autowired
	CountryService countryService;

	@Autowired
	CustomerService customerService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderProductService orderProductService;


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveAndFindProduct() {
		Product ipad = new Product();
		ipad.setName("iPad");
		ipad.setDescription("A shiny new iPad");
		ipad.setPrice(new BigDecimal("399.90"));
		ipad.setStock(20);

		ipad = productService.saveProduct(ipad);
		assertNotEquals(null, ipad.getId());
		
		ipad = productService.updatePrice(ipad, new BigDecimal("299.90"));

		ipad = productService.findProductWithId(ipad.getId());
		assertEquals("iPad", ipad.getName());

		ipad.setStock(10);
		productService.saveProduct(ipad);

		ipad = productService.findProductWithId(ipad.getId());
		assertEquals(10, ipad.getStock());

	}

	@Test
	public void testSaveCustomer(){
		Country country = new Country();
		country.setName("Finland");
		country = countryService.saveCountry(country);
		Customer customer = new Customer();
		customer.setFirstname("Greg");
		customer.setLastname("Larsson");
		customer.setStreet("Demövsagt");
		customer.setCity("Helsingförs");
		customer.setPostcode(2133);
		customer.setEmail("Greg@Larsson");
		customer.setPhone(5441);
		customerService.saveCustomer(customer, country);
	}

	@Test
	public void testSaveCustomerMakeOrder(){
		Product iPhone = new Product();
		iPhone.setName("iPhone");
		iPhone.setDescription("A shiny new iPhone");
		iPhone.setPrice(new BigDecimal("399.90"));
		iPhone.setStock(20);
		iPhone = productService.saveProduct(iPhone);

		Product ps4 = new Product();
		ps4.setName("Playstation4");
		ps4.setDescription("A shiny new Playstation4");
		ps4.setPrice(new BigDecimal("499.90"));
		ps4.setStock(5);
		ps4 = productService.saveProduct(ps4);


		Country country = new Country();
		country.setName("Sweden");
		country = countryService.saveCountry(country);
		Customer customer = new Customer();
		customer.setFirstname("Mark");
		customer.setLastname("Dan");
		customer.setStreet("västra holm");
		customer.setCity("Malmö");
		customer.setPostcode(1324);
		customer.setEmail("Mark@Dan");
		customer.setPhone(55111);
		customerService.saveCustomer(customer, country);


		Order customerOrder = new Order();
		customerOrder.setDate(new Date());
		customerOrder = orderService.saveOrder(customerOrder, customer);
		
		//customer adds to order
		OrderProduct orderProduct = new OrderProduct();
		orderProductService.saveOrderProduct(orderProduct, customerOrder, iPhone, 1);
		//register product  
		orderService.addOrderProdcut(customerOrder, orderProduct);
		
		//customer adds to order
		OrderProduct orderProductTwo = new OrderProduct();
		orderProductService.saveOrderProduct(orderProductTwo, customerOrder, ps4, 1);
		orderService.addOrderProdcut(customerOrder, orderProductTwo);
	}

	@Test
	public void updateCustomer(){

		Country country = new Country();
		country.setName("Kenya");
		country = countryService.saveCountry(country);
		Customer customer = new Customer();
		customer.setFirstname("Sann");
		customer.setLastname("Malik");
		customer.setStreet("Soft");
		customer.setCity("akunamatata");
		customer.setPostcode(6588);
		customer.setEmail("Softa@Malik");
		customerService.saveCustomer(customer, country);

		Customer acustomer = customerService.findCustomer("Sann", "Malik").get(0);
		assertEquals("Sann", acustomer.getFirstname());
		customerService.updateCustomer(acustomer.getID(), "Sann", "Malik",
				6579, "JamicaStreet","Soft", "Greg@Larsson", 7551);
	}


	@Test
	public void testDeleteProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllProducts() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindProductWithId() {
		Product wristwatch = new Product();
		wristwatch.setName("Rolex");
		wristwatch.setDescription("New rolex wristwatch for men");
		wristwatch.setPrice(new BigDecimal("999.90"));
		wristwatch.setStock(5);
		wristwatch = productService.saveProduct(wristwatch);
		wristwatch = productService.findProductWithId(wristwatch.getId());
		assertEquals("Rolex", wristwatch.getName());
	}

	@Test
	public void testFindAllOrder(){
		Product xBoxOne = new Product();
		xBoxOne.setName("XBoxOne");
		xBoxOne.setDescription("A shiny new XBoxOne");
		xBoxOne.setPrice(new BigDecimal("399.90"));
		xBoxOne.setStock(20);	
		xBoxOne = productService.saveProduct(xBoxOne);

		Product camera = new Product();
		camera.setName("DSLR");
		camera.setDescription("A shiny new DSLR camera");
		camera.setPrice(new BigDecimal("699.90"));
		camera.setStock(5);
		camera = productService.saveProduct(camera);

		Country country = new Country();
		country.setName("England");
		country = countryService.saveCountry(country);
		Customer customer = new Customer();
		customer.setFirstname("Tom");
		customer.setLastname("Starn");
		customer.setStreet("east");
		customer.setCity("jerssy");
		customer.setPostcode(1788);
		customer.setEmail("Tom@Starn");
		customer.setPhone(55333);
		customer = customerService.saveCustomer(customer, country);

		//customer creates an order
		Order customerOrder = new Order();
		customerOrder.setDate(new Date());
		customerOrder = orderService.saveOrder(customerOrder, customer);

		//customer adds to order
		OrderProduct orderProduct = new OrderProduct();
		orderProductService.saveOrderProduct(orderProduct, customerOrder, xBoxOne, 1);
		//register product  
		orderService.addOrderProdcut(customerOrder, orderProduct);

		//customer adds to order
		OrderProduct orderProductTwo = new OrderProduct();
		orderProductService.saveOrderProduct(orderProductTwo, customerOrder, camera, 1);
		orderService.addOrderProdcut(customerOrder, orderProductTwo);

		List<Order> orders = orderService.findAllOrders();
		assertEquals(2,orders.get(0).getOrderProduct().size());
	}

	@Test
	public void testFindCustomerOrderById(){
		Product hardDrive = new Product();
		hardDrive.setName("WD 1TB");
		hardDrive.setDescription("A new external hardrive");
		hardDrive.setPrice(new BigDecimal("199.90"));
		hardDrive.setStock(20);
		hardDrive = productService.saveProduct(hardDrive);
		
		Product headSet = new Product();
		headSet.setName("Beats headset");
		headSet.setDescription("A shiny new headset");
		headSet.setPrice(new BigDecimal("99.99"));
		headSet.setStock(7);
		headSet = productService.saveProduct(headSet);

		Country country = new Country();
		country.setName("Denmark");
		country = countryService.saveCountry(country);
		Customer customer = new Customer();
		customer.setFirstname("Tomi");
		customer.setLastname("Animasaun");
		customer.setStreet("Nørregade");
		customer.setCity("Copenhagen");
		customer.setPostcode(1325);
		customer.setEmail("Tomi@Animasaun");
		customer.setPhone(55242);
		customer = customerService.saveCustomer(customer, country);

		//customer creates an order
		Order customerOrder = new Order();
		customerOrder.setDate(new Date());
		customerOrder = orderService.saveOrder(customerOrder, customer);

		//customer adds product to order
		OrderProduct orderProduct = new OrderProduct();
		orderProductService.saveOrderProduct(orderProduct, customerOrder, headSet, 2);
		
		//register product  
		orderService.addOrderProdcut(customerOrder, orderProduct);
		
		OrderProduct orderProductTwo = new OrderProduct();
		orderProductService.saveOrderProduct(orderProductTwo, customerOrder, hardDrive, 1);
		
		orderService.addOrderProdcut(customerOrder, orderProductTwo);
				
		//Change price
		headSet = productService.updatePrice(headSet, new BigDecimal("149.99"));
		assertEquals(new BigDecimal("149.99"),headSet.getPrice());
		hardDrive = productService.updatePrice(hardDrive, new BigDecimal("169.99"));
		assertEquals(new BigDecimal("169.99"),hardDrive.getPrice());

		Set<Order> orders = orderService.findAndFetchWithEntityAccess(customer.getID());
		Order anOrder = (Order) orders.iterator().next();
		assertEquals(false, anOrder.getOrderProduct().isEmpty());
	}
	
	@Test
	public void saveCountry(){
		Country country = new Country();
		country.setName("USA");
		country = countryService.saveCountry(country);
		
		assertEquals(country.getName(), "USA");
	}
}
