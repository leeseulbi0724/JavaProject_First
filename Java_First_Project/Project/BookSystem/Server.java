package BookSystem;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class Server {
	//Field
		ServerSocket server;	
		static ArrayList<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
		static Vector<String> users = new Vector<String>();
		
		//Constructor
		public Server() {
			try {
				server = new ServerSocket(9000);
				System.out.println("���� ������");
				
				while(true) {
					Socket s = server.accept();
					ServerThread st = new ServerThread(s);
					st.start();
					
					list.add(st.oos);
					System.out.println("Ŭ���̾�Ʈ ���� �Ϸ�");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//������ ��� Ŭ���̾�Ʈ���� �޽��� ����-����ȭ ����
		synchronized static public void broadcast() {
			try {	
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}
