package item_manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		if(Helper.isItemNameValid(request.getParameter("iname")) &&
				Helper.isItemQtyValid(request.getParameter("iqty"))) {
			String iname = request.getParameter("iname");
			int iqty = Integer.parseInt(request.getParameter("iqty"));
			
			int uid = (Integer) sess.getAttribute("uid");
			
			Item i = new Item(-1, iname, iqty, uid);
			
			DB_Access db = new DB_Access();
			db.insertItem(i);
			response.sendRedirect("Home?msg=item is successfully inserted");
		}
		 if(!Helper.isItemNameValid(request.getParameter("iname"))){
			response.sendRedirect("Home?msg=Please Enter a valid name");
		}
		 if(!Helper.isItemQtyValid(request.getParameter("iqty"))){
			response.sendRedirect("Home?msg=Please enter a valid quantity");
		}
		else {
			response.sendRedirect("Home?msg=Please enter something");
		}
//		if(Helper.isQtyNumber(Integer.parseInt(request.getParameter("iqty")))) {
//			response.sendRedirect("Home>msg=Only digits are allowed as Quantity");
//		}
		
	}

}




