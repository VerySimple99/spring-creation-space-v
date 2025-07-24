package org.kosa.myproject.service;

import org.kosa.myproject.domain.Member;
import org.kosa.myproject.domain.Point;
import org.kosa.myproject.mapper.MemberMapper;
import org.kosa.myproject.mapper.PointMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 클래스 레벨에 @Transactional(readOnly = true)를 적용하여
 * 모든 메서드의 기본 트랜잭션 속성을 읽기 전용으로 설정합니다.
 * 이는 데이터 조회 작업이 주를 이루는 서비스에서 성능 최적화에 도움이 됩니다.
 */
@Service
@Transactional(readOnly = true) // 클래스 레벨 트랜잭션: 기본적으로 모든 메서드를 읽기 전용으로 설정
public class MemberService {

    // MemberMapper를 주입받아 회원 관련 데이터베이스 작업을 수행합니다.
    //@Autowired
    private final MemberMapper memberMapper;

    // PointMapper를 주입받아 포인트 관련 데이터베이스 작업을 수행합니다.
   // @Autowired
    private final PointMapper pointMapper;

    
    
    public MemberService(MemberMapper memberMapper, PointMapper pointMapper) {
		super();
		this.memberMapper = memberMapper;
		this.pointMapper = pointMapper;
	}

	/**
     * 특정 ID를 가진 회원을 조회하는 메서드입니다.
     * 이 메서드는 클래스 레벨의 @Transactional(readOnly = true) 속성을 따릅니다.
     * 읽기 전용 트랜잭션은 데이터 변경을 허용하지 않으며, 성능상 이점을 가질 수 있습니다.
     *
     * @param id 조회할 회원의 ID (Integer 타입)
     * @return 조회된 Member 객체, 없으면 null
     */
    public Member findMemberById(Integer id) {
        // 읽기 전용 트랜잭션이 적용됩니다.
        return memberMapper.findMemberById(id);
    }

    /**
     * 전체 회원 수를 조회하는 메서드입니다.
     * 이 메서드 역시 클래스 레벨의 @Transactional(readOnly = true) 속성을 따릅니다.
     *
     * @return 전체 회원 수
     */
    public int getTotalMemberCount() {
        // 읽기 전용 트랜잭션이 적용됩니다.
        return memberMapper.getTotalMemberCount();
    }

    /**
     * 새로운 회원과 해당 회원의 포인트를 동시에 등록하는 트랜잭션 메서드입니다.
     * 이 메서드에는 @Transactional 어노테이션을 별도로 적용하여
     * 클래스 레벨의 readOnly = true 속성을 오버라이드하고,
     * 읽기/쓰기 가능한 트랜잭션으로 설정합니다.
     *
     * 트랜잭션의 ACID 특성:
     * A (Atomicity, 원자성): 회원 등록과 포인트 등록 두 작업이 모두 성공하거나,
     * 둘 중 하나라도 실패하면 전체 작업이 롤백되어 아무것도 반영되지 않습니다.
     * C (Consistency, 일관성): 데이터베이스의 무결성 제약 조건(예: 외래 키)을 유지합니다.
     * 만약 포인트 등록 시 존재하지 않는 회원 ID를 참조하면 예외가 발생하고 롤백됩니다.
     * I (Isolation, 격리성): 이 트랜잭션이 실행되는 동안 다른 트랜잭션으로부터 영향을 받지 않고,
     * 다른 트랜잭션에 영향을 주지 않도록 격리됩니다 (기본 격리 레벨).
     * D (Durability, 지속성): 트랜잭션이 성공적으로 커밋되면, 시스템 장애에도 불구하고
     * 그 결과는 영구적으로 데이터베이스에 저장됩니다.
     *
     * @param member 등록할 회원 정보 (id는 AUTO_INCREMENT이므로 서비스에서 설정하지 않음)
     * @param point 등록할 포인트 정보 (memberId는 회원 등록 후 생성된 ID로 설정됨)
     * @throws RuntimeException 데이터베이스 작업 중 예외 발생 시 (예: 외래 키 제약 조건 위반)
     */
    @Transactional // 쓰기 작업이므로 readOnly = false (기본값). 트랜잭션 범위 시작.
    public void registerMemberAndPoint(Member member, Point point) {
        // 1. 회원 정보 등록
        // memberMapper.register(member) 호출 시, MyBatis는 설정된 keyProperty("id")를 통해
        // AUTO_INCREMENT로 생성된 회원 ID를 member 객체의 id 필드에 자동으로 채워줍니다.
        memberMapper.register(member);
        System.out.println("회원 등록 성공. 생성된 회원 ID: " + member.getId());

        // 2. 포인트 정보 등록
        // 포인트는 방금 등록된 회원의 ID를 참조해야 합니다.
        // 회원 등록 후 member 객체에 채워진 ID를 point 객체의 memberId 필드에 설정합니다.
        point.setMemberId(member.getId());
        pointMapper.register(point);
        System.out.println("포인트 등록 성공. 생성된 포인트 트랜잭션 ID: " + point.getPointTxId());

        // --- 트랜잭션 롤백 테스트를 위한 코드 (학습용) ---
        // 아래 주석을 해제하면, 회원 등록은 성공하지만 포인트 등록 후 강제로 RuntimeException을 발생시켜
        // 전체 트랜잭션이 롤백되어 회원 등록도 취소되는 것을 확인할 수 있습니다.
        // if (true) {
        //     throw new RuntimeException("강제 예외 발생: 트랜잭션 롤백 테스트");
        // }
        // --------------------------------------------------

        // 이 메서드가 성공적으로 완료되면, Spring의 @Transactional 어노테이션에 의해
        // 회원 등록과 포인트 등록 작업이 모두 데이터베이스에 커밋됩니다.
        // 만약 이 과정에서 어떤 RuntimeException (또는 Error)이 발생하면,
        // 모든 변경 사항은 자동으로 롤백됩니다.
    }
}
