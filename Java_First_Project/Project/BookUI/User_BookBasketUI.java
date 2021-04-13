package BookUI;

import java.awt.Color;
import java.awt.Window;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class User_BookBasketUI extends JDialog{

	//Filed
	
	LoginUI login;
	JTextField bsBookName_tf;
	JTextField bsBookPrice_tf;
	
	//Constructor
	public User_BookBasketUI(Window parent) {
		super(parent,"��ٱ���",ModalityType.APPLICATION_MODAL);
		init();
		login = new LoginUI();
	}
	
	
	//Method
	public void init() {
		JPanel basketPanel = new JPanel();
		setBounds(100, 100, 530, 540);
		getContentPane().setLayout(null);
		basketPanel.setBackground(Color.WHITE);
		basketPanel.setBounds(0, 0, 530, 540);
		getContentPane().add(basketPanel);
		basketPanel.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(44, 113, 428, 2);
		basketPanel.add(separator_1);
		
		String[] count = new String[] {"1","2","3","4","5"};
		JComboBox count_box = new JComboBox(count);
		count_box.setBounds(347, 152, 125, 35);
		basketPanel.add(count_box);
		
		bsBookName_tf = new JTextField();
		bsBookName_tf.setText("\uC0C1\uD488\uBA85");
		bsBookName_tf.setBounds(44, 125, 269, 35);
		basketPanel.add(bsBookName_tf);
		bsBookName_tf.setColumns(10);
		
		bsBookPrice_tf = new JTextField();
		bsBookPrice_tf.setText("\uAC00\uACA9");
		bsBookPrice_tf.setBounds(44, 170, 269, 35);
		basketPanel.add(bsBookPrice_tf);
		bsBookPrice_tf.setColumns(10);
		
		JButton buyIt_btn = new JButton("\uAD6C\uB9E4\uD558\uAE30");
		buyIt_btn.setBounds(44, 449, 195, 53);
		buyIt_btn.setForeground(Color.WHITE);
		buyIt_btn.setBackground(Color.PINK);
		basketPanel.add(buyIt_btn);
		
		JButton buyCancel_btn = new JButton("\uCDE8\uC18C");
		buyCancel_btn.setBounds(277, 449, 195, 53);
		buyCancel_btn.setForeground(Color.WHITE);
		buyCancel_btn.setBackground(Color.PINK);
		basketPanel.add(buyCancel_btn);
		
		JLabel lblNewLabel = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(126, 10, 249, 53);
		basketPanel.add(lblNewLabel);
		
	}
}
