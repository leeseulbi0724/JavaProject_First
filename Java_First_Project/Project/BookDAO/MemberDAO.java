package BookDAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import BookVO.MemberVO;

public class MemberDAO extends DBConn {
		
	/** �α��� ó�� **/
	public boolean getLoginResult(String id, String pass) {
		boolean result = false;
		
		try {
			String sql = " select count(*) from book_users where id=? and pass=? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)==1) result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/** ȸ������ ó�� **/
	public boolean getJoinResult(MemberVO member) {
		boolean result = false;
		
		try {
			String sql = " insert into book_users values(?,?,?,?,?,?,sysdate) ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirthday());
			pstmt.setString(5, member.getHp());
			pstmt.setString(6, member.getAddr());
			
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** ������ ȸ����ȸ - ȸ����� �������� **/
	public ArrayList<MemberVO> getResultSelect(){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			
		try {
			String sql = " select id,pass,name,birthday,hp,addr from book_users ";
			getPreparedStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getString(1));
				member.setPass(rs.getString(2));
				member.setName(rs.getString(3));
				member.setBirthday(rs.getString(4));
				member.setHp(rs.getString(5));
				member.setAddr(rs.getString(6));
				list.add(member);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return list;
	}

	/** User_MyPage_Usermodify (����� - ȸ����������) **/
	public boolean getModifyResult(MemberVO member) {
		// id ���ؼ� ������ ��й�ȣ, ����ȣ, �ּ� ����
		boolean result = false;
		
		try {
			String sql = " update book_users set pass = ?, hp = ?, addr = ? where id = ? "; //id ��? pass ��?
			getPreparedStatement(sql);
			
			pstmt.setString(1, member.getPass());
			pstmt.setString(2, member.getHp());
			pstmt.setString(3, member.getAddr());
			pstmt.setString(4, member.getId());
			
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(pstmt !=  null) pstmt.close();
			if(conn != null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
