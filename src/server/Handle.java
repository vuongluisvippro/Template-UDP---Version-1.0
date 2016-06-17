package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectdb.MyConnectDB;

@SuppressWarnings("all")
public class Handle {

	private Connection conn;
	private MyConnectDB mConnect;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private String X_REQUEST = "SoMui";
	private String X_TABLE = "vacxin";
	private String X_CONDITION = "TenVacxin";
	
	public Handle(){
		mConnect = new MyConnectDB();
	}
	public int checkVX(String nameVX) {
		int kq = 0;
		Connection conn = mConnect.getConnectMySQL();
		String sql = "SELECT "+X_REQUEST+" FROM "+X_TABLE+" WHERE "+X_CONDITION+" = ? LIMIT 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nameVX);
			rs = pst.executeQuery();
			while(rs.next()){
				kq = rs.getInt(X_REQUEST);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		return kq;
	}
}
