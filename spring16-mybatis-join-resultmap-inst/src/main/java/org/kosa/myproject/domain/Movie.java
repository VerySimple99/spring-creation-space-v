package org.kosa.myproject.domain;

/**
 * 영화 정보를 담는 도메인 모델
 * 
 * 핵심 개념: Association Mapping
 * - Movie와 Director 간의 다대일(N:1) 관계 표현
 * - 하나의 감독은 여러 영화를 만들 수 있음
 * - 하나의 영화는 하나의 감독을 가짐
 */
public class Movie {
    
    // 영화 고유 식별자
    private Long movieId;
    
    // 영화 제목 - 비즈니스에서 가장 중요한 식별 정보
    private String title;
    
    // 장르 - 영화의 분류 (드라마, 액션, 코미디 등)
    private String genre;
    
    // 관객수 - 영화의 흥행 성과를 나타내는 중요 지표
    private Long attendance;
    
    // 감독 정보 - Association (연관관계)
    // Has-A 관계: Movie has a Director
    private Director director;  //  'director'가 더 명확
   
    
    // 기본 생성자 - MyBatis가 객체를 생성할 때 필요
    public Movie() {
        super();
    }
    
    // 비즈니스 생성자 - 새로운 영화 등록시 사용 (ID 제외)
    public Movie(String title, String genre, Long attendance, Director director) {
        super();
        this.title = title;
        this.genre = genre;
        this.attendance = attendance;
        this.director = director;
    }
    
    // 전체 필드 생성자 - 완전한 객체 생성시 사용
    public Movie(Long movieId, String title, String genre, Long attendance, Director director) {
        super();
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.attendance = attendance;
        this.director = director;
    }
    
    // === Getter & Setter 메서드들 ===
    
    public Long getMovieId() {
        return movieId;
    }
    
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public Long getAttendance() {
        return attendance;
    }
    
    public void setAttendance(Long attendance) {
        this.attendance = attendance;
    }
    
    public Director getDirector() {
        return director;
    }
    
    public void setDirector(Director director) {
        this.director = director;
    }

    
    // === 비즈니스 메서드들 ===
    
    /**
     * 영화 제목의 유효성을 검증하는 비즈니스 로직
     * @return 유효하면 true, 아니면 false
     */
    public boolean isValidTitle() {
        return title != null && 
               !title.trim().isEmpty() && 
               title.length() <= 100;
    }
    
    /**
     * 관객수가 흥행작인지 판단하는 비즈니스 로직
     * @return 1000만 이상이면 true (대흥행)
     */
    public boolean isBlockbuster() {
        return attendance != null && attendance >= 10_000_000L;
    }
    
    /**
     * 영화의 흥행 등급을 반환하는 비즈니스 로직
     * @return 흥행 등급 문자열
     */
    public String getBoxOfficeGrade() {
        if (attendance == null) return "정보없음";
        if (attendance >= 10_000_000L) return "대흥행";
        if (attendance >= 5_000_000L) return "흥행";
        if (attendance >= 1_000_000L) return "보통";
        return "저조";
    }
    
    /**
     * 감독명을 포함한 영화 정보를 반환하는 편의 메서드
     * @return "영화제목 (감독명)" 형식
     */
    public String getTitleWithDirector() {
        if (director != null && director.getDirectorName() != null) {
            return title + " (" + director.getDirectorName() + ")";
        }
        return title;
    }

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", genre=" + genre + ", attendance=" + attendance
				+ ", director=" + director + "]";
	}
    
    
}
