package BookDAO;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import BookVO.BoardVO;
import BookVO.BookVO;
import Commons.Commons;

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
	public String[][] recommendList(boolean flag) {		
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql;
		
		try {
			if(flag==false) sql = "SELECT*FROM BOOK_DATA WHERE BOOKNAME='����ó�����'";	
			else if(flag==true) sql = "SELECT*FROM BOOK_DATA WHERE BOOKNAME='����ǿµ�'";	
			else sql = null;
			
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new String[] {
				String.valueOf(rs.getInt("BNO")),
				rs.getString("BOOKNAME"),
				rs.getString("AUTHOR"),
				rs.getString("PBLSH"),
				String.valueOf(rs.getInt("PRICE")),
				rs.getString("PBLSHDATE")
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
			JOptionPane.showMessageDialog(null, Commons.getMsg("�����Ͻ� �� ���� �����Դϴ�."));
		}		
		return result;
	}
	
	public ArrayList<BookVO> getBookEquals() {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {
			String sql = "SELECT BOOKNAME FROM BOOK_DATA";
			getPreparedStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookname(rs.getString(1));
				
				list.add(vo);
			}
		} catch (Exception e) {
			
		}
		return list;
		
	}
	
	/** ������ - ������ȸ **/
	public ArrayList<BookVO> getResult(String text) {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		String sql;
		try {		
			sql = "SELECT BNO,BOOKNAME,AUTHOR,PBLSH,PRICE,PBLSHDATE FROM BOOK_DATA WHERE BOOKNAME = ? ";			
			getPreparedStatement(sql);
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
	public boolean getResult(BookVO vo, String name, boolean check_result) {
		boolean result = false;
		boolean result_2 = check_result;		
		try {
			if (result_2) {				
				String sql = "UPDATE BOOK_USER_BASKET SET COUNT = COUNT+? WHERE BOOKNAME = ? AND ID = ?";
				getPreparedStatement(sql);
				pstmt.setInt(1, vo.getCount());
				pstmt.setString(2, vo.getBookname());
				pstmt.setString(3, name);
			} else {
				String sql = "INSERT INTO BOOK_USER_BASKET VALUES (?,?,?,?) ";
				getPreparedStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, vo.getBookname());
				pstmt.setInt(3, vo.getPrice());
				pstmt.setInt(4, vo.getCount());
			}						
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/** ����� - ��ٱ��� ����� �̹� ����ִ� �������� üũ **/
	public boolean getBasketCheck(BookVO vo, String name) {
		boolean result = false;
		
		try {
			String sql = " SELECT BOOKNAME FROM BOOK_USER_BASKET WHERE BOOKNAME = ? AND ID = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getBookname());
			pstmt.setString(2, name);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookname(rs.getString(1));			
				if (book != null) {
					result = true;
				}
			}
		} catch (Exception e) {
			
		}
		return result;
	}
	
	/** ����� - �Խ��� DB���� **/
	public boolean getBoardInsert(BoardVO vo) {
		boolean result = false;
		
		try {
			String sql = " INSERT INTO BOOK_BOARD VALUES (?,?,?,?,SYSDATE)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getId());
			
			int val = pstmt.executeUpdate();
			if (val != 0 ) {
				result = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/** �����, ������ - �Խ��� ��� �ҷ����� **/
	public ArrayList<BoardVO> getBoardSelect() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
		String sql = " SELECT ROWNUM, CATEGORY, TITLE, CONTENT, ID, WDATE FROM"
				+ " (SELECT CATEGORY, TITLE, CONTENT, ID, WDATE FROM BOOK_BOARD ORDER BY WDATE)"
				+ " ORDER BY ROWNUM DESC";
		getPreparedStatement(sql);		
		rs = pstmt.executeQuery();
		while (rs.next()) {
			BoardVO vo = new BoardVO();
			vo.setRownum(rs.getInt(1));
			vo.setCategory(rs.getString(2));
			vo.setTitle(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setId(rs.getString(5));
			vo.setDate(rs.getString(6));
			
			list.add(vo);
		}
		
		} catch (Exception e) {
			
		}
		return list;
	}
	
	/** ����� - �Խ��� Ŭ�� �� �ش� �Խ��� ���� �������� **/
	public BoardVO getBoardResult(String content) {
		BoardVO vo = null;
		
		try {
			String sql = "SELECT TITLE, CONTENT FROM BOOK_BOARD WHERE CONTENT = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, content);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));		
				
			}
			
		} catch (Exception e) {
			
		}			
		return vo;
		
	}
	
	
	/** ����� - ���������� - �ֹ���ȸ  **/
	public ArrayList<BookVO> getResultOrder(String name) {
		ArrayList<BookVO> booklist = new ArrayList<BookVO>();
		
		try {
			String sql = " select bookname, author, pblsh, price, orderdate "
					+ " from BOOK_USER_ORDER  where id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookVO book = new BookVO();
				
				book.setBookname(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setPblsh(rs.getString(3));
				book.setPrice(rs.getInt(4));
				book.setPblshdate(rs.getString(5));
				
				booklist.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return booklist;
	}
	
	/** ����� - ��������� - �ֹ������� �ѱ�� **/
	public boolean getOrder(String id, BookVO vo) {
		boolean result = false;
		
		try {
			String sql = " insert into BOOK_USER_ORDER values(?,?,?,?,?, sysdate, ?) "; 
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);		//id�޾Ƽ� �ֱ�
			pstmt.setString(2, vo.getBookname());
			pstmt.setString(3, vo.getAuthor());
			pstmt.setString(4, vo.getPblsh());
			pstmt.setInt(5, vo.getPrice());
			pstmt.setInt(6, vo.getCount());
			
			int val = pstmt.executeUpdate();
			if (val != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** ��ٱ��� ���̺� -> �ֹ���ȸ ���̺��� å ���� ������ **/
	public ArrayList<BookVO> getResultBookinfo(String bookname) {
		ArrayList<BookVO> booklist = new ArrayList<BookVO>();
		
		try {
			String sql = " select bookname, author, pblsh, price from book_data where bookname = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, bookname);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookname(rs.getString(1));
				vo.setAuthor(rs.getString(2));
				vo.setPblsh(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				
				booklist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return booklist;
	}
	
	/** ����� - ���� ��� DB���� **/
	public boolean getReviewResult(BoardVO vo) {
		boolean result = false;
		try {
			String sql = " INSERT INTO BOOK_REVIEW VALUES(?,?,?,?,SYSDATE)";
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setInt(4, vo.getScore());
			
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			
		}
		return result;
	}
	
	/** ����� - ���� �ۼ� �� �̹� �ۼ��� ���䰡 �ִ��� üũ **/
	public boolean getReviewCheck(String bookname, String username) {
		boolean result = true;
			try {
				String sql = " SELECT BOOKNAME FROM BOOK_REVIEW WHERE BOOKNAME = ? AND ID = ?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, bookname);
				pstmt.setString(2, username);
				
				rs = pstmt.executeQuery();		
				while (rs.next()) {
					BoardVO vo = new BoardVO();
					vo.setTitle(rs.getString(1));
					if (vo != null) {
						result = false;
					}
				}
				
			} catch (Exception e) {
				
			}
			
			return result;
			
	}
	
	/** ����� - ��� ���� �������� **/
	public ArrayList<BoardVO> getAllReview(String bookname) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			String sql = " SELECT ID, SCORE, CONTENT FROM BOOK_REVIEW WHERE BOOKNAME = ?";
			getPreparedStatement(sql);
			
			pstmt.setString(1, bookname);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setId(rs.getString(1));
				vo.setScore(rs.getInt(2));
				vo.setContent(rs.getString(3));
				
				list.add(vo);
			}
		} catch (Exception e) {
			
		}
		
		return list;
	}
	
	/** ����� - ���������� - My�̷� (���� �� ��� �Խ��Ǳ�)  **/
 	public ArrayList<BoardVO> getMyboard(String id) {
 		ArrayList<BoardVO> writelist = new ArrayList<BoardVO>();
		
		try {
			String sql = " SELECT CATEGORY, TITLE, CONTENT, WDATE FROM BOOK_BOARD WHERE ID = ? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setCategory(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setDate(rs.getString(4));
				
				writelist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return writelist;
 	}
 	
 	/** ����� - ���������� - My�̷� (���� �� ��� �����)  **/
 	public ArrayList<BoardVO> getMyreview(String id) {
 		ArrayList<BoardVO> writelist = new ArrayList<BoardVO>();
		
		try {
			String sql = " SELECT BOOKNAME, CONTENT, RDATE FROM BOOK_REVIEW WHERE ID = ? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setCategory("����");
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));
				vo.setDate(rs.getString(3));
				
				writelist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return writelist;
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
