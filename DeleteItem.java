package item_manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteItem() {
        super();
    }
    int iid;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		if(request.getParameter("iid") !=null) {
			iid = Integer.parseInt(request.getParameter("iid"));
		}
		int uid =(Integer) sess.getAttribute("uid");
		DB_Access db = new DB_Access();
		db.deleteItems(iid, uid);
		response.sendRedirect("Home?msg1=Item Deleted");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

}
