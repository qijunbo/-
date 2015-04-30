package iagenda.servlet;

import static com.mongodb.client.model.Filters.eq;
import iagenda.service.AgendaService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.conversions.Bson;

import com.db.model.Agenda;
import com.db.model.User;

@WebServlet(name = "agenda", urlPatterns = { "/agenda" })
public class AgendaServlet extends HttpServlet {

	private AgendaService service;

	private static final long serialVersionUID = -8892622115326597538L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");

		String agendaid = req.getParameter("id");
		
		Bson filter = eq("title", "Day1");

		List<Agenda> agendas = getService().find(Agenda.class, filter);
		req.getSession().setAttribute("agendas", agendas);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			getService().insertOne(Agenda.class, req.getParameterMap());

			req.getRequestDispatcher("/item.jsp").forward(req, resp);
		} catch (Exception e) {
			throw e;
			// TODO redirect to error page.
		}

	}

	public AgendaService getService() {
		if (service == null) {
			service = new AgendaService();
		}
		return service;
	}

	public void setService(AgendaService service) {
		this.service = service;
	}

}
