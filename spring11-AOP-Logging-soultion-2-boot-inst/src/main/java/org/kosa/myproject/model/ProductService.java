package org.kosa.myproject.model;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	//private Logger logger=LoggerFactory.getLogger(getClass());

	public void deleteProduct(String id) {
		System.out.println("core deleteProduct id:"+id);
	}

	public void findProductById(String id) {
		System.out.println("core findProductById id:"+id);
		//logger.debug("cross cutting ProductServiceImpl findProductById 검색어 {}",id);
	}

	public void findProductListByMaker(String maker) {
		System.out.println("core findProductListByMaker maker:"+maker);
		//logger.debug("cross cutting ProductServiceImpl findProductListByMaker 검색어 {}",maker);
	}

	public void findProductListByPriceAndMaker(int price, String maker) {
		System.out.println("core findProductListByPriceAndMaker price:"+price +" maker:"+maker);
		//logger.debug("cross cutting ProductServiceImpl findProductListByPriceAndMaker 검색어1 {} 검색어2 {}",price,maker);
	}

}
