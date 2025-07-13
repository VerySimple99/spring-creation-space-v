package test.step3;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.kosta.myproject.model.ProductVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFindProductListByMaker {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		String maker="삼성";
		List<ProductVO> list=productMapper.findProductListByMaker(maker);
		// product_no 내림차순으로 정렬 , 삼성 maker 상품들만 출력 
		for(ProductVO vo:list)
			System.out.println(vo);
		ctx.close();
	}
}








