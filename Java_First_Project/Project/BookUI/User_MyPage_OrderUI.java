package BookUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_MyPage_OrderUI implements ActionListener, MouseListener{
	
	User_MyPageUI main;
	JPanel content_panel;
	String[] colName = {"�ֹ���ȣ", "������", "����", "���ǻ�", "����", "�ֹ�����", "�ֹ�����"};
	DefaultTableModel model = new DefaultTableModel(colName, 0);
	Object[] row = new Object[7];
	JTable board_table = new JTable(model);
	BookSystem system = new BookSystem();
	JButton btn_review;
	String bookname, user_name;
	
	public User_MyPage_OrderUI(User_MyPageUI main) {
		this.main = main;
		this.system = main.system;
		this.user_name = main.user_name;
		init();
	}
	
	public void init() {
		main.switching(User_MyPageUI.Order);
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.info);
		content_panel.setBounds(0, 0, 531, 301);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		//table�� row data �߰�
		orderData();
		model.setColumnIdentifiers(colName);
		board_table.setModel(model);
		board_table.setRowHeight(20);		
		
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btn_review = new JButton("�����ۼ�");
		button_panel.add(btn_review);		
		
		content_panel.add(button_panel, BorderLayout.SOUTH);
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		main.content_panel.setVisible(true);
		main.content_panel.add(content_panel);
		
		/** ��Ʈ **/
		board_table.setFont(Commons.getFont());
		scrollPane.setFont(Commons.getFont());
		btn_review.setFont(Commons.getFont());
		
		/** ��ư �̺�Ʈ **/
		btn_review.addActionListener(this);
		board_table.addMouseListener(this);
	}//init
	
	//table�� ��µǴ� ������ (BOOKNAME, AUTHOR, PBLSH, PRICE) ����
	public void orderData() {
		model.setNumRows(0);
		for(BookVO book : main.system.getOrderList(main.main.name)) {
			
			row[1] = book.getBookname();
			row[2] = book.getAuthor();
			row[3] = book.getPblsh();
			row[4] = book.getPrice();
			row[5] = book.getCount();
			row[6] = book.getPblshdate();
			model.addRow(row);
			
		}
		model.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_review)) {
			if (system.ReviewCheck(bookname, user_name)) {
				if (bookname != null) {
					User_ReviewWriteUI rw = new User_ReviewWriteUI(main.frame, bookname, user_name);
					rw.setVisible(true);				
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("���並 �ۼ��Ͻ� �ֹ����� Ŭ�����ּ���."));
				}				
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("�̹� ���� �ۼ��� �Ϸ�� �����Դϴ�."));
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		 //������ ���� �� ��ȣ��� 
		  int row = board_table.getSelectedRow();		  
		  //���̺��� �𵨰�ü ������
		  TableModel data = board_table.getModel();		  
		  bookname = (String)data.getValueAt(row,1);  
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	

	
}// class 
