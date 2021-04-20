package BookUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import BookDAO.MemberDAO;
import BookSystem.BookSystem;
import BookVO.MemberVO;
import Commons.Commons;

public class FindPassUIEvent implements ActionListener {
	FindPassUI main;
	BookSystem system;
	MemberDAO mdao = new MemberDAO();
	
	public FindPassUIEvent(FindPassUI main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//��й�ȣ ���� �۾�
		if(obj == main.temp_check_btn) {
			if(mdao.getUpdatePassResult(main.temp_pass_tf.getText(), main.sign_name_tf.getText(), main.sign_birthday_tf.getText()
					, main.sign_id_tf.getText())) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("��й�ȣ�� ����Ǿ����ϴ�."));
				main.temp_panel.setVisible(false);
				main.find_panel.setVisible(false);
			}

		}
		
	}
	
	
	
}
