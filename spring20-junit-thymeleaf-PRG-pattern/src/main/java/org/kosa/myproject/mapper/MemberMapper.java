package org.kosa.myproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.myproject.domain.Member;

@Mapper
public interface MemberMapper {
    // id가 Integer 타입으로 변경되었으므로 파라미터 타입도 Integer로 변경
    public Member findMemberById(Integer id);

    public int getTotalMemberCount();

    // register 시 Member 객체를 받아서 삽입
    public int register(Member member);
}
