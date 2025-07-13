package org.kosta.myproject.model.vo;

public class DirectorVO {
	private long directorId;// column name : director__id => 설정으로 자동 매핑 
	private String directorName;// 자동 매핑 
	private String intro;
	public DirectorVO() {
		super();		
	}
	public DirectorVO(String directorName, String intro) {
		super();
		this.directorName = directorName;
		this.intro = intro;
	}
	public DirectorVO(long directorId, String directorName, String intro) {
		super();
		this.directorId = directorId;
		this.directorName = directorName;
		this.intro = intro;
	}
	public long getDirectorId() {
		return directorId;
	}
	public void setDirectorId(long directorId) {
		this.directorId = directorId;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Override
	public String toString() {
		return "DirectorVO [directorId=" + directorId + ", directorName=" + directorName + ", intro=" + intro + "]";
	}
	
}
