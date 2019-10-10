package item_manager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession sess = request.getSession();
		String msg = "";
		if(request.getParameter("msg") != null) {
			msg = request.getParameter("msg");
		}
		String fname= "", uname = "", upass ="";
		
		if(sess.getAttribute("fname") != null) {
			fname = (String) sess.getAttribute("fname");
			sess.removeAttribute("fname");
		}
		if(sess.getAttribute("uname") != null) {
			uname = (String) sess.getAttribute("uname");
			sess.removeAttribute("uname");
		}
		if(sess.getAttribute("upass") != null) {
			upass = (String) sess.getAttribute("upass");
			sess.removeAttribute("upass");
		}
		
		out.println("<center>");
		out.println("<span style='color:red;'>"+msg+"</span>");
		out.println("<h2>Register Form</h2>");
		out.println("<form method=POST action=Register>");
		out.println("Enter your Full Name: <input type=text name=fname value=\""+fname+"\"><br><br>");
		out.println("Enter a login Name: <input type=text name=uname value=\""+uname+"\"><br><br>");
		out.println("Enter Password: <input type=text name=upass value=\""+upass+"\"><br><br>");
		out.print("<input type=submit value=Register>");
		out.println("</form>");
		out.println("</center>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		if(Helper.isNameValid(request.getParameter("fname")) &&
				Helper.isValueValid(request.getParameter("upass"))) {
			String fname = request.getParameter("fname");
			String uname = request.getParameter("uname");
			String upass = request.getParameter("upass");
			User u = new User(-1,fname,uname,upass);
			DB_Access db = new DB_Access();
			db.newRegistration(u);
			response.sendRedirect("Form?msg=Registration Successful");
		}
		else {
			response.sendRedirect("Register?msg=Invalid Data");
		}
	}
}
