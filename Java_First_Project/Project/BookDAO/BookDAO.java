package BookDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import BookVO.BookVO;

public class BookDAO extends DBConn {
	
	/** ������ - ���� ��� **/
	public boolean getResultInsert(BookVO vo) {
		boolean result = false;
		try {
			String sql = "Insert Into BOOK_DATA values(?,?,?,?,?,?)";
			getPreparedStatement(sql);
			
			pstmt.setInt(1, vo.getBno());
			pstmt.setString(2, vo.getBookname());
			pstmt.setString(3, vo.getAuthor());
			pstmt.setString(4, vo.getPblsh());
			pstmt.setInt(5, vo.getPrice());
			pstmt.setString(6, vo.getPblshdate());
			
			int val = pstmt.executeUpdate();
			if (val !=0 ) {
				result = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	//��õ���� ������ �ؽ�Ʈ�ʵ忡�� �ߴ� ������ �޾ƿ��� ��
	public String[][] recommendList() {
		
		try {
			String sql = "SELECT*FROM BOOK_DATA WHERE BOOKNAME='����ó�����'";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet result = st.executeQuery();
			
			ArrayList<String[]> list = new ArrayList<String[]>();
			
			while(result.next()) {
				list.add(new String[] {
				String.valueOf(result.getInt("BNO")),
				result.getString("BOOKNAME"),
				result.getString("AUTHOR"),
				result.getString("PBLSH"),
				String.valueOf(result.getInt("PRICE")),
				result.getString("PBLSHDATE")
				});
			}
			
			String[][] array = new String[list.size()][6];
			return list.toArray(array);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
