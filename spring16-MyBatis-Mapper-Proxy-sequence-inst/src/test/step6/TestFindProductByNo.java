package test.step6;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.kosta.myproject.model.ProductVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFindProductByNo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		long productNo=3;
		ProductVO productVO=productMapper.findProductByNo(productNo);
		System.out.println("검색결과:"+productVO);
		ctx.close();
	}
}










