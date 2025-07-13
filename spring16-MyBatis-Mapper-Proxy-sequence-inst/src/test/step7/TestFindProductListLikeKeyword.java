package test.step7;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.kosta.myproject.model.ProductVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFindProductListLikeKeyword {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		String keyword="폰";
		List<ProductVO> list=productMapper.findProductListLikeKeyword(keyword);
		for(ProductVO vo:list)
			System.out.println(vo);
		ctx.close();
	}
}











