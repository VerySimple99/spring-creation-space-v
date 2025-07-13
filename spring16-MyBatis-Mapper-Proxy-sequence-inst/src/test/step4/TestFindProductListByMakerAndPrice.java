package test.step4;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.kosta.myproject.model.ProductVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFindProductListByMakerAndPrice {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		// 검색조건이 여러개일때에는 MyBatis에서는 Object 인 VO 또는 Map으로 전달한다 
		String maker="애플";
		int price=70;
		ProductVO productVO=new ProductVO();//VO를 만들고 아래에 검색조건을 할당한 후 전달 
		productVO.setMaker(maker);
		productVO.setPrice(price);
		// maker가 애플 , price가 70 미만인 Product List 를 조회  
		List<ProductVO> list=productMapper.findProductListByMakerAndPrice(productVO);		
		for(ProductVO vo:list)
			System.out.println(vo);
		ctx.close();
	}
}
