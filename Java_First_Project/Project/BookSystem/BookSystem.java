package BookSystem;

import java.util.ArrayList;

import BookDAO.BookDAO;
import BookDAO.MemberDAO;
import BookVO.BoardVO;
import BookVO.BookVO;
import BookVO.MemberVO;

public class BookSystem {
		//Field
		MemberDAO mdao = new MemberDAO();			
		BookDAO bdao = new BookDAO();
		
		//Constructor
		public BookSystem() {			

		}
		
		//Method
		/** ������ - ������� **/
		public boolean Admin_Insert(BookVO vo) {				
			return bdao.getResultInsert(vo);
		}
		
		/** �α��� **/
		public boolean loginCheck(String id, String pass) {
			return mdao.getLoginResult(id,pass);
		}
		
		/** ȸ������ ó�� **/
		public boolean join(MemberVO member) {
				return mdao.getJoinResult(member);
		}
			
		/** ����� - ���������� - �������� **/
		public boolean User_MyPage_Modify(MemberVO vo) {
			return mdao.getModifyResult(vo);
		}
		
		/** ������ - ȸ����ȸ (ȸ����� ��������) **/
		public ArrayList<MemberVO> Admin_MemberSelect(){
				return mdao.getResultSelect();
		}		
		
		/** ������ - ����ȭ��, ����ȭ�� (������ϰ�������) **/
		public ArrayList<BookVO> Admin_Select() {
			return bdao.getResultSelect();				
		}
		
		/** ������ - �������� **/
		public boolean Admin_Delete(String name) {
			return bdao.getResultDelete(name);
		}
		
		/** ������ - �����˻� **/
		public ArrayList<BookVO> Admin_Search(String text) {
			return bdao.getResult(text);
		}
		
		/** ����� - ���������� - ��ٱ��� ���� �ҷ�����**/
		public ArrayList<BookVO> getBookList(String name) {
			return bdao.getResultBasket(name);
		}
		
		/** ����� - ��ٱ��� ��� **/
		public boolean User_Basket(BookVO vo, String name, boolean result) {
			return bdao.getResult(vo, name, result);
		}
		
		/** ����� - ��ٱ��� ��� �� �̹� ��� �ִ� �������� Ȯ�� **/
		public boolean Basket_check(BookVO vo, String name) {
			return bdao.getBasketCheck(vo, name);
		}
		
		/** ����� - �Խ��� ���� ���� **/
		public boolean User_Board(BoardVO vo) {
			return bdao.getBoardInsert(vo);
		}
		
		/** �����, ������ - �Խ��� ��� �ҷ����� **/
		public ArrayList<BoardVO> board_data() {
			return bdao.getBoardSelect();
		}
		
		/** ����� - �Խ��� Ŭ�� �� �ش� �� �������� **/
		public BoardVO board_result(String content) {			
			return bdao.getBoardResult(content);			
		}
		
		/** ������ - �������� �� DB�� �ִ� ���������� üũ�ϱ� **/
		public ArrayList<BookVO> Book_Equals() {
			return bdao.getBookEquals();
		}
		
		/** ����� - ���������� - �ֹ���ȸ (select) **/
		public ArrayList<BookVO> getOrderList(String name) {
			return bdao.getResultOrder(name);
		}
		
		/** ����� - ��������� - �ֹ������� �ѱ�� (insert) **/
		public boolean User_Order(BookVO vo, String name) {
			return bdao.getOrder(vo, name);
		}
		
		/** ����� - �����ۼ� DB ���� **/
		public boolean User_ReviewResult(BoardVO vo) {
			return bdao.getReviewResult(vo);
		}
		
		/** ����� -- ���� �ۼ� �� �̹� �ۼ��� ���䰡 �ִ��� üũ **/
		public boolean ReviewCheck(String bookname, String username) {
			return bdao.getReviewCheck(bookname, username);
		}
		
		/** ����� - ��� �������� �������� **/
		public ArrayList<BoardVO> All_Review(String bookname) {
			return bdao.getAllReview(bookname);
		}
		
		/** ����� - ���������� ����ȭ�� ��й�ȣ Ȯ�� �� ȭ�� ��ȯ�Ҷ� ��й�ȣ �������� **/
		public boolean PassCheck(String name, String pass) {
			return mdao.getPassCheck(name, pass);
		}
			
//			/** �ֹ����� �������� **/
//			public ArrayList<BookVO> Admin_Count(String name) {
//				System.out.println(bdao.getCount(name));
//				System.out.println("22");
//				return bdao.getCount(name);
//			}

}
