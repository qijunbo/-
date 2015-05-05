package iagenda.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.crud.MongoCRUDService;
import com.db.model.User;

@WebServlet(name = "user", urlPatterns = { "/user" })
public class UserServlet extends HttpServlet {

	private MongoCRUDService crudService;

	private static final long serialVersionUID = -8892622115326597538L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			String[] passwords = req.getParameterMap().get("password");
			if (passwords.length >= 2 & passwords[0].equals(passwords[1])) {
				getCrudService().insertOne(User.class, req.getParameterMap());
				resp.sendRedirect("index.jsp");
				// req.getRequestDispatcher("/index.jsp").forward(req, resp);
			} else {
				req.setAttribute("error", "Password not idential!");
				req.getRequestDispatcher("/user.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			throw e;
			// TODO redirect to error page.
		}

	}

	public MongoCRUDService getCrudService() {
		if (crudService == null) {
			crudService = new MongoCRUDService();
		}

		return crudService;
	}

	public void setCrudService(MongoCRUDService crudService) {
		this.crudService = crudService;
	}

}
