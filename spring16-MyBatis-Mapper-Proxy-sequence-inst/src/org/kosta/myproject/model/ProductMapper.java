package org.kosta.myproject.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper // MyBatis Proxy 생성을 위한 어노테이션 
// MyBatis Proxy 란?  Mapper(DAO) Interface 를 implements 한 구상(구현) 클래스(객체)를 MyBatis Framework 에서 자동 생성 
public interface ProductMapper {

	int getTotalProductCount();

	List<ProductVO> findAllProductList();

	List<ProductVO> findProductListByMaker(String maker);

	List<ProductVO> findProductListByMakerAndPrice(ProductVO productVO);

	List<ProductVO> findProductListByLowPriceAndHighPrice(Map<String, Object> map);

	ProductVO findProductByNo(long productNo);

	List<ProductVO> findProductListLikeKeyword(String keyword);

	void register(ProductVO productVO);

	void registerVer2(ProductVO productVO);

}










