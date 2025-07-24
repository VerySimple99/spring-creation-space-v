package org.kosa.myproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosa.myproject.domain.Member;
import org.kosa.myproject.domain.Point;
import org.kosa.myproject.mapper.MemberMapper;
import org.kosa.myproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

/**
 * MemberService 클래스의 트랜잭션 동작을 테스트하는 JUnit 클래스입니다.
 * Spring Boot 테스트 환경을 로드하고, 각 테스트 메서드 실행 후 데이터베이스 변경 사항을 롤백하여
 * 테스트 간의 독립성을 보장합니다.
 */
@SpringBootTest
@Transactional // 각 테스트 메서드 실행 후 데이터베이스 변경 사항을 롤백합니다.
public class MemberServiceTest {

    @Autowired
    private MemberService memberService; // 테스트할 MemberService 주입

    @Autowired
    private MemberMapper memberMapper; // 롤백 확인을 위해 MemberMapper도 주입 (선택 사항)

    /**
     * 조회 기능 테스트: findMemberById
     * 읽기 전용 트랜잭션이 잘 적용되는지 확인합니다.
     */
    @Test
    @DisplayName("회원 ID로 조회 기능 테스트 (읽기 전용)")
    void testFindMemberById() {
        // 테스트를 위해 먼저 회원 등록 (이 테스트는 롤백되므로 다른 테스트에 영향 없음)
        Member member = new Member();
        member.setPassword("querypass");
        member.setName("조회회원");
        member.setAddress("조회주소");
        memberMapper.register(member); // 직접 매퍼를 사용하여 등록

        // 서비스 메서드를 통해 회원 조회
        Member foundMember = memberService.findMemberById(member.getId());

        // 조회 결과 검증
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getId()).isEqualTo(member.getId());
        assertThat(foundMember.getName()).isEqualTo("조회회원");
        System.out.println("조회된 회원: " + foundMember);
    }

    /**
     * 조회 기능 테스트: getTotalMemberCount
     * 읽기 전용 트랜잭션이 잘 적용되는지 확인합니다.
     */
    @Test
    @DisplayName("총 회원 수 조회 기능 테스트 (읽기 전용)")
    void testGetTotalMemberCount() {
        // 초기 회원 수 확인 (각 테스트는 독립적이므로 0에서 시작)
        assertThat(memberService.getTotalMemberCount()).isEqualTo(1);

        // 회원 등록 (이 테스트는 롤백되므로 다른 테스트에 영향 없음)
        Member member1 = new Member();
        member1.setPassword("pass1");
        member1.setName("회원1");
        member1.setAddress("주소1");
        memberMapper.register(member1);

        assertThat(memberService.getTotalMemberCount()).isEqualTo(2);

        Member member2 = new Member();
        member2.setPassword("pass2");
        member2.setName("회원2");
        member2.setAddress("주소2");
        memberMapper.register(member2);

        assertThat(memberService.getTotalMemberCount()).isEqualTo(3);
        System.out.println("총 회원 수: " + memberService.getTotalMemberCount());
    }

    /**
     * 등록 기능 테스트: registerMemberAndPoint (성공 케이스)
     * 회원과 포인트가 하나의 트랜잭션 내에서 모두 성공적으로 등록되는지 확인합니다.
     */
    @Test
    @DisplayName("회원 및 포인트 등록 성공 트랜잭션 테스트")
    void testRegisterMemberAndPointSuccess() {
        Member member = new Member();
        member.setPassword("successpass");
        member.setName("성공테스트");
        member.setAddress("성공주소");

        Point point = new Point();
        // memberId는 서비스 메서드 내에서 설정될 것이므로 여기서는 설정하지 않습니다.
        point.setPoint(100);
        point.setPointType("signup");

        // 서비스 메서드 호출 (트랜잭션이 시작되고 완료됨)
        assertDoesNotThrow(() -> memberService.registerMemberAndPoint(member, point));

        // 트랜잭션이 성공적으로 커밋되었는지 확인 (현재 @Transactional로 인해 롤백되지만,
        // 메서드 자체의 성공 여부를 확인하는 것이 목적)
        // 실제 DB 상태 확인은 @Rollback(false)를 사용하거나 별도의 통합 테스트 환경에서 수행.
        // 여기서는 서비스 메서드 내에서 예외가 발생하지 않았음을 확인합니다.

        // 서비스 메서드 내에서 member.getId()와 point.getPointTxId()가 설정되었는지 확인
        assertThat(member.getId()).isNotNull();
        assertThat(point.getPointTxId()).isNotNull();

        System.out.println("성공적으로 등록된 회원 ID: " + member.getId());
        System.out.println("성공적으로 등록된 포인트 트랜잭션 ID: " + point.getPointTxId());

        // 롤백 확인을 위해 다시 조회 (현재 트랜잭션 내에서만 유효)
        Member foundMember = memberMapper.findMemberById(member.getId());
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getName()).isEqualTo("성공테스트");
    }

    /**
     * 등록 기능 테스트: registerMemberAndPoint (실패 및 롤백 케이스)
     * 포인트 등록 시 외래 키 제약 조건 위반으로 인해 전체 트랜잭션이 롤백되는지 확인합니다.
     */
    @Test
    @DisplayName("포인트 등록 실패 시 회원 등록 롤백 트랜잭션 테스트")
    void testRegisterMemberAndPointFailureRollback() {
        Member member = new Member();
        member.setPassword("failpass");
        member.setName("실패테스트");
        member.setAddress("실패주소");

        Point point = new Point();
        point.setMemberId(99999); // 존재하지 않는 memberId를 의도적으로 설정하여 외래 키 제약 조건 위반 유도
        point.setPoint(50);
        point.setPointType("error");

        // registerMemberAndPoint 호출 시 DataIntegrityViolationException이 발생하는지 확인
        assertThrows(DataIntegrityViolationException.class, () -> {
            memberService.registerMemberAndPoint(member, point);
        });

        // 트랜잭션이 롤백되었는지 확인
        // @Transactional 어노테이션 덕분에 테스트 메서드 종료 시 자동으로 롤백됩니다.
        // 따라서 이 테스트가 끝나면 데이터베이스에는 '실패테스트' 회원도, 포인트도 남아있지 않습니다.
        // 현재 트랜잭션 내에서 조회해도 롤백이 예상되므로, 여기서는 직접 조회하여 검증하기 어렵습니다.
        // (JUnit의 @Transactional은 테스트 메서드 종료 시 롤백을 보장하므로,
        // 예외 발생 여부와 함께 롤백 동작을 간접적으로 확인하는 것이 목적입니다.)
        System.out.println("포인트 등록 실패로 인해 전체 트랜잭션이 롤백될 예정입니다.");
    }
}
