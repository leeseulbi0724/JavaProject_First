package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import BookDAO.MemberDAO;
import BookVO.MemberVO;
import Commons.Commons;

public class User_MainUI implements ActionListener{

	/////Field	
	LoginUI main;
	JFrame f;
	JPanel topPanel;
	JPanel mainPanel;
	JPanel recommendText_Panel;
	JPanel content_panel;
	//ž�г� ��ư
	JButton logout_btn,myPage_btn,board_btn,home_btn;	
	//�����г� ��ư
	JButton book1_btn,book2_btn,bookList_btn;	
	String name;	
	// book1,book2 ����
	JTextArea Rankarea;
	JScrollPane scrollPane;
	public boolean flag;
	
	public static final int HOME = 0;
	public static final int BOOK = 1;
	public static final int BOOKLIST = 3;
	public static final int BOARD = 4;
	public static final int MYPAGE = 5;	
	
	MemberDAO dao = new MemberDAO();
	
	//Constructor	
	public User_MainUI(LoginUI main, String name) {
		this.main = main;
		this.f = main.frame;
		this.name = name;
		init();
		
	}
	
	public User_MainUI() {
	
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
		
		JLabel id_label = new JLabel();
		id_label.setBounds(120, 0, 187, 64);
		id_label.setText(name+"�� ȯ���մϴ�");
		id_label.setForeground(Color.pink);
		id_label.setBackground(Color.WHITE);
		topPanel.add(id_label);
		
		board_btn = new RoundedButton("�Խ���");
		board_btn.setBorder(null);
		board_btn.setFocusPainted(false);
		board_btn.setBounds(430, 10, 105, 36);
		topPanel.add(board_btn);
		
		myPage_btn = new RoundedButton("����������");
		myPage_btn.setForeground(Color.PINK);
		myPage_btn.setBackground(Color.WHITE);
		myPage_btn.setBorder(null);
		myPage_btn.setFocusPainted(false);
		myPage_btn.setBounds(547, 10, 105, 36);
		topPanel.add(myPage_btn);
		
		logout_btn = new RoundedButton("�α׾ƿ�");
		logout_btn.setForeground(Color.PINK);
		logout_btn.setBackground(Color.WHITE);
		logout_btn.setBorder(null);
		logout_btn.setFocusPainted(false);
		logout_btn.setBounds(672, 10, 105, 36);
		topPanel.add(logout_btn);
	
		home_btn = new JButton("");
		home_btn.setIcon(new ImageIcon("images/homeBtn.png"));
		home_btn.setPressedIcon(new ImageIcon("images/home_Btn2.png"));
		home_btn.setBackground(Color.WHITE);
		home_btn.setBorderPainted(false);
		home_btn.addActionListener(this);
		home_btn.setFocusPainted(false);
		home_btn.setBounds(12, 20, 105, 40);
		topPanel.add(home_btn);
		
		
		/*
		 * ���� �г�
		 */
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 240, 245));
		mainPanel.setBounds(0, 109, 804, 491);
		f.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		content_panel = new JPanel();
		content_panel.setBounds(12, 10, 768, 374);
		content_panel.setBackground(new Color(255, 240, 245));
		content_panel.setLayout(null);
		mainPanel.add(content_panel);
		
//		JLabel recommendIconLabel = new JLabel();
//		recommendIconLabel.setIcon(new ImageIcon("images/bestSeller.PNG"));
//		recommendIconLabel.setBounds(135, 10, 70, 70);
//		content_panel.add(recommendIconLabel);				
		
		book1_btn = new JButton();
		book1_btn.setBackground(new Color(255, 240, 245));
		book1_btn.setBorderPainted(false);
		book1_btn.setIcon(new ImageIcon("images/book1img.jpg"));
		book1_btn.setBounds(52, 118, 112, 172);
		content_panel.add(book1_btn);
		
		book2_btn = new JButton();
		book2_btn.setBackground(new Color(255, 240, 245));
		book2_btn.setBorderPainted(false);
		book2_btn.setIcon(new ImageIcon("images/book2img.jpg"));
		book2_btn.setBounds(188, 118, 112, 172);
		content_panel.add(book2_btn);		

		bookList_btn = new JButton("�� �� �� ü �� �� Ʈ");
		bookList_btn.setBackground(Color.PINK);
		bookList_btn.setBounds(285, 334, 201, 30);
		bookList_btn.setBorder(null);
//		bookList_btn.setFocusPainted(false);
		content_panel.add(bookList_btn);
		
		recommendText_Panel = new JPanel();
		recommendText_Panel.setLayout(new BorderLayout(0, 0));
		recommendText_Panel.setBackground(Color.WHITE);
		recommendText_Panel.setBounds(105, 78, 136, 30);
		content_panel.add(recommendText_Panel);
		
		JLabel recommendTextLabel = new JLabel("�̴��� ��õ����");
		recommendTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recommendText_Panel.add(recommendTextLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(517, 78, 136, 30);
		panel.setBackground(Color.WHITE);
		content_panel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));		
		
		JLabel lblNewLabel = new JLabel("�̴��� ���ſ�");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);		
		
		Rankarea = new JTextArea();
		Rankarea.setEditable(false);
		Rankarea.setTabSize(6);
		
		scrollPane = new JScrollPane(Rankarea);
		scrollPane.setBounds(454, 118, 256, 80);
		scrollPane.setVisible(true);
		content_panel.add(scrollPane);

		
		
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
		recommendTextLabel.setFont(Commons.getFont());
		id_label.setFont(Commons.getFont());
		lblNewLabel.setFont(Commons.getFont());		
		
		user_order_rank();
		
	}//init
	
	public void user_order_rank() {
		ArrayList<MemberVO> list = dao.getRank();		
		for (MemberVO m : list) {
			Rankarea.append("\t [ "+m.getRno() + "�� ] " + m.getId() + "�� " + m.getCount() + "�� ����\n");
		}
	}


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
				flag = false;
				new User_BookRecommendUI(User_MainUI.this,flag);
			}else if(obj==book2_btn) {
				flag = true;
				new User_BookRecommendUI(User_MainUI.this,flag);
			}
			else if(obj==bookList_btn) {	// ������� ��ư
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
					content_panel.setBounds(12, 10, 768, 374);
					mainPanel.add(content_panel);
				}
		
		/** ��ư ������ �ٲٱ� **/
		class RoundedButton extends JButton {
		      public RoundedButton() { super(); decorate(); } 
		      public RoundedButton(String text) { super(text); decorate(); } 
		      public RoundedButton(Action action) { super(action); decorate(); } 
		      public RoundedButton(Icon icon) { super(icon); decorate(); } 
		      public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
		      protected void decorate() { setBorderPainted(false); setOpaque(false); }
		      @Override 
		      protected void paintComponent(Graphics g) {
		         Color c=new Color(255, 240, 245); //���� ����
		         Color o=new Color(0,0,0); //���ڻ� ����
		         int width = getWidth(); 
		         int height = getHeight(); 
		         Graphics2D graphics = (Graphics2D) g; 
		         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		         if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
		         else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
		         else { graphics.setColor(c); } 
		         graphics.fillRoundRect(0, 0, width, height, 10, 10); 
		         FontMetrics fontMetrics = graphics.getFontMetrics(); 
		         Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
		         int textX = (width - stringBounds.width) / 2; 
		         int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
		         graphics.setColor(o); 
		         graphics.setFont(getFont()); 
		         graphics.drawString(getText(), textX, textY); 
		         graphics.dispose(); 
		         super.paintComponent(g); 
		         }
		      }
	
}
