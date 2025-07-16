package org.kosa.myproject;

import org.kosa.myproject.model.MemberService;
import org.kosa.myproject.model.NoticeService;
import org.kosa.myproject.model.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyStartupRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MyStartupRunner.class);

	private final MemberService ms;
	private final ProductService ps;
	private final NoticeService ns;

	public MyStartupRunner(MemberService ms, ProductService ps, NoticeService ns) {
		super();
		this.ms = ms;
		this.ps = ps;
		this.ns = ns;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("MyStartupRunner가 실행되었습니다.");

		// AOP 적용 전에는 실제 Core 객체가 스프링 컨테이너로부터 전달된다
		System.out.println(ms.getClass().getName());
		System.out.println(ps.getClass().getName());
		ms.findMemberById("java");
		ms.findMemberListByAddress("오리");
		ms.register("손흥민 토트넘");
		ps.findProductById("1");
		ps.findProductListByMaker("오뚜기");
		ps.findProductListByPriceAndMaker(1000, "롯데");
		ps.deleteProduct("2");
		ns.findNotice("불금");
		ns.findNoticeList("회식", "최상");
	}
}