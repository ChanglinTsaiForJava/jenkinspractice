package tw.com.ispan.service;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.ProductBean;

@SpringBootTest
public class ProductServiceTests {
	@Autowired
	private ProductService productService;
	
	@Test
	public void testFindById() {
		ProductBean find = productService.findById(1);
		System.out.println("findById="+find);
	}
	@Test
	public void testExists() {
		boolean exist = productService.exists(4);
		System.out.println("exist="+exist);
	}
//	@Test
	public void testCreate() {
		JSONObject obj = new JSONObject();
		obj = obj.put("id", 100);
		obj = obj.put("name", "hahaha");
		obj = obj.put("price", 1.23);
		obj = obj.put("make", "2025-03-11");
		obj = obj.put("expire", 45);
		
		ProductBean create = productService.create(obj.toString());
		System.out.println("create="+create);
	}
//	@Test
	public void testModify() {
		JSONObject obj = new JSONObject()
				.put("id", 100)
				.put("name", "hohoho")
				.put("price", 6.78)
				.put("make", "2025-01-01")
				.put("expire", 90);
		
		ProductBean modify = productService.modify(obj.toString());
		System.out.println("modify="+modify);
	}
//	@Test
	public void testRemove() {
		boolean remove = productService.remove(100);
		System.out.println("remove="+remove);
	}
	@Test
	public void testSelect() {
		List<ProductBean> products = productService.select(null);
		if(products!=null && !products.isEmpty()) {
			for(ProductBean product : products) {
				System.out.println("select product="+product);				
			}
		} else {
			System.out.println("select no data");
		}
	}
//	@Test
	public void testInsert() {
		ProductBean insert = new ProductBean();
		insert.setId(100);
		insert.setName("hahaha");
		insert.setPrice(1.23);
		insert.setMake(new java.util.Date());
		insert.setExpire(45);
		
		ProductBean result = productService.insert(insert);
		System.out.println("insert="+result);
	}
//	@Test
	public void testUpdate() {
		ProductBean update = new ProductBean();
		update.setId(100);
		update.setName("hohoho");
		update.setPrice(6.78);
		update.setMake(new java.util.Date(0));
		update.setExpire(90);
		
		ProductBean result = productService.update(update);
		System.out.println("update="+result);
	}
//	@Test
	public void testDelete() {
		ProductBean delete = new ProductBean();
		delete.setId(100);
		
		boolean result = productService.delete(delete);
		System.out.println("delete="+result);
	}
}
