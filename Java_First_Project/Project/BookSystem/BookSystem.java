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
			
			/** ����� - ���������� - �������� **/
			public boolean User_MyPage_Modify(MemberVO vo) {
				return mdao.getModifyResult(vo);
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
			
			/** ����� - ���������� - ��ٱ��� **/
			public ArrayList<BookVO> getBookList() {
				return bdao.getResultBasket();
			}
			
//			/** �ֹ����� �������� **/
//			public ArrayList<BookVO> Admin_Count(String name) {
//				System.out.println(bdao.getCount(name));
//				System.out.println("22");
//				return bdao.getCount(name);
//			}
}
