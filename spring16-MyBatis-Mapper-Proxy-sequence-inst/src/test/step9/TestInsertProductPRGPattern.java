package test.step9;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.kosta.myproject.model.ProductVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestInsertProductPRGPattern {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		ProductVO productVO=new ProductVO();
		productVO.setName("감자깡");
		productVO.setMaker("농심");
		productVO.setPrice(17000);
		System.out.println("상품등록완료전:"+productVO);
		productMapper.registerVer2(productVO);
		//이전 register 와는 다르게 상품 등록하면 등록된 상품번호(발급된 시퀀스)가 할당된다 
		System.out.println("상품등록완료후:"+productVO);
		//등록 후 redirect로 다른 페이지에서 응답할 경우 등록된 상품 상세정보를 제공할 수 있다 
		System.out.println("등록된 상품정보:"
		+productMapper.findProductByNo(productVO.getProductNo()));	
		ctx.close();
	}
}













