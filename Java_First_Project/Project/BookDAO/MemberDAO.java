package BookDAO;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import BookUI.Admin_MainUI;
import BookUI.Admin_MemberViewsUI;
import BookVO.BookVO;
import BookVO.MemberVO;
import Commons.Commons;

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
	
	/** ȸ���˻� **/
	public ArrayList<MemberVO> search(String category, String value) {
	ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			String sql = " SELECT ROWNUM RNO, ID, PASS, NAME, BIRTHDAY, HP, ADDR  "
					+ " FROM BOOK_USERS WHERE ";
		
			if(category.equals("���̵�")) {
				sql = sql + " ID = ?";
			}else if(category.equals("�̸�")) {				
				sql = sql + " NAME = ?";
			}
			
			getPreparedStatement(sql);
			pstmt.setString(1, value);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setRno(rs.getInt(1));
				member.setId(rs.getString(2));
				member.setPass(rs.getString(3));
				member.setName(rs.getString(4));
				member.setBirthday(rs.getString(5));
				member.setHp(rs.getString(6));
				member.setAddr(rs.getString(7));
				list.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/** ������ ȸ����ȸ - ȸ����� �������� **/
	public ArrayList<MemberVO> getResultSelect(){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			
		try {
			String sql = " SELECT ROWNUM RNO, ID, PASS, NAME, BIRTHDAY, HP, ADDR " 
						+ " FROM (SELECT ID,PASS,NAME,BIRTHDAY,HP,ADDR FROM BOOK_USERS ORDER BY ID DESC) WHERE ID != '������' AND ID != 'admin'  ";
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setRno(rs.getInt(1));
				member.setId(rs.getString(2));
				member.setPass(rs.getString(3));
				member.setName(rs.getString(4));
				member.setBirthday(rs.getString(5));
				member.setHp(rs.getString(6));
				member.setAddr(rs.getString(7));
				list.add(member);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return list;
	}
	
	/** ������ - ȸ������ **/
	public boolean delete(String id) {
		boolean result = false;		
		try {
			String sql = " DELETE FROM BOOK_USERS WHERE ID = ?  ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);			
			int val = pstmt.executeUpdate();		
			if (val != 0) {
				result = true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("������ �� ���� ȸ���Դϴ�."));
		} 
		return result;
	}
	
	/** ȸ������ - ID �ߺ�üũ **/
	public boolean CheckID(String id) {
		
		try {
			String sql = " select count(*) cnt from book_users where id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				if(cnt>0) {
					return true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/** ��й�ȣ ã�� ���� ���� **/
	public boolean CheckInfo(String name, String birthday, String id) {
		boolean result = false;
		
		try {
			String sql = " select count(*) cnt from book_users where name = ? and birthday = ? and id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birthday);
			pstmt.setString(3, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cnt = rs.getInt(1);
				System.out.println(cnt);
				if(cnt>0) {
					result =  true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/** ��й�ȣ ã�� �ӽú�й�ȣ�� ���� **/
	public boolean getUpdatePassResult(String pass, String name, String birthday, String id) {
		boolean result = false;
		
		try {
			String sql = " update book_users set pass = ? where name = ? and birthday = ? and id = ? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, birthday);
			pstmt.setString(4, id);
			
			int val = pstmt.executeUpdate();
			if(val != 0 ) result = true;
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	/** ���������� - ��й�ȣ Ȯ�� **/
	public boolean CheckPass(String pass,String id) {
		
		try {
			String sql = " select count(*) cnt from book_users where pass = ? and id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				if(cnt>0) {
					return true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/** ���������� - ȸ������ �������� **/
	public MemberVO MemberInfo(String id) {
		MemberVO member = new MemberVO();
		try {
			String sql = " select name, id, birthday, hp, addr from book_users where id = ?  ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				member.setName(rs.getString(1));
				member.setId(rs.getString(2));
				member.setBirthday(rs.getString(3));
				member.setHp(rs.getString(4));
				member.setAddr(rs.getString(5));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
		
		
	}

	/** User_MyPage_Usermodify (����� - ȸ����������) **/
	public int getModifyResult(MemberVO member,String id) {
		// id ���ؼ� ������ ��й�ȣ, ����ȣ, �ּ� ����
		int result = 0;
		
		try {
			
			String sql =  " update book_users "
					+ " set pass = ?, hp=?, addr=? "
					+ " where id =?"; //id ��? pass ��?
			getPreparedStatement(sql);
			pstmt.setString(1, member.getPass());
			pstmt.setString(2, member.getHp());
			pstmt.setString(3, member.getAddr());
			pstmt.setString(4, id);
			
		
			int val = pstmt.executeUpdate();
			
			
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
