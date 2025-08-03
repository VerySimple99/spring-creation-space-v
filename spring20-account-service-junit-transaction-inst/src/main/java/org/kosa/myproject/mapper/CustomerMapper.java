
// CustomerMapper.java - 고객 데이터 접근 인터페이스
package org.kosa.myproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.myproject.domain.Customer;

/**
 * 고객 정보 관련 데이터베이스 접근 인터페이스
 * - 고객 등록 기능 제공
 * - MyBatis가 구현체를 자동 생성
 */
@Mapper
public interface CustomerMapper {
    
    /**
     * 신규 고객 등록
     * @param customer 등록할 고객 정보 (name, email 필수)
     * @return 등록된 행의 수 (1: 성공, 0: 실패)
     */
    int register(Customer customer);
    
    /**
     * 고객 ID로 고객 정보 조회 (향후 확장 가능성을 위해 추가)
     * @param customerId 조회할 고객 ID
     * @return 고객 정보 (없으면 null)
     */
    Customer findById(Long customerId);
}