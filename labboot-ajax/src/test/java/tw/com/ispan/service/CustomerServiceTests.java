package tw.com.ispan.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.CustomerBean;

@SpringBootTest
public class CustomerServiceTests {

	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testLogin() {
		CustomerBean login1 = customerService.login("Alex", "A");
		System.out.println("login1="+login1);
	}

	@Test
	public void testChangePassword() {
		boolean update = customerService.changePassword("Ellen", "EEE", "E");
		System.out.println("update="+update);
	}
}
