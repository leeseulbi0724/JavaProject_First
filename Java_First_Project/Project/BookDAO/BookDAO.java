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
			String sql = "Insert Into BOOK_DATA values(?,?,?,?,?,?,SYSDATE)";
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
	

	/** ����� - ���� ��õ���� �ؽ�Ʈ �޾ƿ��� **/
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
	
	
	/** ������, ����� - ����ȭ��, ����ȭ�� (������Ϻҷ�����) **/
	public ArrayList<BookVO> getResultSelect() {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {
			String sql = " Select BNO,BOOKNAME,AUTHOR,PBLSH,PRICE,PBLSHDATE"
					 			+ " from BOOK_DATA" + " ORDER BY SDATE DESC";			
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookVO vo = new BookVO();
				vo.setBno(rs.getInt(1));
				vo.setBookname(rs.getString(2));
				vo.setAuthor(rs.getString(3));
				vo.setPblsh(rs.getString(4));
				vo.setPrice(rs.getInt(5));
				vo.setPblshdate(rs.getString(6));				
				list.add(vo);			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/** ������ - �������� **/
	public boolean getResultDelete(String name) {
		boolean result = false;		
		try {
			String sql = " DELETE FROM BOOK_DATA WHERE BOOKNAME = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, name);			
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/** ������ - ������ȸ **/
	public ArrayList<BookVO> getResult(String text, String name) {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		String sql;
		try {
			if (name == "������") {
				sql = "SELECT BNO,BOOKNAME,AUTHOR,PBLSH,PRICE,PBLSHDATE FROM BOOK_DATA WHERE BOOKNAME = ? ";
			} else {
				sql = "SELECT BNO,BOOKNAME,AUTHOR,PBLSH,PRICE,PBLSHDATE FROM BOOK_DATA WHERE BNO = ? ";
			}			
			getPreparedStatement(sql);
			System.out.println(name);
			pstmt.setString(1, text);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				BookVO vo = new BookVO();
				vo.setBno(rs.getInt(1));
				vo.setBookname(rs.getString(2));
				vo.setAuthor(rs.getString(3));
				vo.setPblsh(rs.getString(4));
				vo.setPrice(rs.getInt(5));
				vo.setPblshdate(rs.getString(6));				
				list.add(vo);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	
	/** ����� - ���������� - ��ٱ�����ȸ **/
	public ArrayList<BookVO> getResultBasket(String name) {
		ArrayList<BookVO> booklist = new ArrayList<BookVO>();
		
		try {
			String sql = " select bookname, price, count from BOOK_USER_BASKET where id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookname(rs.getString(1));
				book.setPrice(rs.getInt(2));
				book.setCount(rs.getInt(3));
				
				booklist.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booklist;
	}
	
	
	/** ����� - ��ٱ��� ��� **/
	public boolean getResult(BookVO vo, String name) {
		boolean result = false;
		
		try {
			String sql = "INSERT INTO BOOK_USER_BASKET VALUES (?,?,?,?) ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, vo.getBookname());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setInt(4, vo.getCount());
			
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	/** ���� �������� **/
//	public ArrayList<BookVO> getCount(String name) {
//		ArrayList<BookVO> list = new ArrayList<BookVO>();
//		try {
//			String sql = " SELECT BOOKNAME, COUNT(BOOKNAME)" +
//					" FROM BOOK_USER_ORDER"  +
//					" WHERE BOOKNAME = ?"
//					+ " GROUP BY BOOKNAME";
//			getPreparedStatement(sql);					
//			pstmt.setString(1, name);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				BookVO vo = new BookVO();
//				vo.setBookname(rs.getString(1));
//				vo.setCount(rs.getInt(2));					
//				list.add(vo);
//			}		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return list;
//	}

	
	public void close() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
