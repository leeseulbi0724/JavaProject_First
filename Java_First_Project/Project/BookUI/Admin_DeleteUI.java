package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class Admin_DeleteUI implements ActionListener{
//
	Admin_MainUI main;
	JFrame frame;
	JPanel bottom_panel;
	JButton btn_search;
	JTextField search_tf;	
	JComboBox comboBox;
	ArrayList<BookVO> list;
	BookSystem system;
	String comboname;
	
	Object[] header = {"��ȣ", "������", "����", "���ǻ�", "����", "������", "����"};		
	JTable book_table = new JTable();
	JScrollPane scrollPane = new JScrollPane(book_table);		
	
	static final int LIST= 1;

	public Admin_DeleteUI(Admin_MainUI main) {
		this.main = main;
		this.frame = main.frame;
		init();
	}
	public void init() {	
		main.switching(Admin_MainUI.Delete);
		bottom_panel = new JPanel();
		bottom_panel.setBackground(new Color(176, 196, 222));
		bottom_panel.setBounds(192, 439, 535, 41);
		bottom_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel bottom_label = new JLabel("������ ������ �˻����ּ���");
		bottom_label.setHorizontalAlignment(SwingConstants.CENTER);
		bottom_panel.add(bottom_label, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		bottom_panel.add(comboBox, BorderLayout.WEST);
		comboBox.addItem("������ȣ");  comboBox.addItem("������");	
		
		JLabel name_label = new JLabel("�� �� �� ��");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		main.content_panel.add(name_label, BorderLayout.NORTH);
		name_label.setBackground(SystemColor.activeCaption);
		
		btn_search = new JButton("�˻�");
		bottom_panel.add(btn_search, BorderLayout.EAST);
		
		search_tf = new JTextField();
		bottom_panel.add(search_tf, BorderLayout.CENTER);
		search_tf.setColumns(10);
		
		main.content_panel.add(BorderLayout.CENTER, main.scrollPane);
		main.content_panel.add(BorderLayout.SOUTH, bottom_panel);
		
		btn_search.addActionListener(this);
		search_tf.addActionListener(this);
		
		search_tf.requestFocus();
		
		
		/** ��Ʈ ���� (���Ƕ� �ּ�ó���Ͻðų� ���� ��Ʈ�� �����Ͻø� �˴ϴ�! )**/
		bottom_label.setFont(Commons.getFont());
		comboBox.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());
		name_label.setFont(Commons.getFont());	
		
	}
	
	
	/** �˻� ��ư Ŭ�� �� �̺�Ʈ **/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		comboname = (String)comboBox.getSelectedItem();
		int i = 0;
		if (obj.equals(search_tf) || obj.equals(btn_search)) {
			ArrayList<BookVO> list = main.system.Book_Equals(comboname);
				for (i = 0; i<list.size(); i++) {
						if (search_tf.getText().equals(list.get(i).getBookname())) {
							main.content_panel.remove(main.scrollPane);
							main.content_panel.setVisible(false);					
							data_search(comboname);
						} 		
				}		 
			if (i == list.size()+1) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("�������� �ʴ� �����Դϴ�. �ٽ� �Է����ּ���"));
			}
		}
	}
	
	
	
	/** ���� �˻� **/
	public void data_search(String comboname) {		
		list = new ArrayList<BookVO>();
		list = main.system.Admin_Search(comboname, search_tf.getText());		
		main.content_panel.setVisible(true);		
		Object[][] rowDatas = new Object[list.size()][header.length];
		 for (int i = 0; i < list.size(); i++) {
	            rowDatas[i] = new Object[] {
	                list.get(i).getBno(),
	                list.get(i).getBookname(),
	                list.get(i).getAuthor(),
	                list.get(i).getPblsh(),
	                list.get(i).getPrice(),
	                list.get(i).getPblshdate(),
	                " "
	            };
	       }
		 book_table.setModel(new DefaultTableModel(rowDatas,header) {
	            boolean[] columnEditables = new boolean[] {
	                false, false, false, true, false
	            };
	        });
		 
		 book_table.getColumnModel().getColumn(6).setCellRenderer(new TableUpdateCell("����", this, system,Admin_DeleteUI.LIST ));
		 book_table.getColumnModel().getColumn(6).setCellEditor(new TableUpdateCell("����", this, system,Admin_DeleteUI.LIST ));
		
		 book_table.getColumnModel().getColumn(0).setResizable(false);
		 book_table.getColumnModel().getColumn(1).setResizable(false);
		 book_table.getColumnModel().getColumn(2).setResizable(false);
		 book_table.getColumnModel().getColumn(3).setResizable(false);
		 book_table.getColumnModel().getColumn(4).setResizable(false);
		 book_table.getColumnModel().getColumn(5).setResizable(false);
		 book_table.getColumnModel().getColumn(6).setResizable(false);
		 
		book_table.getColumnModel().getColumn(0).setPreferredWidth(10);
		book_table.getColumnModel().getColumn(1).setPreferredWidth(150);
		book_table.getColumnModel().getColumn(2).setPreferredWidth(30);
		book_table.getColumnModel().getColumn(3).setPreferredWidth(30);
		book_table.getColumnModel().getColumn(4).setPreferredWidth(30);
		book_table.getColumnModel().getColumn(5).setPreferredWidth(40);
		book_table.getColumnModel().getColumn(6).setPreferredWidth(40);

		book_table.setFont(Commons.getFont());
		scrollPane.setViewportView(book_table);	
		main.content_panel.add(scrollPane, BorderLayout.CENTER);
		
		 DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcmSchedule = book_table.getColumnModel();
			for (int i = 0; i < tcmSchedule.getColumnCount()-1; i++) {
				tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
        
        /** ���̺� �� ���� **/
		JTableHeader head = book_table.getTableHeader();
		head.setBackground(new Color(173, 216, 230));
		head.setForeground(new Color(255,255,255));		
		
	}
	
	//���� ��ư ���� �� �߰� Ŭ����
		class TableUpdateCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
		    JButton jb;
		   BookSystem system;

		    public TableUpdateCell(String name, Admin_DeleteUI mlist, BookSystem system, int option) {
		    	this.system= system;
		        jb = new JButton(name);
		        jb.setFont(Commons.getFont());		        
		        jb.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	String name = e.getActionCommand();            
		            	int result = 0;
		            	
		            	if(name.equals("����")){
		            		int confirm=JOptionPane.showConfirmDialog(null, Commons.getMsg("������ �����Ͻðڽ��ϱ�?"));
			            	if(confirm==0) {	            		
			            		if(option == Admin_DeleteUI.LIST) {
			            			if (main.system.Admin_Delete(comboname, search_tf.getText())) {
										JOptionPane.showMessageDialog(null, Commons.getMsg("������ �Ϸ�Ǿ����ϴ�."));
										main.switching(Admin_MainUI.home);						
			            		}			            		
			            		if(result !=0)	mlist.init();
			            	}
		            	}
		            	
		            }
		        }
		      });
		    }
		    @Override
		    public Object getCellEditorValue() {
		        return null;
		    }
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
		            int row, int column) {
		        return jb;
		    }
		    @Override
		    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
		            int column) {
		        return jb;
		    }
		}//TableUpdateCell class	

}
