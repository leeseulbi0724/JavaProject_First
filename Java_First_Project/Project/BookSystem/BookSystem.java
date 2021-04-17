package BookSystem;

import java.util.ArrayList;

import BookDAO.BookDAO;
import BookDAO.MemberDAO;
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
		public ArrayList<BookVO> Admin_Search(String text, String name) {
			return bdao.getResult(text, name);
		}
		
		/** ����� - ���������� - ��ٱ��� ���� �ҷ�����**/
		public ArrayList<BookVO> getBookList(String name) {
			return bdao.getResultBasket(name);
		}
		
		/** ����� - ��ٱ��� ��� **/
		public boolean User_Basket(BookVO vo, String name) {
			return bdao.getResult(vo, name);
		}
			
//			/** �ֹ����� �������� **/
//			public ArrayList<BookVO> Admin_Count(String name) {
//				System.out.println(bdao.getCount(name));
//				System.out.println("22");
//				return bdao.getCount(name);
//			}

}
