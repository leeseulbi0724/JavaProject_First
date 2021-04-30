package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookSystem.BookSystem;
import BookVO.BoardVO;
import Commons.Commons;

public class User_WriteUI extends JDialog implements ActionListener {

	User_MainUI main;
	JPanel content_panel;
	JButton btn_reset, btn_write;
	JComboBox comboBox;
	JTextField title_tf;
	JTextArea content_ta;
	String name, boxname, bid;
	BookSystem system;
	String category;
	BoardVO vo;
	
	public User_WriteUI(String category, User_MainUI main, Window parent) {
		super(parent, "�Խñ� �ۼ�", ModalityType.APPLICATION_MODAL);
		this.category = category;
		this.main = main;
		this.name = main.name;
		this.system = main.system;
		init();
	}
	public User_WriteUI(String bid, String category, BoardVO vo, User_MainUI main, Window parent) {
		super(parent, "�Խñ� �ۼ�", ModalityType.APPLICATION_MODAL);
		this.bid = bid;
		this.vo = vo;
		this.category = category;
		this.main = main;
		this.name = main.name;
		this.system = main.system;
		init();
	}
	
	
	public void init() {
		
		content_panel = new JPanel();
		content_panel.setBackground(new Color(240, 248, 255));
		content_panel.setBounds(133, 10, 531, 300);
		content_panel.setLayout(new BorderLayout(0, 0));
		add(content_panel);
		setBounds(133, 10, 531, 380);
		setLocationRelativeTo(null);
		
		JLabel Label = new JLabel(" �� �� �� �� �� �� ");
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		content_panel.add(Label, BorderLayout.NORTH);

		JPanel write_panel = new JPanel();
		write_panel.setBackground(new Color(240, 248, 255));
		content_panel.add(write_panel);
		write_panel.setLayout(null);
		
		JLabel label_title = new JLabel("����");
		JLabel label_content = new JLabel("����");		
		JLabel label_gory = new JLabel("�о�");

		JPanel box_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		box_panel.setBackground(new Color(240, 248, 255));
		box_panel.setBounds(87, 5, 388, 32);
		write_panel.add(box_panel);
		
		label_title.setBounds(12, 40, 75, 15);
		label_content.setBounds(12, 70, 75, 15);
		
		comboBox = new JComboBox();
		comboBox.addItem("����");	 comboBox.addItem("����FAQ");		comboBox.addItem("������û");
		box_panel.add(comboBox);		
		
		title_tf = new JTextField(50);
		content_ta = new JTextArea();
		
		title_tf.setBounds(91, 40, 388, 25);
		content_ta.setBounds(91, 75, 388, 186);	
		
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_content.setHorizontalAlignment(SwingConstants.CENTER);
		
		write_panel.add(label_title);		
		write_panel.add(title_tf);
		write_panel.add(label_content);			
		write_panel.add(content_ta);		

		btn_write = new JButton("Ȯ��");
		btn_write.setBounds(153, 280, 75, 23);
		write_panel.add(btn_write);
		
		btn_reset = new JButton("���");
		btn_reset.setBounds(324, 280, 75, 23);
		write_panel.add(btn_reset);		
		
		/** ��Ʈ���� **/
		label_title.setFont(Commons.getFont());
		label_content.setFont(Commons.getFont());
		btn_write.setFont(Commons.getFont());
		btn_reset.setFont(Commons.getFont());
		title_tf.setFont(Commons.getFont());
		content_ta.setFont(Commons.getFont());
		Label.setFont(Commons.getFont(20));
		comboBox.setFont(Commons.getFont());		
		
		/** �̺�Ʈ ó�� **/
		comboBox.addActionListener(this);		
		btn_reset.addActionListener(this);
		btn_write.addActionListener(this);
		
		if (category == "����") {
			if (vo.getCategory().equals("����FAQ")) {
				comboBox.setSelectedIndex(1);				
			} else if (vo.getCategory().equals("������û")) {
				comboBox.setSelectedIndex(2);
			}
			title_tf.setText(vo.getTitle());
			content_ta.setText(vo.getContent());
			comboBox.setEnabled(false);
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_reset)) {
			dispose();
		} else if (obj.equals(comboBox)) {
			comboBox_form();
		} else if (obj.equals(btn_write)) {
			write_proc();
		}
	}
	
	/** �۾��� ��� **/
	public void comboBox_form() {
		boxname = comboBox.getSelectedItem().toString();
		if (boxname.equals("����FAQ")) {
			title_tf.setText("�����մϴ�!");
			content_ta.setText("");
		} else if (boxname.equals("������û")) {
			title_tf.setText("���� ��û�մϴ�!");
			content_ta.setText(" ������ : \n ���� : \n ���� : ");
		} else if (boxname.equals("����")) {
			title_tf.setText("");
			content_ta.setText("");
		}
	}
	
	/** ���� DB ���� **/
	public void write_proc() {
		BoardVO board = new BoardVO();
		board.setCategory(boxname);
		board.setTitle(title_tf.getText());
		board.setContent(content_ta.getText());
		board.setId(name);
		if (category == "�ۼ�") {
			if (system.User_Board(board)) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("�ۼ��� �Ϸ�Ǿ����ϴ�."));
				dispose();
				new User_BoardUI(main);
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("�ۼ��� ���еǾ����ϴ�."));
			}		
		} else if (category == "����") {			
			if (system.User_Board_Update(bid, board)) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("������ �Ϸ�Ǿ����ϴ�."));
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("������ ���еǾ����ϴ�."));
			}
		}
	}

}
