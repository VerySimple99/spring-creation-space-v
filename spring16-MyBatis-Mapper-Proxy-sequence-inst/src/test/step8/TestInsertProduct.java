package test.step8;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.kosta.myproject.model.ProductVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestInsertProduct {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		ProductVO productVO=new ProductVO();
		productVO.setName("가나초콜릿4");
		productVO.setMaker("롯데4");
		productVO.setPrice(1000);
		productMapper.register(productVO);
		System.out.println("상품등록완료 "+productVO);
		List<ProductVO> list=productMapper.findAllProductList();
		for(ProductVO vo:list)
			System.out.println(vo);
		ctx.close();
	}
}








