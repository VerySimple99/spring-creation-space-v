package org.kosta.myproject.model.vo;

public class MovieVO {
	private long movieId; // 자동 매핑
	private String title;
	private String genre;
	private long attendance;
	// has a 
	private DirectorVO directorVO;
	public MovieVO() {
		super();
	}
	public MovieVO(String title, String genre, long attendance, DirectorVO directorVO) {
		super();
		this.title = title;
		this.genre = genre;
		this.attendance = attendance;
		this.directorVO = directorVO;
	}
	public MovieVO(long movieId, String title, String genre, long attendance, DirectorVO directorVO) {
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
	public DirectorVO getDirectorVO() {
		return directorVO;
	}
	public void setDirectorVO(DirectorVO directorVO) {
		this.directorVO = directorVO;
	}
	@Override
	public String toString() {
		return "MovieVO [movieId=" + movieId + ", title=" + title + ", genre=" + genre + ", attendance=" + attendance
				+ ", directorVO=" + directorVO + "]";
	}
	
}
