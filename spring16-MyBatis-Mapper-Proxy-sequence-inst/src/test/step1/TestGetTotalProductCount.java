package test.step1;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestGetTotalProductCount {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		int count=productMapper.getTotalProductCount();
		System.out.println("총상품수:"+count);
		ctx.close();
	}
}
