package Commons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JTextField;

public class Commons {
		//Field
		//Constructor
		//Method
	/** ���� ���� ��Ʈ�� �ٲټż� ���ø� �˴ϴ� **/
	/** ��Ʈ **/
	public static Font getFont() {
		Font font = new Font("LG PC", Font.PLAIN, 13);
		return font;
	}
	public static Font getFont(int num) {
		Font font = new Font("LG PC", Font.PLAIN, num);
		return font;
	}	
	
	/** �� **/
	public static Label getMsg(String msg) {
			Font font = new Font("LG PC", Font.PLAIN, 10);
			Label label = new Label(msg);
			label.setFont(font);
			return label;
	}
	
	
	
	
}
