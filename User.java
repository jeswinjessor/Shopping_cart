package item_manager;

public class User {
	
	private int uid;
	private String fname;
	private String uname;
	private String upass;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int uid, String fname, String uname, String upass) {
		super();
		this.uid = uid;
		this.fname = fname;
		this.uname = uname;
		this.upass = upass;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}

}
