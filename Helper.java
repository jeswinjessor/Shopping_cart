package item_manager;

public class Helper {
	public static boolean isValueValid(String val) {
		boolean isValid = true;
		
		if(val.trim().equalsIgnoreCase("")) isValid = false;
		if(val.trim().length() < 5) isValid = false;
		
		return isValid;
	}
	public static boolean isItemNameValid(String val) {
		boolean isValid = true;
		if(val.trim().equalsIgnoreCase("")) isValid = false;
		return isValid;
	}
	
	public static boolean isItemQtyValid(String val) {
		boolean isValid = true;
		try {
			Integer.parseInt(val);
		}
		catch(Exception e) {
			isValid = false;
		}
		return isValid;
	}
	
//	public static boolean isQtyNumber(int input_qty) {
//		boolean success = true;
//        if ((input_qty >= 48 && input_qty <= 57)) {
//        	success = true;
//        }
//        else {
//        	success = false;
//        } 
//		
//		return success;
//		
//	}
	
	public static int validateNamePass(String uname, String upass) {		
		DB_Access db = new DB_Access();
		int uid = db.checkUserLogin(uname, upass);
		
		return uid;
	}

	public static boolean isNameValid(String name) {
		boolean isValid = true;
		try {
			Integer.parseInt(name);
			isValid = false;
		}catch(Exception e) {
			isValid = true;
		}
		return isValid;
	}


}







