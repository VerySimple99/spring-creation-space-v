package org.kosta.myproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MemberDAOImpl implements MemberDAO {
	private final DataSource dataSource;
	// 스프링 컨테이너(IOC Container)가 생성자 호출 시점에  datasource 구현체 dbcp => dependency injection 한다
	@Autowired // DI 
	public MemberDAOImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		System.out.println(getClass().getName()+" 생성자 실행, 객체 생성시 dbcp 주입받음 "+dataSource);
	}

	@Override
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close(); // 컨넥션을 컨넥션풀에 반납한다 
	}

	@Override
	public MemberVO findMemberById(String id) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		try {
			con=dataSource.getConnection();//컨넥션을 dbcp로부터 빌려온다 
			String sql="select id,password,name,address from spring_member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				memberVO=new MemberVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return memberVO;
	}
}



















