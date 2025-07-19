package org.kosa.myproject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import org.kosa.myproject.domain.Director;
import org.kosa.myproject.mapper.DirectorMapper;

/**
 * DirectorMapper 동작 테스트를 위한 실행 클래스
 * 
 * CommandLineRunner: Spring Boot 애플리케이션 시작 후 자동 실행
 * - 개발 단계에서 MyBatis 매핑이 정상 동작하는지 검증
 * - 학습용: SQL 실행 결과를 콘솔에서 직접 확인 가능
 */
@Component
@Order(1)
public class MyBatisStudyDirectorRunner implements CommandLineRunner {
	
	// SLF4J 로거 - MyBatis 쿼리 실행 로그와 함께 확인 가능
	 static final Logger logger = LoggerFactory.getLogger(MyBatisStudyDirectorRunner.class);
	
	// DirectorMapper 의존성 주입
	// @Autowired: Spring이 자동으로 DirectorMapper 구현체를 주입
	@Autowired
	 DirectorMapper directorMapper;
	
	/**
	 * 애플리케이션 시작 시 자동 실행되는 메서드
	 * - 각 테스트 메서드를 순차적으로 실행
	 * - 예외 발생 시 전체 애플리케이션이 종료됨
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("=================================================");
		logger.info("DirectorMapper 테스트 시작");
		logger.info("=================================================");
		
		try {
			// 1단계: 기본 조회 테스트
//		testFindAll();
//			testFindById();
//			
//			// 2단계: 데이터 조작 테스트 (CUD)
//			testInsertAndUpdate();
//			
			// 3단계: 통계 쿼리 테스트
//			testStatistics();
			
			// 4단계: 삭제 테스트 (마지막에 실행)
	//		testDelete();
			
		} catch (Exception e) {
			logger.error("테스트 실행 중 오류 발생: {}", e.getMessage(), e);
			throw e; // 예외 재발생으로 애플리케이션 종료
		}
		
		logger.info("=================================================");
		logger.info("DirectorMapper 테스트 완료");
		logger.info("=================================================");
	}
	
	/**
	 * 1. 모든 감독 목록 조회 테스트
	 * - findAll() 메서드 동작 확인
	 * - 데이터베이스에 저장된 모든 감독 정보 출력
	 */
	 void testFindAll() {
		logger.info("\n--- 1. 모든 감독 목록 조회 테스트 ---");
		
		List<Director> directors = directorMapper.findAll();
		
		logger.info("조회된 감독 수: {}", directors.size());
		
		// 조회된 각 감독 정보를 상세히 출력
		for (int i = 0; i < directors.size(); i++) {
			Director director = directors.get(i);
			logger.info("{}. 감독ID: {}, 감독명: {}, 소개: {}", 
				i + 1, 
				director.getDirectorId(), 
				director.getDirectorName(), 
				director.getIntro()
			);
		}
		
		// 비즈니스 로직 테스트: 감독명 유효성 검증
		if (!directors.isEmpty()) {
			Director firstDirector = directors.get(0);
			boolean isValid = firstDirector.isValidDirectorName();
			logger.info("첫 번째 감독의 이름 유효성: {}", isValid);
		}
	}
	
	/**
	 * 2. 특정 감독 조회 테스트
	 * - findById() 메서드 동작 확인
	 * - 존재하는 ID와 존재하지 않는 ID 모두 테스트
	 */
	 void testFindById() {
		logger.info("\n--- 2. 특정 감독 조회 테스트 ---");
		
		// 2-1. 존재하는 감독 조회 (ID = 1)
		Long existingId = 1L;
		Director existingDirector = directorMapper.findById(existingId);
		
		if (existingDirector != null) {
			logger.info("ID {} 감독 조회 성공: {}", existingId, existingDirector);
			logger.info("감독명: {}, 소개: {}", 
				existingDirector.getDirectorName(), 
				existingDirector.getIntro()
			);
		} else {
			logger.warn("ID {} 감독을 찾을 수 없습니다.", existingId);
		}
		
		// 2-2. 존재하지 않는 감독 조회 (ID = 999)
		Long nonExistingId = 999L;
		Director nonExistingDirector = directorMapper.findById(nonExistingId);
		
		if (nonExistingDirector == null) {
			logger.info("ID {} 감독 조회 결과: null (정상 - 존재하지 않는 ID)", nonExistingId);
		} else {
			logger.warn("예상과 다름: ID {}에 대해 감독 정보가 반환됨", nonExistingId);
		}
	}
	
	/**
	 * 3. 감독 등록 및 수정 테스트
	 * - insert() 메서드: Auto Increment 키 생성 확인
	 * - update() 메서드: 데이터 수정 확인
	 */
	 void testInsertAndUpdate() {
		logger.info("\n--- 3. 감독 등록 및 수정 테스트 ---");
		
		// 3-1. 새로운 감독 등록
		Director newDirector = new Director();
		newDirector.setDirectorName("크리스토퍼 놀란");
		newDirector.setIntro("인셉션, 인터스텔라로 유명한 할리우드 거장");
		
		logger.info("등록 전 감독 ID: {}", newDirector.getDirectorId()); // null이어야 함
		
		int insertResult = directorMapper.insert(newDirector);
		
		logger.info("등록 결과: {} (1이면 성공)", insertResult);
		logger.info("등록 후 자동 생성된 감독 ID: {}", newDirector.getDirectorId()); // Auto Increment된 값
		
		// 3-2. 등록된 감독 정보 수정
		if (newDirector.getDirectorId() != null) {
			newDirector.setIntro("다크 나이트, 덩케르크 감독으로도 유명");
			
			int updateResult = directorMapper.update(newDirector);
			
			logger.info("수정 결과: {} (1이면 성공)", updateResult);
			
			// 수정된 내용 확인
			Director updatedDirector = directorMapper.findById(newDirector.getDirectorId());
			logger.info("수정 후 감독 정보: {}", updatedDirector);
		}
	}
	
	/**
	 * 4. 감독별 통계 정보 조회 테스트
	 * - findDirectorStatistics() 메서드 동작 확인
	 * - JOIN 쿼리와 집계 함수 결과 확인
	 */
	 void testStatistics() {
		logger.info("\n--- 4. 감독별 통계 정보 조회 테스트 ---");
		
		List<Map<String, Object>> statistics = directorMapper.findDirectorStatistics();
		
		logger.info("통계 데이터 건수: {}", statistics.size());
		
		// 각 감독의 통계 정보 출력
		for (int i = 0; i < statistics.size(); i++) {
			Map<String, Object> stat = statistics.get(i);
			
			String directorName = (String) stat.get("directorName");
			BigDecimal totalAttendance = (BigDecimal) stat.get("totalAttendance");
			BigDecimal averageAttendance = (BigDecimal) stat.get("averageAttendance");
			
			logger.info("{}. 감독: {}, 총관객수: {}, 평균관객수: {}", 
				i + 1, directorName, totalAttendance, averageAttendance);
		}
		
		// 통계 데이터 분석
		if (!statistics.isEmpty()) {
			Map<String, Object> topDirector = statistics.get(0); // ORDER BY로 이미 정렬됨
			logger.info("최고 흥행 감독: {}", topDirector.get("directorName"));
		}
	}
	
	/**
	 * 5. 감독 삭제 테스트
	 * - deleteById() 메서드 동작 확인
	 * - 외래키 제약조건 동작 확인 (영화가 있는 감독 삭제 시도)
	 */
	 void testDelete() {
		logger.info("\n--- 5. 감독 삭제 테스트 ---");
		
		// 5-1. 영화가 없는 감독 삭제 시도 (앞서 등록한 크리스토퍼 놀란)
		// 실제로는 영화 데이터가 없으므로 삭제 가능
		
		// 먼저 현재 등록된 모든 감독 확인
		List<Director> allDirectors = directorMapper.findAll();
		logger.info("삭제 전 전체 감독 수: {}", allDirectors.size());
		
		// 마지막에 등록된 감독을 찾아서 삭제 (일반적으로 ID가 가장 큰 값)
		if (!allDirectors.isEmpty()) {
			Director lastDirector = allDirectors.get(allDirectors.size() - 1);
			Long deleteTargetId = lastDirector.getDirectorId();
			
			logger.info("삭제 대상 감독: ID {}, 이름 '{}'", 
				deleteTargetId, lastDirector.getDirectorName());
			
			try {
				int deleteResult = directorMapper.deleteById(deleteTargetId);
				logger.info("삭제 결과: {} (1이면 성공)", deleteResult);
				
				// 삭제 확인
				Director deletedDirector = directorMapper.findById(deleteTargetId);
				if (deletedDirector == null) {
					logger.info("삭제 확인: 해당 감독이 정상적으로 삭제됨");
				} else {
					logger.warn("삭제 실패: 해당 감독이 여전히 존재함");
				}
				
			} catch (Exception e) {
				// 외래키 제약조건으로 인한 삭제 실패 시
				logger.warn("감독 삭제 실패 (외래키 제약조건): {}", e.getMessage());
				logger.info("이는 정상적인 동작입니다. 영화가 있는 감독은 삭제할 수 없습니다.");
			}
		}
		
		// 최종 감독 수 확인
		List<Director> finalDirectors = directorMapper.findAll();
		logger.info("삭제 후 전체 감독 수: {}", finalDirectors.size());
	}
	
	/**
	 * 추가 헬퍼 메서드: 예외 상황 테스트
	 * - 잘못된 데이터로 인한 예외 처리 확인
	 * - 실무에서 자주 발생하는 상황들 테스트
	 */
	@SuppressWarnings("unused")
	 void testExceptionCases() {
		logger.info("\n--- 추가: 예외 상황 테스트 ---");
		
		// NULL 파라미터 테스트
		try {
			Director nullResult = directorMapper.findById(null);
			logger.info("NULL ID 조회 결과: {}", nullResult);
		} catch (Exception e) {
			logger.warn("NULL ID 조회 시 예외 발생: {}", e.getMessage());
		}
		
		// 유효하지 않은 데이터로 등록 시도
		try {
			Director invalidDirector = new Director();
			// 필수 필드 누락
			int result = directorMapper.insert(invalidDirector);
			logger.info("불완전한 데이터 등록 결과: {}", result);
		} catch (Exception e) {
			logger.warn("불완전한 데이터 등록 시 예외 발생: {}", e.getMessage());
		}
	}
}