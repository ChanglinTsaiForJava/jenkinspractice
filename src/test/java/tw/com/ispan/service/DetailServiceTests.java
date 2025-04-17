package tw.com.ispan.service;

import java.io.FileOutputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.DetailBean;

@SpringBootTest
public class DetailServiceTests {
	@Autowired
	private DetailService detailService;
	
	@Test
	public void testFindById() throws Exception {
		DetailBean detail1 = detailService.findById(2);
		System.out.println("detail1="+detail1);
		if(detail1!=null) {
			byte[] photo = detail1.getPhoto();
			if(photo!=null && photo.length!=0) {
				FileOutputStream out = new FileOutputStream("C:/Users/User/Desktop/demo.jpg");
				out.write(photo, 0, photo.length);
				out.close();
			}
		}
	}
}
