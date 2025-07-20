package org.kosa.myproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.kosa.myproject.domain.Fruit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/fruit")  // 클래스 레벨: 모든 메소드의 기본 경로 설정
public class FruitController {
    
    // 실습용 데이터 (실제 프로젝트에서는 데이터베이스 사용)
    private static final Map<String, Fruit> fruitStore = new HashMap<>();
    
    // 초기 데이터 생성 (static 블록)
    static {
        fruitStore.put("apple", new Fruit("사과", "빨강", 3000, "한국"));
        fruitStore.put("banana", new Fruit("바나나", "노랑", 2000, "필리핀"));
        fruitStore.put("orange", new Fruit("오렌지", "주황", 4000, "미국"));
        fruitStore.put("strawberry", new Fruit("딸기", "빨강", 8000, "한국"));
    }
    
    /**
     * 1. @GetMapping + @RequestParam 예제
     * 과일 검색 기능 (쿼리 파라미터 활용)
     * URL 예: /fruit/search?name=apple&minPrice=1000
     */
    @GetMapping("/search")
    public String searchFruit(
            @RequestParam(value = "name", required = false) String fruitName,
            @RequestParam(value = "minPrice", defaultValue = "0") int minPrice,
            Model model) {
        
        String result = "검색 결과를 확인해보세요.";
        Fruit foundFruit = null;
        
        if (fruitName != null && !fruitName.trim().isEmpty()) {
            foundFruit = fruitStore.get(fruitName.toLowerCase());
            if (foundFruit != null && foundFruit.getPrice() >= minPrice) {
                result = foundFruit.getName() + " 과일을 찾았습니다!";
            } else {
                result = "조건에 맞는 과일을 찾을 수 없습니다.";
            }
        }
        
        // Model 객체를 통해 템플릿에 데이터 전달
        model.addAttribute("searchResult", result);
        model.addAttribute("fruit", foundFruit);
        model.addAttribute("searchedName", fruitName);
        
        return "fruit/detail";  // templates/fruit/detail.html
    }
    
    /**
     * 2. @PathVariable 예제
     * URL 경로의 일부를 변수로 받기
     * URL 예: /fruit/view/apple
     */
    @GetMapping("/view/{fruitId}")
    public String viewFruit(@PathVariable String fruitId, Model model) {
        
        Fruit fruit = fruitStore.get(fruitId.toLowerCase());
        
        if (fruit == null) {
            model.addAttribute("errorMessage", "존재하지 않는 과일입니다: " + fruitId);
            return "error/notfound";  // templates/error/notfound.html
        }
        
        model.addAttribute("fruit", fruit);
        model.addAttribute("fruitId", fruitId);
        
        return "fruit/detail";
    }
    
    /**
     * 3. GET 요청으로 폼 페이지 보여주기
     * 과일 등록 폼 화면
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // 빈 Fruit 객체를 폼에 바인딩용으로 전달
        model.addAttribute("fruit", new Fruit());
        return "fruit/form";  // templates/fruit/form.html
    }
    
    /**
     * 4. @PostMapping + @ModelAttribute 예제
     * 폼 데이터를 객체로 바인딩하여 처리
     */
    @PostMapping("/add")
    public String addFruit(@ModelAttribute Fruit fruit, 
                          RedirectAttributes redirectAttributes) {
        
        // 간단한 유효성 검사
        if (fruit.getName() == null || fruit.getName().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "과일 이름은 필수입니다.");
            return "redirect:/fruit/add";
        }
        
        // 데이터 저장 (실제로는 DB에 저장)
        String key = fruit.getName().toLowerCase();
        fruitStore.put(key, fruit);
        
        // 성공 메시지를 리다이렉트 시 전달
        redirectAttributes.addFlashAttribute("successMessage", 
            fruit.getName() + " 과일이 성공적으로 등록되었습니다!");
        
        return "redirect:/fruit/view/" + key;  // PRG 패턴 적용
    }
    
    /**
     * 5. ModelAndView 사용 예제
     * 뷰 이름과 데이터를 함께 관리하는 전통적인 방식
     */
    @GetMapping("/random")
    public ModelAndView getRandomFruit() {
        // 랜덤 과일 선택
        List<String> fruitKeys = new ArrayList<>(fruitStore.keySet());
        String randomKey = fruitKeys.get(new Random().nextInt(fruitKeys.size()));
        Fruit randomFruit = fruitStore.get(randomKey);
        
        // ModelAndView 객체 생성 (뷰 이름 + 데이터)
        ModelAndView mav = new ModelAndView("fruit/detail");
        mav.addObject("fruit", randomFruit);
        mav.addObject("isRandom", true);
        mav.addObject("message", "오늘의 추천 과일입니다!");
        
        return mav;
    }
    
    /**
     * 6. HttpSession 사용 예제
     * 세션을 이용한 최근 본 과일 기록
     */
    @GetMapping("/history")
    public String viewHistory(HttpSession session, Model model) {
        // 세션에서 히스토리 가져오기
        @SuppressWarnings("unchecked")
        List<String> history = (List<String>) session.getAttribute("fruitHistory");
        
        if (history == null) {
            history = new ArrayList<>();
        }
        
        model.addAttribute("history", history);
        model.addAttribute("historyCount", history.size());
        
        return "fruit/history";
    }
    
    /**
     * 세션에 과일 히스토리 추가하는 헬퍼 메소드
     */
    @GetMapping("/visit/{fruitId}")
    public String visitFruit(@PathVariable String fruitId, 
                            HttpSession session, 
                            RedirectAttributes redirectAttributes) {
        
        // 과일 존재 여부 확인
        if (!fruitStore.containsKey(fruitId.toLowerCase())) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "존재하지 않는 과일입니다.");
            return "redirect:/";
        }
        
        // 세션에서 히스토리 가져오기
        @SuppressWarnings("unchecked")
        List<String> history = (List<String>) session.getAttribute("fruitHistory");
        
        if (history == null) {
            history = new ArrayList<>();
            session.setAttribute("fruitHistory", history);
        }
        
        // 중복 제거 후 맨 앞에 추가
        history.remove(fruitId);
        history.add(0, fruitId);
        
        // 최대 5개까지만 보관
        if (history.size() > 5) {
            history = history.subList(0, 5);
            session.setAttribute("fruitHistory", history);
        }
        
        return "redirect:/fruit/view/" + fruitId;
    }
    
    /**
     * 7. 세션 클리어 예제
     */
    @PostMapping("/clear-history")
    public String clearHistory(HttpSession session, 
                              RedirectAttributes redirectAttributes) {
        session.removeAttribute("fruitHistory");
        redirectAttributes.addFlashAttribute("successMessage", 
            "히스토리가 삭제되었습니다.");
        
        return "redirect:/fruit/history";
    }
}