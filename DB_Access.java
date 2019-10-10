package item_manager;

import java.sql.*;
import java.util.ArrayList;

public class DB_Access implements DB_Vars {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	
	public DB_Access() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uname, upass);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int checkUserLogin(String uname, String upass) {
		int uid = -1; // -1 means the login was not successfull
		
		String sql = "select uid from t_users "+
						"where loginname = '"+uname+"' "+
						"and loginpass = '"+upass+"'";
		
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				uid = rs.getInt(1);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uid;
	}
	
	public String getUserName(int uid) {
		String sql = "select name from t_users where uid = " + uid;
		String name = "";
		
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	public ArrayList<Item> getAllUserItems(int uid) {
		ArrayList<Item> all = new ArrayList<Item>();
		
		String sql = "select iid, itemname, qty, uid from t_items " +
					"where uid = " + uid;
		
		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				all.add(new Item(rs.getInt(1), 
								rs.getString(2), 
								rs.getInt(3), 
								rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return all;
	}
	
	public boolean insertItem(Item i) {
		boolean success = true;
		
		String sql = "insert into t_items(itemname, qty, uid) values(?, ?, ?)";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, i.getItemName());
			pst.setInt(2, i.getQty());
			pst.setInt(3, i.getUid());
			if(pst.executeUpdate() == 0) success = false;
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		
		return success;
	}
	
	
	public boolean newRegistration(User u) {
		boolean success = true;
		String sql = "insert into t_users(LoginName, Name, LoginPass) values(?, ?, ?)";
		try {
				pst = con.prepareStatement(sql);
				pst.setString(1, u.getUname());
				pst.setString(2, u.getFname());
				pst.setString(3, u.getUpass());
				if(pst.executeUpdate() == 0) success = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				success = false;
				e.printStackTrace();
			}
		return success;
		
}
	
	
	public boolean modifyUser(User u) {
		boolean success = true;
		String sql = "UPDATE t_users SET LoginName = ?, Name = ?, LoginPass = ? WHERE uid = ? ";
		try {
				pst = con.prepareStatement(sql);
				pst.setString(1, u.getUname());
				pst.setString(2, u.getFname());
				pst.setString(3, u.getUpass());
				pst.setInt(4, u.getUid());
				if(pst.executeUpdate() == 0) success = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				success = false;
				e.printStackTrace();
			}
		return success;
		
}
	
	public boolean viewItems(int iid, Item i) {
		boolean success = true;
		String sql = "SELECT ItemName, Qty FROM t_items WHERE iid = "+iid;
		ResultSet rs;
		try {
			
			rs = st.executeQuery(sql);
			while(rs.next()) {
				i.setItemName(rs.getString(1));
				i.setQty(rs.getInt(2));	
			}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				success = false;
				e.printStackTrace();
			}
		return success;
		
}
	
	public  boolean modifyItems(Item i) {
		boolean success = true;
		String sql = "UPDATE t_items SET ItemName = ?, Qty = ? WHERE  iid = ? ";
		try {
				pst = con.prepareStatement(sql);
				pst.setString(1, i.getItemName());
				pst.setInt(2, i.getQty());
				pst.setInt(3, i.getIid());
				if(pst.executeUpdate() == 0) success = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				success = false;
				e.printStackTrace();
			}
		return success;
		
}
	public boolean deleteItems(int iid,int uid) {
		boolean success = true;
		String sql = "DELETE FROM t_items where iid = ? AND uid = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, iid);
			pst.setInt(2, uid);
			if(pst.executeUpdate() == 0) success = false;
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}



}




