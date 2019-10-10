package item_manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ModifyItem")
public class ModifyItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyItem() {
        super();
    }
    int iid;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession sess = request.getSession();
		String msg = "";
		if(request.getParameter("msg") != null) {
			msg = request.getParameter("msg");
		}
		
		if(request.getParameter("iid") != null) {
			iid = Integer.parseInt(request.getParameter("iid"));
		}
		
		
		int iqty = 0  ;
		String iname = "";
		
		if(sess.getAttribute("iname") !=null) {
			iname = (String) sess.getAttribute("iname");
			sess.removeAttribute("iname");
		}
		
		if(sess.getAttribute("iqty") !=null) {
			iqty = (Integer) sess.getAttribute("iqty");
			sess.removeAttribute("iqty");
		}

		
		out.println("<center>");
		out.println("<span style='color:red;'>"+msg+"</span>");
		out.println("<form method=post action=ModifyItem>");
		out.println("<h2>update item details</h2>");
		out.println("Item Name: <input type=text name=iname value=\""+iname+"\"><br><br>");
		out.println("Quantity: <input type=text name=iqty value=\""+iqty+"\"><br><br>");
		out.println("<input type=submit value=Update>");
		out.println("</form>");
		out.println("</center>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession();
		if(Helper.isItemNameValid(request.getParameter("iname")) && 
				Helper.isItemQtyValid(request.getParameter("iqty"))) {
			
			String iname = request.getParameter("iname");
			int iqty = Integer.parseInt(request.getParameter("iqty"));
			int uid = (Integer) sess.getAttribute("uid");
			Item i = new Item(iid,iname,iqty,uid);
			DB_Access db = new DB_Access();
			db.modifyItems(i);
			response.sendRedirect("Home?msg1=Item Updated");
	}
		else {
			response.sendRedirect("ModifyItem?msg");
		}

}}
