package org.kosa.myproject.model;

import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Override
	public void findNotice() {
		System.out.println(getClass().getName()+"core concern findNotice");
	}
	@Override
	public void findNoticeList() {
		System.out.println(getClass().getName()+"core concern findNoticeList");
	}
	// 그 외 여러 메서드가 있다 
}
