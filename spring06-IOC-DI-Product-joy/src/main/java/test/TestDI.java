package test;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDI {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
	//	ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");
		ProductService service=(ProductService)ctx.getBean("productServiceImpl");
		System.out.println(service.findProductById("javaking"));
		ctx.close();
	}
}
