package org.kosta.myproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Override
	public void deleteProduct(String id) {
		System.out.println("core deleteProduct id:"+id);
	}
	@Override
	public void findProductById(String id) {
		System.out.println("core findProductById id:"+id);
		logger.debug("cross cutting ProductServiceImpl findProductById 검색어 {}",id);
	}
	@Override
	public void findProductListByMaker(String maker) {
		System.out.println("core findProductListByMaker maker:"+maker);
		logger.debug("cross cutting ProductServiceImpl findProductListByMaker 검색어 {}",maker);
	}
	@Override
	public void findProductListByPriceAndMaker(int price, String maker) {
		System.out.println("core findProductListByPriceAndMaker price:"+price +" maker:"+maker);
		logger.debug("cross cutting ProductServiceImpl findProductListByPriceAndMaker 검색어1 {} 검색어2 {}",price,maker);
	}

}
