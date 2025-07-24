package org.kosa.myproject.domain;

public class Point {
    private Integer pointTxId; // 새로운 AUTO_INCREMENT PK
    private Integer memberId;  // Member 테이블의 id를 참조하는 외래 키
    private int point;
    private String pointType;

    public Point() {
        super();
    }

    public Point(Integer pointTxId, Integer memberId, int point, String pointType) {
        super();
        this.pointTxId = pointTxId;
        this.memberId = memberId;
        this.point = point;
        this.pointType = pointType;
    }

    public Integer getPointTxId() {
        return pointTxId;
    }

    public void setPointTxId(Integer pointTxId) {
        this.pointTxId = pointTxId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    @Override
    public String toString() {
        return "Point [pointTxId=" + pointTxId + ", memberId=" + memberId + ", point=" + point + ", pointType=" + pointType + "]";
    }
}
