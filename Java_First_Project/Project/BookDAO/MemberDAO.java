package BookDAO;

import BookVO.MemberVO;

public class MemberDAO extends DBConn{
	
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
}
