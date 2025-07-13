package test.step5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.ProductMapper;
import org.kosta.myproject.model.ProductVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFindProductListByLowPriceAndHighPrice {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ProductMapper productMapper=(ProductMapper)ctx.getBean("productMapper");
		System.out.println(productMapper.getClass());
		// 검색조건이 여러개일때에는 MyBatis에서는 Object 인 VO 또는 Map으로 전달한다 
		// 최저가 30 초과하고 최고가 130 미만 가격 상품을 조회 => 검색조건이 여러개이고 같은 성격인 price가 두개이므로 
		// 검색조건을 추가 => maker 삼성인 제품들만 검색하도록 한다 
		// ProductVO 로 전달하기에는 부적합 , 이럴 경우에는 Map이 적합하다 
		Map<String,Object> map=new HashMap<>();
		map.put("HIGHPRICE", 130);
		map.put("LOWPRICE", 30);
		map.put("MAKER", "삼성");
		List<ProductVO> list=productMapper.findProductListByLowPriceAndHighPrice(map);		
		for(ProductVO vo:list)
			System.out.println(vo);
		ctx.close();
	}
}








