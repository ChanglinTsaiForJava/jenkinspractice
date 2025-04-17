package tw.com.ispan;

import java.text.DateFormat;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LabbootJpaApplicationTests {
	@Test
	void contextLoads() throws ParseException {
		DateFormat dateFormat = DateFormat.getDateInstance();
		
		String date1 = dateFormat.format(new java.util.Date());
		System.out.println("date1="+date1);
		
		java.util.Date date2 = dateFormat.parse("2025年03月13日");
		System.out.println("date2="+date2);
	}
}
