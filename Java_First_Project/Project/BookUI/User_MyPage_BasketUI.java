package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_MyPage_BasketUI implements MouseListener{

	JFrame frame;
	User_MyPageUI main;
	JPanel content_panel, btn_panel;
	JTextField textField;
	String[] colName = {"������", "��������", "����"};
	DefaultTableModel model = new DefaultTableModel(colName, 0);
	Object[] row = new Object[3];
	JTable board_table = new JTable(model);
	BookSystem system = new BookSystem();
	BookVO vo;
	String name;

	public User_MyPage_BasketUI(User_MyPageUI main) {
		this.main = main;
		this.frame = main.frame;
		this.system = main.system;
		init();
	}

	
	public void init() {
		main.switching(User_MyPageUI.Basket);		
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.info);
		content_panel.setBounds(0, 0, 531, 301);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		//table�� row data �߰�
		basketData();
		model.setColumnIdentifiers(colName);
		board_table.setModel(model);
		board_table.setRowHeight(20);
		
		JScrollPane scrollPane = new JScrollPane();		
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);		
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn_order = new JButton("�ֹ��ϱ�");
		btn_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btn_order)) {
					Order_proc();
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
		
		/** ���̺� ��� ���� **/
		JTableHeader head = board_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
		
		/** ���̺� �� Ŭ�� �̺�Ʈ **/
		board_table.addMouseListener(this);
		
	} //init

	//table�� ��µǴ� ������ (BOOKNAME, AUTHOR, PRICE) ����
	public void basketData() {
		model.setNumRows(0);
		for(BookVO book : main.system.getBookList(main.main.name)) {
			row[0] = book.getBookname();
			row[1] = book.getPrice();
			row[2] = book.getCount();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		  //������ ���� �� ��ȣ��� 
		  int row = board_table.getSelectedRow();		  
		  //���̺��� �𵨰�ü ������
		  TableModel data = board_table.getModel();		  
		  //0��° �������� �޾� setBookname���� �Ѱ���
		  name = (String)data.getValueAt(row,0);
		  vo = new BookVO();
		  vo.setBookname(name);	
		  
	}
	
	
	public void Order_proc() {
		vo = new BookVO();
		
		vo.setBookname(name);
		
		if(system.User_Order(vo, name)) {	//insert
			JOptionPane.showMessageDialog(null, Commons.getMsg("�ֹ��Ϸ����"));
		}else {
			JOptionPane.showMessageDialog(null, Commons.getMsg("�ֹ����Ф�����"));
		}
		
	}
	
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {	
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	
	
}//class