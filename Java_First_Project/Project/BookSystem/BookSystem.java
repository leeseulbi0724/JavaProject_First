package BookSystem;

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
			
}
