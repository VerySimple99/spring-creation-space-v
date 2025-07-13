package org.kosta.myproject.model.board;

import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Override
	public String find() {		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("find post");
		return "게시물 정보";
	}
	@Override
	public String findAllList() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("find post list");
		return "게시물 리스트 정보";
	}
}




