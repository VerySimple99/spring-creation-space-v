package org.kosa.myproject;

import java.util.List;

import org.kosa.myproject.domain.Director;
import org.kosa.myproject.domain.Movie;
import org.kosa.myproject.mapper.MovieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * MovieMapper JOIN 쿼리 동작 테스트를 위한 실행 클래스
 * 
 * 핵심 학습 목표:
 * 1. MyBatis ResultMap을 통한 중첩 객체 매핑 이해
 * 2. INNER JOIN 쿼리의 실제 동작 확인
 * 3. Movie 객체 내부의 Director 객체 자동 생성 과정 체험
 * 
 * 실행 순서: DirectorMapper 테스트 후 실행 권장
 */
@Component
@Order(2)
public class MyBatisStudyMovieRunner implements CommandLineRunner {
	
	 static final Logger logger = LoggerFactory.getLogger(MyBatisStudyMovieRunner.class);
	
	// MovieMapper 의존성 주입
	@Autowired
	 MovieMapper movieMapper;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("=================================================");
		logger.info("MovieMapper JOIN 쿼리 테스트 시작");
		logger.info("=================================================");
		
		try {
			// 1단계: 기본 집계 쿼리 테스트
	//		testCount();
			
			// 2단계: JOIN 쿼리 테스트 (핵심!)
	//	testFindById();
	//	testFindAll();
//			
//			
//			// 3단계: 비즈니스 로직 활용 테스트
//			testBusinessLogic();
			
		} catch (Exception e) {
			logger.error("MovieMapper 테스트 실행 중 오류 발생: {}", e.getMessage(), e);
			throw e;
		}
		
		logger.info("=================================================");
		logger.info("MovieMapper JOIN 쿼리 테스트 완료");
		logger.info("=================================================");
	}
	
	/**
	 * 1. 전체 영화 수 조회 테스트
	 * - 단순 집계 쿼리 동작 확인
	 * - JOIN 없는 기본 쿼리 vs JOIN 쿼리 성능 비교 기준
	 */
	 void testCount() {
		logger.info("\n--- 1. 전체 영화 수 조회 테스트 ---");
		
		long totalMovies = movieMapper.count();
		
		logger.info("데이터베이스에 저장된 총 영화 수: {}", totalMovies);
		
		if (totalMovies > 0) {
			logger.info("✅ 영화 데이터가 존재합니다. JOIN 쿼리 테스트를 진행합니다.");
		} else {
			logger.warn("⚠️ 영화 데이터가 없습니다. movie-director.sql을 먼저 실행해주세요.");
		}
	}
	
	/**
	 * 2. 특정 영화 조회 테스트 (JOIN 쿼리)
	 * - ResultMap을 통한 중첩 객체 매핑 확인
	 * - Movie 객체 내부의 Director 객체 자동 생성 검증
	 */
	 void testFindById() {
		logger.info("\n--- 2. 특정 영화 조회 테스트 (JOIN 쿼리) ---");
		
		// 2-1. 존재하는 영화 조회 (ID = 1, 일반적으로 '기생충')
		Long movieId = 1L;
		Movie movie = movieMapper.findById(movieId);
		
		if (movie != null) {
			logger.info("🎬 영화 조회 성공!");
			logger.info("  영화 ID: {}", movie.getMovieId());
			logger.info("  영화 제목: {}", movie.getTitle());
			logger.info("  장르: {}", movie.getGenre());
			logger.info("  관객수: {:,}명", movie.getAttendance());
			
			// 중첩 객체 확인 - 핵심 포인트!
			Director director = movie.getDirector();
			if (director != null) {
				logger.info("🎭 감독 정보 (JOIN으로 조회됨):");
				logger.info("  감독 ID: {}", director.getDirectorId());
				logger.info("  감독명: {}", director.getDirectorName());
				logger.info("  소개: {}", director.getIntro());
				
				// ResultMap 매핑 성공 확인
				logger.info("✅ ResultMap Association 매핑 성공!");
				logger.info("   Movie 객체 안에 Director 객체가 완전히 생성되었습니다.");
			} else {
				logger.error("❌ 감독 정보가 null입니다. ResultMap 설정을 확인해주세요.");
			}
			
		} else {
			logger.warn("영화 ID {}에 해당하는 영화를 찾을 수 없습니다.", movieId);
		}
		
		// 2-2. 존재하지 않는 영화 조회
		Long nonExistingId = 999L;
		Movie nonExistingMovie = movieMapper.findById(nonExistingId);
		
		if (nonExistingMovie == null) {
			logger.info("존재하지 않는 영화 ID {} 조회 결과: null (정상)", nonExistingId);
		}
	}
	
	/**
	 * 3. 모든 영화 목록 조회 테스트 (JOIN 쿼리)
	 * - List<Movie> 형태의 복잡한 결과 처리
	 * - 여러 영화의 감독 정보가 모두 포함되는지 확인
	 */
	 void testFindAll() {
		logger.info("\n--- 3. 모든 영화 목록 조회 테스트 (JOIN 쿼리) ---");
		
		List<Movie> movies = movieMapper.findAll();
		
		logger.info("조회된 영화 목록 (감독 정보 포함): {} 건", movies.size());
		logger.info("");
		
		// 각 영화와 감독 정보를 상세히 출력
		for (int i = 0; i < movies.size(); i++) {
			Movie movie = movies.get(i);
			Director director = movie.getDirector();
			
			logger.info("{}. 🎬 {}", i + 1, movie.getTitle());
			logger.info("   장르: {}, 관객수: {}명", movie.getGenre(), movie.getAttendance());
			
			if (director != null) {
				logger.info("   🎭 감독: {} ({})", director.getDirectorName(), director.getIntro());
			} else {
				logger.warn("   ❌ 감독 정보 누락!");
			}
			logger.info("");
		}
		
		// JOIN 쿼리 결과 검증
		long moviesWithDirector = movies.stream()
			.filter(movie -> movie.getDirector() != null)
			.count();
		
		logger.info("검증 결과:");
		logger.info("  총 영화 수: {}", movies.size());
		logger.info("  감독 정보가 있는 영화 수: {}", moviesWithDirector);
		
		if (moviesWithDirector == movies.size()) {
			logger.info("✅ 모든 영화에 감독 정보가 완벽하게 매핑되었습니다!");
		} else {
			logger.warn("⚠️ 일부 영화의 감독 정보가 누락되었습니다.");
		}
	}
	
	
	/**
	 * 5. 비즈니스 로직 활용 테스트
	 * - Movie 도메인 객체의 비즈니스 메서드 활용
	 * - JOIN으로 조회된 완전한 객체의 실무 활용 패턴
	 */
	 void testBusinessLogic() {
		logger.info("\n--- 5. 비즈니스 로직 활용 테스트 ---");
		
		List<Movie> movies = movieMapper.findAll();
		
		logger.info("📊 영화 분석 결과:");
		logger.info("");
		
		// 흥행 등급별 분류
		long blockbusters = movies.stream()
			.filter(Movie::isBlockbuster)
			.count();
		
		logger.info("🏆 대흥행작 (1000만 관객 이상): {} 편", blockbusters);
		
		// 흥행작 상세 정보
		movies.stream()
			.filter(Movie::isBlockbuster)
			.forEach(movie -> {
				String titleWithDirector = movie.getTitleWithDirector();
				String grade = movie.getBoxOfficeGrade();
				
				logger.info("  - {} [{}]", titleWithDirector, grade);
			});
		
		logger.info("");
		
		// 감독별 평균 관객수 계산 (Java Stream 활용)
		movies.stream()
			.collect(java.util.stream.Collectors.groupingBy(
				movie -> movie.getDirector().getDirectorName(),
				java.util.stream.Collectors.averagingLong(Movie::getAttendance)
			))
			.entrySet()
			.stream()
			.sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
			.forEach(entry -> {
				String directorName = entry.getKey();
				double avgAttendance = entry.getValue();
				
				logger.info("🎭 {} 감독 평균 관객수: {:,.0f}명", directorName, avgAttendance);
			});
		
		logger.info("");
		logger.info("✅ JOIN으로 조회된 완전한 객체를 활용한 비즈니스 로직 테스트 완료!");
	}
	
	/**
	 * 추가: 성능 및 메모리 사용량 간단 체크
	 * - 대용량 데이터에서의 JOIN 쿼리 성능 고려사항
	 */
	@SuppressWarnings("unused")
	 void testPerformanceAspects() {
		logger.info("\n--- 추가: 성능 측면 검토 ---");
		
		long startTime = System.currentTimeMillis();
		
		List<Movie> movies = movieMapper.findAll();
		
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		logger.info("쿼리 실행 시간: {}ms", executionTime);
		logger.info("조회된 데이터: {} 건", movies.size());
		
		if (executionTime > 1000) { // 1초 이상
			logger.warn("⚠️ 쿼리 실행 시간이 깁니다. 인덱스 및 쿼리 최적화를 검토하세요.");
		} else {
			logger.info("✅ 쿼리 성능이 양호합니다.");
		}
		
		// 메모리 사용량 체크 (개발 환경에서만)
		Runtime runtime = Runtime.getRuntime();
		long usedMemory = runtime.totalMemory() - runtime.freeMemory();
		logger.info("현재 메모리 사용량: {:,} bytes", usedMemory);
	}
}