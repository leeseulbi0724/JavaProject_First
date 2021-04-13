package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Commons.Commons;

public class Admin_MainUI  implements ActionListener {

	LoginUI main;
	JFrame frame;
	JPanel btn_panel, top_panel, content_panel;
	JButton btn_delete, btn_insert, btn_user_select, btn_board, btn_logout, btn_home;
	JScrollPane scrollPane;
	
	public static final int home = 0;
	public static final int Insert = 1;
	public static final int Delete = 2;
	public static final int User_select = 3;
	public static final int Board = 4;
	
	
	public Admin_MainUI(LoginUI main) {
		this.main = main;
		this.frame = main.frame;
		init();
	}
	
	public void init() {
		
		btn_panel = new JPanel();
		btn_panel.setBackground(Color.WHITE);
		btn_panel.setBounds(61, 98, 131, 331);
		frame.getContentPane().add(btn_panel);
		btn_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btn_panel.setBackground(new Color(255, 245, 238));
		
		btn_insert = new JButton("  ���� ��� ");
		btn_panel.add(btn_insert);
		btn_insert.setBackground(Color.LIGHT_GRAY);
		
		btn_delete = new JButton("  ���� ���� ");
		btn_delete.setBackground(Color.LIGHT_GRAY);
		btn_panel.add(btn_delete);
		
		btn_user_select = new JButton("  ȸ�� ��ȸ ");
		btn_user_select.setBackground(Color.LIGHT_GRAY);
		btn_panel.add(btn_user_select);
		
		btn_board = new JButton("ȸ�� �Խ���");
		btn_board.setBackground(Color.LIGHT_GRAY);
		btn_panel.add(btn_board);
		
		top_panel = new JPanel();
		top_panel.setBounds(423, 23, 325, 56);
		top_panel.setBackground(new Color(255, 245, 238));
		
		btn_home = new JButton("");
		btn_home.setBackground(new Color(255, 245, 238));
		btn_home.setIcon(new ImageIcon("images/home.png"));
		btn_home.setBorderPainted(false);		
		top_panel.add(btn_home);
		
		frame.getContentPane().add(top_panel);
		
		JLabel title_label = new JLabel("�����ڴ� ȯ���մϴ�");
		top_panel.add(title_label);
		
		btn_logout = new JButton("�α׾ƿ�");
		btn_logout.setBackground(Color.WHITE);
		top_panel.add(btn_logout);
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.activeCaption);
		content_panel.setBounds(192, 98, 535, 331);
		String[] colNames = {"������ȣ","������","����","���ǻ�","����","������","�Ǹż���"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel name_label = new JLabel("�� �� �� �� �� Ȳ");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		content_panel.add(name_label, BorderLayout.NORTH);
		name_label.setBackground(SystemColor.activeCaption);
				
		scrollPane = new JScrollPane();
		JTable book_table = new JTable(model);
		JScrollPane book_pane = new JScrollPane(book_table);
		scrollPane.setViewportView(book_pane);		
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		content_panel.setVisible(true);
		frame.getContentPane().add(content_panel);
		frame.getContentPane().setBackground(new Color(255, 245, 238));
		
		
		/** ��ư �̺�Ʈ ó�� **/		
		btn_delete.addActionListener(this);
		btn_logout.addActionListener(this);
		btn_insert.addActionListener(this);
		btn_user_select.addActionListener(this);
		btn_board.addActionListener(this);		
		btn_home.addActionListener(this);
		
		
				
		/** ��Ʈ ���� (���Ƕ� �ּ�ó���Ͻðų� ���� ��Ʈ�� �����Ͻø� �˴ϴ�! )**/
		btn_delete.setFont(Commons.getFont());
		btn_user_select.setFont(Commons.getFont());
		btn_board.setFont(Commons.getFont());
		title_label.setFont(Commons.getFont());
		btn_logout.setFont(Commons.getFont());
		name_label.setFont(Commons.getFont());
		book_table.setFont(Commons.getFont(7));
		book_pane.setFont(Commons.getFont(7));
		btn_insert.setFont(Commons.getFont());
	}
	
	/** ����Ī **/
	public void switching(int num) {
		content_panel.removeAll();
		content_panel.setVisible(false);
		btn_insert.setBackground(Color.LIGHT_GRAY);
		btn_delete.setBackground(Color.LIGHT_GRAY);
		btn_user_select.setBackground(Color.LIGHT_GRAY);
		btn_board.setBackground(Color.LIGHT_GRAY);
			switch(num) {
				case home :
					content_panel.setVisible(true);
					content_panel.add(BorderLayout.CENTER, scrollPane);
					break;
				case Insert :
					content_panel.setVisible(true);
					btn_insert.setBackground(new Color(255, 228, 225));
					break;
				case Delete :
					content_panel.setVisible(true);
					btn_delete.setBackground(new Color(255, 228, 225));
					break;
				case User_select :
					content_panel.setVisible(true);
					btn_user_select.setBackground(new Color(255, 228, 225));
					break;
				case Board :
					content_panel.setVisible(true);
					btn_board.setBackground(new Color(255, 228, 225));
					break;		
			}
		
	}
	
	public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
				if (obj.equals(btn_insert)) {			
					new Admin_InsertUI(Admin_MainUI.this); // �������
				} else if (obj.equals(btn_delete)) {			
					new Admin_DeleteUI(Admin_MainUI.this); // ��������
				} else if (obj.equals(btn_home)) {
					switching(home);
				} else if (obj.equals(btn_board)) {
					new Admin_BoardUI(Admin_MainUI.this); //�Խ���
				} else if (obj.equals(btn_user_select)) {
					new Admin_MemberViewsUI(Admin_MainUI.this); // ȸ����ȸ
				} else if (obj.equals(btn_logout)) { 
					int result = JOptionPane.showConfirmDialog(null, "������ �α׾ƿ� �Ͻðڽ��ϱ�?"); // �α׾ƿ�
					if (result == 0 ) {
						btn_panel.setVisible(false);
						top_panel.setVisible(false);
						content_panel.setVisible(false);
						main.login_panel.setVisible(true);
						new LoginUI();		
					}
			}
		
	}
	
}
	
