package iagenda.servlet;

import static com.mongodb.client.model.Filters.*;
import iagenda.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.conversions.Bson;

import com.db.model.User;

@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	private UserService service;

	private static final long serialVersionUID = -8892622115326597538L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			String email = req.getParameter("email");
			String password = req.getParameter("password");
			Bson filter = and(eq("email", email), eq("password", password));
			User user = getService().findOne(User.class, filter);
			if (user != null) {
				req.getSession().setAttribute("user", user);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("/404.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			throw e;
			// TODO redirect to error page.
		}
	}

	public UserService getService() {
		if (service == null) {
			service = new UserService();
		}
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

}
