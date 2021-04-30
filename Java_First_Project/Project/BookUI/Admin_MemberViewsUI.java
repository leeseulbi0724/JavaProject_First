package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
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

import BookDAO.MemberDAO;
import BookSystem.BookSystem;
import BookVO.MemberVO;
import Commons.Commons;



public class Admin_MemberViewsUI implements ActionListener {
	JFrame frame;
	Admin_MainUI main;
	LoginUI ui;
	ArrayList<MemberVO> list;
	JButton btn_search;
	JPanel bottom_panel;
	JComboBox comboBox;
	JTextField search_tf;
	JButton btn_member;
	DefaultTableModel model;
	JScrollPane scrollPane, member_pane, search_pane;
	JTable member_table;
	Object row[];
	BookSystem system;
	MemberDAO mdao = new MemberDAO();

	
	static final int LIST = 1;
	static final int SEARCH = 2;
	
	public Admin_MemberViewsUI(Admin_MainUI main) {
		this.system = main.system;
		this.main = main;
		this.frame = main.frame;
		init();
		
	}
	
	
	public void init() {
		main.switching(Admin_MainUI.User_select);		

		scrollPane = new JScrollPane(member_table);		
//		member_pane = new JScrollPane();
		
		bottom_panel = new JPanel();
		bottom_panel.setBackground(new Color(176, 196, 222));
		bottom_panel.setBounds(192, 439, 535, 41);
		bottom_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel name_label = new JLabel("ȸ �� �� ȸ");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		main.content_panel.add(name_label, BorderLayout.NORTH);
		name_label.setBackground(SystemColor.activeCaption);
		
		JLabel bottom_label = new JLabel("������ ȸ���� �˻����ּ���");
		bottom_label.setHorizontalAlignment(SwingConstants.CENTER);
		bottom_panel.add(bottom_label, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		bottom_panel.add(comboBox, BorderLayout.WEST);
		comboBox.addItem("���̵�");		comboBox.addItem("�̸�");
		
		btn_search = new JButton("�˻�");
		bottom_panel.add(btn_search, BorderLayout.EAST);
		btn_search.addActionListener(this);
		
		search_tf = new JTextField();
		bottom_panel.add(search_tf, BorderLayout.CENTER);
		search_tf.setColumns(10);
		search_tf.addActionListener(this);
		
		main.content_panel.add(BorderLayout.SOUTH, bottom_panel);
		
		search_tf.requestFocus();
		
		/** ��Ʈ ���� **/
		name_label.setFont(Commons.getFont());
		bottom_label.setFont(Commons.getFont());
		comboBox.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());	

		member_data();
		
		
	}
	
	public void member_data() {
		list = system.getResultSelect();
		String [] colNames = new String [] {"��ȣ","���̵�","�н�����","�̸�","�������","HP","�ּ�","����"};
		Object[][] rowDatas = new Object[list.size()][colNames.length];
		 for (int i = 0; i < list.size(); i++) {
	            rowDatas[i] = new Object[] {
	                list.get(i).getRno(),
	                list.get(i).getId(),
	                list.get(i).getPass(),
	                list.get(i).getName(),
	                list.get(i).getBirthday(),
	                list.get(i).getHp(),
	                list.get(i).getAddr(),
	                " "
	            };
	        }
		 
		 member_table = new JTable();
		 member_table.setModel(new DefaultTableModel(rowDatas,colNames) {
	            boolean[] columnEditables = new boolean[] {
	                false, false, false, true, false
	            };
	        });
		
		 member_table.getColumnModel().getColumn(7).setCellRenderer(new TableUpdateCell("����", this, mdao,Admin_MemberViewsUI.LIST ));
	     member_table.getColumnModel().getColumn(7).setCellEditor(new TableUpdateCell("����", this, mdao,Admin_MemberViewsUI.LIST ));
		
	     member_table.getColumnModel().getColumn(0).setResizable(false);
	     member_table.getColumnModel().getColumn(0).setPreferredWidth(50);
	     member_table.getColumnModel().getColumn(1).setResizable(false);
	     member_table.getColumnModel().getColumn(1).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(2).setResizable(false);
	     member_table.getColumnModel().getColumn(2).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(3).setResizable(false);
	     member_table.getColumnModel().getColumn(3).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(4).setResizable(false);
	     member_table.getColumnModel().getColumn(4).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(5).setResizable(false);
	     member_table.getColumnModel().getColumn(5).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(6).setResizable(false);
	     member_table.getColumnModel().getColumn(6).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(7).setResizable(false);
	     member_table.getColumnModel().getColumn(7).setPreferredWidth(140);	   
	     
//	     member_pane.setViewportView(member_table);
	     scrollPane.setViewportView(member_table);	  
		main.content_panel.add(scrollPane, BorderLayout.CENTER);
		main.content_panel.setVisible(true);
		
			    
		/** ���̺� �� ���� **/
		JTableHeader head = member_table.getTableHeader();
		head.setBackground(new Color(173, 216, 230));
		head.setForeground(new Color(255,255,255));		
		member_table.setFont(Commons.getFont());
		
		table_sort();

		}
	
	//�˻�
    public void searchList() {
        list = system.search(comboBox.getSelectedItem().toString(),search_tf.getText());
        String [] colNames = new String [] {"��ȣ","���̵�","�н�����","�̸�","�������","HP","�ּ�","����"};
        Object[][] rowDatas = new Object[list.size()][colNames.length];             
        for (MemberVO member : list) {
            rowDatas[0] = new Object[] {            		
            		member.getRno(),
            		member.getId(),
            		member.getPass(),
            		member.getName(),
            		member.getBirthday(),
            		member.getHp(),
            		member.getAddr(),
  	                " "
            };
        }
        member_table = new JTable();
        member_table.setModel(new DefaultTableModel(rowDatas,colNames) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, true, false
            };
        });
        
        //JTable��  ���� ��ư ���� �� �̺�Ʈ ó���� ���� ������ ����
        member_table.getColumnModel().getColumn(7).setCellRenderer(new TableUpdateCell("����", this, mdao,Admin_MemberViewsUI.SEARCH ));
	    member_table.getColumnModel().getColumn(7).setCellEditor(new TableUpdateCell("����", this, mdao,Admin_MemberViewsUI.SEARCH ));
		
	     member_table.getColumnModel().getColumn(0).setResizable(false);
	     member_table.getColumnModel().getColumn(0).setPreferredWidth(50);
	     member_table.getColumnModel().getColumn(1).setResizable(false);
	     member_table.getColumnModel().getColumn(1).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(2).setResizable(false);
	     member_table.getColumnModel().getColumn(2).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(3).setResizable(false);
	     member_table.getColumnModel().getColumn(3).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(4).setResizable(false);
	     member_table.getColumnModel().getColumn(4).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(5).setResizable(false);
	     member_table.getColumnModel().getColumn(5).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(6).setResizable(false);
	     member_table.getColumnModel().getColumn(6).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(7).setResizable(false);
	     member_table.getColumnModel().getColumn(7).setPreferredWidth(140);	    
	    		
//	    member_pane.setViewportView(member_table);
	    scrollPane.setViewportView(member_table);		
		main.content_panel.add(scrollPane, BorderLayout.CENTER);
		main.content_panel.setVisible(true);
	    
		/** ���̺� �� ���� **/
		JTableHeader head = member_table.getTableHeader();
		head.setBackground(new Color(173, 216, 230));
		head.setForeground(new Color(255,255,255));		
		member_table.setFont(Commons.getFont());
		
		table_sort();
		
    }    
    
    public void table_sort() {
    	 DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcmSchedule = member_table.getColumnModel();
			for (int i = 0; i < tcmSchedule.getColumnCount()-1; i++) {
				tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
    }
    
  //�˻� �̺�Ʈ ó��
    @Override
    public void actionPerformed(ActionEvent e) {
    	Object obj = e.getSource();
    	
    	if(obj == btn_search || obj == search_tf) {
    		if(search_tf.getText().equals("")) {
    			member_data();
    		}else {
    			searchList();
    		}
    	}
    }
	
	//JTable ����, ���� ��ư ���� �� �߰� Ŭ����
	class TableUpdateCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	    JButton jb;
	    MemberDAO mdao;

	    public TableUpdateCell(String name, Admin_MemberViewsUI mlist, MemberDAO mdao, int option) {
	    	this.mdao = mdao;
	        jb = new JButton(name);
	        jb.setFont(Commons.getFont());
	        
	        jb.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	String name = e.getActionCommand();            	            	
	            	if(name.equals("����")){
	            		int confirm=JOptionPane.showConfirmDialog(null, Commons.getMsg("������ �����Ͻðڽ��ϱ�?"));
		            	if(confirm==0) {	            		
		            		if(option == Admin_MemberViewsUI.LIST || option == Admin_MemberViewsUI.SEARCH) {
		            			boolean result = mdao.delete(mlist.list.get(mlist.member_table.getSelectedRow()).getId());	
		            			if (result) {
		            				JOptionPane.showMessageDialog(null, Commons.getMsg("ȸ���� �����Ǿ����ϴ�"));
		            				Admin_MemberViewsUI ui = new Admin_MemberViewsUI(main);
		            				ui.init();
		            			}          			
		            			
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
