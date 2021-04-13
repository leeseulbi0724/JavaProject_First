package BookUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Commons.Commons;

public class LoginUI {

	//Field
	 JFrame frame;
	 ImagePanel login_panel;
	 JTextField id_tf;
	 JLabel id_label;
	 JLabel password_label;
	 JPasswordField password_tf;
	
	 JLabel lblNewLabel;
	 JPanel main_panel;
	
	
	//Constructor
	public LoginUI() {		
		init();
	}

	//init
	public void init() {
		frame = new JFrame("���� ���� ���α׷�");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 804, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		login_panel = new ImagePanel(new ImageIcon("images/books_img.jpg").getImage());
		login_panel.setBounds(0, 0, 800, 513);
		frame.getContentPane().add(login_panel);
		login_panel.setLayout(null);
		login_panel.setVisible(true);
		
		id_tf = new JTextField();
		id_tf.setBounds(47, 197, 266, 51);
		login_panel.add(id_tf);
		id_tf.setColumns(10);
		
		id_label = new JLabel("���̵�");
		id_label.setBounds(47, 150, 144, 37);
		login_panel.add(id_label);
		
		password_label = new JLabel("�н�����");
		password_label.setBounds(47, 263, 144, 37);
		login_panel.add(password_label);
		
		JButton login_btn = new JButton("");
		login_btn.setBorderPainted(false);
		login_btn.setIcon(new ImageIcon("images/kakao_login_btn_img.png"));
		login_btn.setBackground(Color.WHITE);
		login_btn.setBounds(100, 377, 125, 51);
		login_panel.add(login_btn);
		//�α��� ��ư
		login_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( id_tf.getText().equals("admin") && password_tf.getText().equals("1234")) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("������ �α��ο� �����ϼ̽��ϴ�."));
					id_tf.setText(""); password_tf.setText("");
					new Admin_MainUI(LoginUI.this);
					login_panel.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("����� �α��ο� �����ϼ̽��ϴ�."));
					new User_MainUI(LoginUI.this);				
					login_panel.setVisible(false);
				}				
			}
		});//�α��� ��ư �׼�
		
		JButton sign_btn = new JButton("ȸ������");
		sign_btn.setBackground(Color.WHITE);
		sign_btn.setBorderPainted( false );
		sign_btn.setBounds(33, 438, 125, 23);
		login_panel.add(sign_btn);
		//ȸ������ ��ư �׼�
		sign_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUI sign = new SignUI(frame);
				sign.setVisible(true);
			}
		});
		
		JButton findPassword_btn = new JButton("��й�ȣ ã��");
		findPassword_btn.setBackground(Color.WHITE);
		findPassword_btn.setBorderPainted( false );
		findPassword_btn.setBounds(184, 438, 125, 23);
		login_panel.add(findPassword_btn);
		//��й�ȣ ã�� �׼�
		findPassword_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FindPassUI find = new FindPassUI(frame);
				find.setVisible(true);
			}
		});
		
		password_tf = new JPasswordField();
		password_tf.setBounds(47, 310, 266, 51);
		login_panel.add(password_tf);
		
		/** ��Ʈ ���� **/
		id_label.setFont(Commons.getFont());
		password_label.setFont(Commons.getFont());
		login_btn.setFont(Commons.getFont());
		id_tf.setFont(Commons.getFont());
		login_btn.setFont(Commons.getFont());
		sign_btn.setFont(Commons.getFont());
		findPassword_btn.setFont(Commons.getFont());
	}
	
	
}//LoginUI


	//�̹��� �г� class ��濡 �̹��� �ֱ�
	class ImagePanel extends JPanel {
		//Field
		private Image img;
		
		//Constructor
		public ImagePanel(Image img) {
			this.img=img;
			setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		}
		
		public int getWidth() {
			return img.getWidth(null);
		}
		public int getHeight() {
			return img.getHeight(null);
		}
		//Method
		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}//ImagePanel class




