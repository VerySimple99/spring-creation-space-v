package org.kosa.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.myproject.domain.Member;
/**
@Mapper: Spring Boot에서 MyBatis를 사용할 때, 이 인터페이스가 MyBatis 매퍼임을 알려주는 어노테이션입니다. 
Spring 컨테이너가 이 인터페이스를 스캔하여 빈(Bean)으로 등록해줍니다.
 */
@Mapper // MyBatis 매퍼 인터페이스임을 명시
public interface MemberMapper {
    
    /**
     * 총 회원 수를 조회하는 메서드
     * XML: <select id="getTotalMemberCount" resultType="int">
     */
    int getTotalMemberCount();
    
    /**
     * 모든 회원 목록을 조회하는 메서드
     * XML: <select id="findMemberList" resultType="org.kosta.myproject.model.MemberVO">
     */
    List<Member> findMemberList();
    
    /**
     * 새로운 회원을 등록하는 메서드
     * XML: <insert id="register" parameterType="org.kosta.myproject.model.MemberVO">
     */
    int register(Member member);
    
    /**
     * 특정 ID의 회원을 삭제하는 메서드
     * XML: <delete id="deleteMember" parameterType="string">
     */
    int deleteMember(String id);
}