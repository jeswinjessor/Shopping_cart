package item_manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewItem")
public class ViewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession sess = request.getSession();
		int iid =-1;
		if(request.getParameter("iid") != null) {
			iid = Integer.parseInt(request.getParameter("iid"));
		}
		DB_Access db = new DB_Access();
		Item i = new Item();
		i.setIid(iid);
		i.setUid((Integer) sess.getAttribute("uid"));
		db.viewItems(iid, i);
		
		out.println("<center>");
		out.println("<h1> "+db.getUserName(i.getUid())+"'s Cart<br>Product Details</h1><br>");
		out.println("<h2> Product: "+i.getItemName()+"</h2><br>");
		out.println("<h3> Quantity: "+i.getQty()+"</h3><br>");
		out.println("<a href=Home><input type=button value=Home></a>");
		out.println("</center>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
