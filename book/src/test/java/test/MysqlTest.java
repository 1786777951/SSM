package test;

import java.sql.DriverManager;

public class MysqlTest {
	private static String url = "jdbc:mysql://localhost:3306/graduationproject?useSSL=false&serverTimezone=Asia/Shanghai";//���ݿ�����ַ
	private static String driver = "com.mysql.cj.jdbc.Driver";//����·��
	private static String username = "root";
	private static String passworld = "Zjf@13713428454";
	    
	public static void main(String[] args) throws Exception {
	    Class.forName(driver).newInstance();

	    //��������ӳɹ������ӡ����
	   System.out.println(DriverManager.getConnection(url, username, passworld));
	}
}
