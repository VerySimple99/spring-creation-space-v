package org.kosa.myproject;

import org.kosa.myproject.model.board.BoardService;
import org.kosa.myproject.model.member.DuplicateIdException;
import org.kosa.myproject.model.member.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyStartRunner implements CommandLineRunner{
	private BoardService bs;
	private MemberService ms;	


	public MyStartRunner(BoardService bs, MemberService ms) {
		super();
		this.bs = bs;
		this.ms = ms;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("main:"+bs.find());
		System.out.println("main:"+bs.findAllList());
		ms.findMember();
		ms.findAllMember();
		try {
			ms.register("java", "아이유");
		} catch (DuplicateIdException e) {
			System.out.println("main:"+e.getMessage());
		}
	}

}
