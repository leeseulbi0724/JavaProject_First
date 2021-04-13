package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Commons.Commons;

public class User_MainUI implements ActionListener{

	//Field
	
	LoginUI main;
	JFrame f;
	JPanel mainPanel,topPanel;	
	//ž�г� ��ư
	JButton logout_btn,myPage_btn,board_btn,home_btn;	
	//�����г� ��ư
	JButton book1_btn,book2_btn,bookList_btn;	
	//Ȩ ȭ�� ������ �� �ʱ�ȭ�Ǿ��� ������ �ٽ� �־������
	JLabel recommendIconLabel;
	
	
	
	public static final int HOME = 0;
	public static final int BOOK = 1;
	public static final int BOOKLIST = 3;
	public static final int BOARD = 4;
	public static final int MYPAGE = 5;	
	
	
	//Constructor	
	public User_MainUI(LoginUI main) {
		this.main = main;
		this.f = main.frame;
		init();
	}

	
	//Method
	public void init() {
		
		/*
		 * ž�г�
		 */
		topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		topPanel.setBounds(0, 0, 804, 110);
		f.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
		board_btn = new JButton("�Խ���");
		board_btn.setBackground(Color.WHITE);
		board_btn.setForeground(Color.PINK);
		board_btn.setBorder(null);
		board_btn.setBounds(430, 10, 105, 36);
		topPanel.add(board_btn);
		
		myPage_btn = new JButton("����������");
		myPage_btn.setForeground(Color.PINK);
		myPage_btn.setBackground(Color.WHITE);
		myPage_btn.setBorder(null);
		myPage_btn.setBounds(547, 10, 113, 36);
		topPanel.add(myPage_btn);
		
		logout_btn = new JButton("�α׾ƿ�");
		logout_btn.setForeground(Color.PINK);
		logout_btn.setBackground(Color.WHITE);
		logout_btn.setBorder(null);
		logout_btn.setBounds(672, 10, 105, 36);
		topPanel.add(logout_btn);
	
		home_btn = new JButton("");
		home_btn.setIcon(new ImageIcon("images/pngwing.com.png"));
		home_btn.setBackground(Color.WHITE);
		home_btn.setBorderPainted(false);
		home_btn.setBounds(12, 10, 105, 36);
		topPanel.add(home_btn);
		
		
		/*
		 * ���� �г�
		 */
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 109, 804, 431);
		f.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
				
		recommendIconLabel = new JLabel("");
		recommendIconLabel.setIcon(new ImageIcon("images/recommendIcon.PNG"));
		recommendIconLabel.setBounds(282, 10, 209, 59);
		mainPanel.add(recommendIconLabel);		
		
		book1_btn = new JButton("");
		book1_btn.setBackground(Color.WHITE);
		book1_btn.setIcon(new ImageIcon("images/book1.jfif"));
		book1_btn.setBounds(214, 92, 114, 150);
		mainPanel.add(book1_btn);
		
		book2_btn = new JButton("");
		book2_btn.setIcon(new ImageIcon("images/javaimg.jfif"));
		book2_btn.setBackground(Color.WHITE);
		book2_btn.setBounds(443, 92, 114, 150);
		mainPanel.add(book2_btn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(170, 304, 434, 2);
		mainPanel.add(separator);
		
		bookList_btn = new JButton("������ȸ");
		bookList_btn.setForeground(Color.WHITE);
		bookList_btn.setBackground(Color.PINK);
		bookList_btn.setBounds(257, 337, 263, 47);
		mainPanel.add(bookList_btn);
		
		/* ��ư */
		home_btn.addActionListener(this);
		book1_btn.addActionListener(this);
		book2_btn.addActionListener(this);
		bookList_btn.addActionListener(this);
		board_btn.addActionListener(this);
		myPage_btn.addActionListener(this);
		logout_btn.addActionListener(this);
		
		
		/** ��Ʈ **/
		myPage_btn.setFont(Commons.getFont());
		logout_btn.setFont(Commons.getFont());
		board_btn.setFont(Commons.getFont());
		bookList_btn.setFont(Commons.getFont());
		
	}//init


	public void switching(int num) {
		mainPanel.removeAll();
		mainPanel.setVisible(false);
		switch(num) {
			case HOME :
				mainPanel.setVisible(true);
				//���µǴ� ��ư�� ���� �����ϴ°�
				createContent();
			case BOARD :
				mainPanel.setVisible(true);
			case MYPAGE :
				mainPanel.setVisible(true);
			case BOOK :			
				mainPanel.setVisible(true);
			case BOOKLIST :
				mainPanel.setVisible(true);
		}
	}	
	
	
	/* ��ư�׼� */
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj.equals(home_btn)) {		//Ȩ ��ư
				switching(HOME);
			}else if(obj==book1_btn) {		// BOOK1 Ŭ����
				new User_BookRecommendUI(User_MainUI.this);
			}else if(obj==book2_btn) {		// BOOK2 Ŭ����
				new User_BookRecommendUI(User_MainUI.this);
			}else if(obj==bookList_btn) {	// ������� ��ư
				new User_BookListSearchUI(User_MainUI.this);
			}else if(obj.equals(board_btn)) {	//�Խ��� ��ư (O)
				new User_BoardUI(User_MainUI.this);			
			}else if(obj.equals(myPage_btn)) {		//���������� ��ư (O)
				new User_MyPageUI(User_MainUI.this);			
			}else if(obj.equals(logout_btn)) {		//�α׾ƿ� ��ư (O)
				int result = JOptionPane.showConfirmDialog(null,"������ �α׾ƿ� �Ͻðڽ��ϱ�?");	
				if(result == 0) {
					mainPanel.setVisible(false);
					topPanel.setVisible(false);
					main.login_panel.setVisible(true);
					new LoginUI();
				}
			}
		}
		
		//����ġ���� Ȩ��ư ������ ��ư ���� �����ؾ��ϴ°� �� �������ؼ� ���� �Űܺþ�� �ٽ� �ű�ŵ� �ſ� ^^
		public void createContent() {
			recommendIconLabel.setBounds(282, 10, 209, 59);
			mainPanel.add(recommendIconLabel);		
			book1_btn.setBounds(214, 92, 114, 150);
			mainPanel.add(book1_btn);
			book2_btn.setBounds(443, 92, 114, 150);
			mainPanel.add(book2_btn);
			bookList_btn.setBounds(257, 337, 263, 47);
			mainPanel.add(bookList_btn);
		}
	
}
