package test.step2;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.kosta.myproject.model.ProductVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFindAllProductList {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		List<ProductVO> list=productMapper.findAllProductList();
		// product_no 내림차순으로 정렬 
		for(ProductVO vo:list)
			System.out.println(vo);
		ctx.close();
	}
}








