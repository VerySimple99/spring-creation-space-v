package org.kosa.myproject.domain;

public class Movie {
	private long movieId; // 자동 매핑
	private String title;
	private String genre;
	private long attendance;
	// has a 
	private Director directorVO;
	public Movie() {
		super();
	}
	public Movie(String title, String genre, long attendance, Director directorVO) {
		super();
		this.title = title;
		this.genre = genre;
		this.attendance = attendance;
		this.directorVO = directorVO;
	}
	public Movie(long movieId, String title, String genre, long attendance, Director directorVO) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.genre = genre;
		this.attendance = attendance;
		this.directorVO = directorVO;
	}
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
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
	public long getAttendance() {
		return attendance;
	}
	public void setAttendance(long attendance) {
		this.attendance = attendance;
	}
	public Director getDirectorVO() {
		return directorVO;
	}
	public void setDirectorVO(Director directorVO) {
		this.directorVO = directorVO;
	}
	@Override
	public String toString() {
		return "MovieVO [movieId=" + movieId + ", title=" + title + ", genre=" + genre + ", attendance=" + attendance
				+ ", directorVO=" + directorVO + "]";
	}
	
}
