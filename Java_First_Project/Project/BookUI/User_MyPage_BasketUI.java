package BookUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Commons.Commons;

public class User_MyPage_BasketUI {

	JFrame frame;
	User_MyPageUI main;
	JPanel content_panel, btn_panel;
	private JTextField textField;

	
	public User_MyPage_BasketUI(User_MyPageUI main) {
		this.main = main;
		this.frame = main.frame;
		init();
	}

	
	public void init() {
		main.switching(User_MyPageUI.Basket);
		
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.info);
		content_panel.setBounds(0, 0, 531, 301);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		String[] colName = {"������", "��������", "����"};
		DefaultTableModel model = new DefaultTableModel(colName, 0);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JTable board_table = new JTable(model);
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);
		
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn_order = new JButton("�ֹ��ϱ�");
		btn_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btn_order)) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("�ֹ� �Ϸ� �Ǿ����ϴ�."));
				}
			}
		});
		button_panel.add(btn_order);
		
		
		content_panel.add(button_panel, BorderLayout.SOUTH);
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		main.content_panel.setVisible(true);
		main.content_panel.add(content_panel);
		
		/** ��Ʈ **/
		board_table.setFont(Commons.getFont());
		scrollPane.setFont(Commons.getFont());
		btn_order.setFont(Commons.getFont());
		
		
		
	}

}
