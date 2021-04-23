package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import BookSystem.BookSystem;
import BookVO.BoardVO;
import BookVO.BookVO;
import Commons.Commons;

public class User_MyPage_MyUI extends JFrame implements MouseListener{
	User_MyPageUI main;
	JButton btn_delete;
	ArrayList<Object> list = new ArrayList<Object>();
	String[] colName = {"�з�", "����", "����", "�ۼ���¥"};
	DefaultTableModel model= new DefaultTableModel(colName, 0);
	JTable board_table = new JTable(model);
	Object[] row = new Object[5];
	String id;
	BookSystem system = new BookSystem();
	int rowrow;
	BoardVO vo;
	ArrayList<BoardVO> boardlist;
	BookVO book;
	ArrayList<BookVO> booklist;
	
	public User_MyPage_MyUI(User_MyPageUI main) {
		super();		
		this.main = main;
		this.id = main.user_name;
		init();
	}	
	
	
	public void init() {
		main.switching(User_MyPageUI.My);
		
		JScrollPane scrollPane = new JScrollPane();					
		
		JPanel center_panel = new JPanel();
		main.content_panel.add(center_panel);
		center_panel.setLayout(new BorderLayout(0, 0));
		center_panel.setBounds(12, 35, 507, 268);
				
		center_panel.add(scrollPane, BorderLayout.CENTER);
		
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);		
		board_pane.setEnabled(false);
		
		JCheckBox checkbox_board = new JCheckBox("�Խ���");
		checkbox_board.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					//�Խ��� ���̺� ���̱�
					boardData();
					model.setColumnIdentifiers(colName);
					board_table.setModel(model);
					board_table.setRowHeight(20);
				}
			}
		});
		checkbox_board.setBounds(70, 6, 70, 23);
		main.content_panel.add(checkbox_board);
		
		
		JCheckBox checkbox_review = new JCheckBox("����");
		checkbox_review.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					//���� ���̺� ���̱�
					reviewData();
					model.setColumnIdentifiers(colName);
					board_table.setModel(model);
					board_table.setRowHeight(20);
				}
			}
		});
		checkbox_review.setSelected(true);
		checkbox_review.setBounds(12, 6, 54, 23);
		main.content_panel.add(checkbox_review);
		
		//üũ�ڽ� ������ �� �ϳ��� ���õǵ��� ����
		ButtonGroup bg = new ButtonGroup();
		bg.add(checkbox_board);		bg.add(checkbox_review);
		
		
		JButton btn_delete = new JButton("����");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btn_delete)) {
					//�Խ��ǿ� üũ�Ǿ� ������ �Խ��ǻ����޼ҵ��, ����� ��������޼ҵ��
					if(checkbox_board.isSelected()) {
						deleteBoardData();
					} else if (checkbox_review.isSelected()) {
						deleteReviewData();
					}
				}
			}
		});
		btn_delete.setBounds(141, 6, 80, 23);
		main.content_panel.add(btn_delete);
		btn_delete.setBackground(Color.WHITE);
		
		/** ��Ʈ���� **/
		board_table.setFont(Commons.getFont());
		checkbox_board.setFont(Commons.getFont());
		checkbox_review.setFont(Commons.getFont());
		btn_delete.setFont(Commons.getFont());
		
		JTableHeader head = board_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
	
	}//init

	
	//table�� ��µǴ� ������ ���� - �ϴ��� �Խ���(ī�װ�, ����, ����, ��¥) �������
	public void boardData() {
		model.setNumRows(0);
		ArrayList<BoardVO> boardlist = system.All_Myboard(main.user_name);
		for(BoardVO board : boardlist) {
			row[0] = board.getCategory();
			row[1] = board.getTitle();
			row[2] = board.getContent();
			row[3] = board.getDate();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
	}
	
	//table�� ��µǴ� ������ ���� - �ϴ��� ����(ī�װ�, ����, ����, ��¥) �������
	public void reviewData() {
		model.setNumRows(0);
		ArrayList<BoardVO> boardlist = system.All_Myreview(main.user_name);
		for(BoardVO board : boardlist) {
			row[0] = board.getCategory();
			row[1] = board.getTitle();
			row[2] = board.getContent();
			row[3] = board.getDate();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
	}

	//table���� �����ϴ� �̺�Ʈ - �Խ��� �� ���� �޼ҵ�
	public void deleteBoardData() {
		rowrow = board_table.getSelectedRow();	
		vo = new BoardVO();
		ArrayList<BoardVO> boardlist = system.All_Myboard(main.user_name);
		
		if(rowrow == -1) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("������ �Խñ��� �������ּ���. "));
		}else {
			int confirm = JOptionPane.showConfirmDialog(null, Commons.getMsg("������ �����Ͻðڽ��ϱ�?"));
			if (confirm == 0) {
				int result = system.MyDelete_Board(boardlist.get(board_table.getSelectedRow()).getBid());
				if (result != 0) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("�Խñ��� �����Ǿ����ϴ�."));
					init();
				}				
			}			
		}
	}
	
	
	//table���� �����ϴ� �̺�Ʈ - ���� ���� �޼ҵ�
	public void deleteReviewData() {
		rowrow = board_table.getSelectedRow();	
		vo = new BoardVO();
		ArrayList<BoardVO> boardlist = system.All_Myreview(main.user_name);
		
		if (rowrow == -1) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("������ ���並 �������ּ���. "));
		} else {
			int confirm = JOptionPane.showConfirmDialog(null, Commons.getMsg("������ �����Ͻðڽ��ϱ�?"));
			if (confirm == 0) {
				int result = system.MyDelete_Review(boardlist.get(board_table.getSelectedRow()).getBid());
				if (result != 0) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("���䰡 �����Ǿ����ϴ�."));
					init();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//������ �� �������� �׼� �̺�Ʈ
		rowrow = board_table.getSelectedRow();	
		TableModel data = board_table.getModel();
		
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {			
	}
		
		
}//outer class
