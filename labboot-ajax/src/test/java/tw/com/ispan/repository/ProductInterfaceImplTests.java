package tw.com.ispan.repository;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.ProductBean;

@SpringBootTest
public class ProductInterfaceImplTests {
	@Autowired
	private ProductInterface productDao;
	
	@Test
	public void test() {
		JSONObject obj = new JSONObject()
				.put("name", "a")
				.put("start", 0)
				.put("rows", 3)
				.put("dir", true)
				.put("sort", "id");
		
		long count = productDao.count(obj);
		System.out.println("count="+count);
		
		List<ProductBean> find = productDao.find(obj);
		if(find!=null && !find.isEmpty()) {
			for(ProductBean product : find) {
				System.out.println("product="+product);
			}
		}
	}
}
