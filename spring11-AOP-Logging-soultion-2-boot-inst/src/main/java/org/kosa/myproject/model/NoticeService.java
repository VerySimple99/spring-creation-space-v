package org.kosa.myproject.model;

import org.springframework.stereotype.Service;

@Service
public class NoticeService {


	public void findNotice(String message) {
		System.out.println(getClass().getName()+"core concern findNotice");
		//logger.debug("cross cutting NoticeServiceImpl findNotice 검색어 {}",message);
	}

	public void findNoticeList(String message,String grade) {
		System.out.println(getClass().getName()+"core concern findNoticeList");
		//logger.debug("cross cutting NoticeServiceImpl findNoticeList 검색어 {} 공지등급 {}",message,grade);
	}
	// 그 외 여러 메서드가 있다 
}






