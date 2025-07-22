package org.kosta.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.kosta.myproject.model.vo.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
	@RequestMapping("getProductTotalCount")
	@ResponseBody
	public String getProductTotalCount() {
		return "77";// db에서 상품수 조회 
	}
	// Thymeleaf 방식의 ajax 응답  : @ResponseBody를 명시하지 않는다 
	@RequestMapping("getProductTotalCount2")
//	@ResponseBody // Thymeleaf 방식의 ajax 방식에서는 명시하지 않는다 
	public String getProductTotalCount2(Model model) {
		model.addAttribute("productCount", "88");
		return "step10-ajax::#productCountView2"; // view name :: 응답정보를 반영할 요소 id 
	}
	@RequestMapping("study11")
	public String study11() {
		return "step11-ajax";
	}
	
	@RequestMapping("findAllProductList")
	@ResponseBody // ajax 방식으로 응답 ( 페이지가 아니라 필요한 데이터로 응답 ) ,
	// spring mvc ( jackson data bind ) 에 의해 VO or Map 은 json object로 , List or Array 은 json array 로
	// 자동 변환되어 클라이언트에게 응답한다 
	public List<ProductVO> findAllProductList() {
		List<ProductVO> list=new ArrayList<>();
		list.add(new ProductVO(1,"테라","하이트진로",1540));
		list.add(new ProductVO(2,"클라우드","롯데",1740));
		list.add(new ProductVO(3,"카스","두산",1600));
		return list;
	}
	@RequestMapping("study12")
	public String study12() {
		return "step12-ajax";
	}
	// thymeleaf 방식의 ajax 응답은 @ResponseBody 어노테이션을 명시하지 않는다
	@RequestMapping("findAllProductList2")
	public String findAllProductList2(Model model) {
		List<ProductVO> list=new ArrayList<>();
		list.add(new ProductVO(1,"테라","하이트진로",1540));
		list.add(new ProductVO(2,"클라우드","롯데",1740));
		list.add(new ProductVO(3,"카스","두산",1600));
		model.addAttribute("productList", list);
		return "step12-ajax ::#productTbody";
	}
}






















