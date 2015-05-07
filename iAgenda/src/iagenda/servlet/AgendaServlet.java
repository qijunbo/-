package iagenda.servlet;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import iagenda.service.AgendaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.db.crud.DocumentHelper;
import com.db.model.Agenda;
import com.db.model.User;

@WebServlet(name = "agenda", urlPatterns = { "/agenda" })
public class AgendaServlet extends HttpServlet {

	private AgendaService service;

	private static final long serialVersionUID = -8892622115326597538L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String title = req.getParameter("title");

		User user = (User) req.getSession().getAttribute("user");
		Bson filter = and(eq("email", user.getEmail()),
				eq("password", user.getPassword()));

		List<Agenda> items = user.getAgendas();
		if (items != null) {
			for (Agenda i : items) {
				if (i.getTitle().equals(title)) {
					items.remove(i);
					getService().findOneAndUpdate(filter, user);
					return;
				}
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");

		String title = req.getParameter("title");
		if (user != null) {
			List<Agenda> agendas = user.getAgendas();
			for (Agenda agenda : agendas) {
				if (agenda.getTitle().equals(title)) {
					req.getSession().setAttribute("agenda", agenda);
					break;
				}
			}
			req.getRequestDispatcher("/agenda.jsp").forward(req, resp);
		} else {

			resp.sendRedirect("index.jsp");

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Document doc = DocumentHelper.buildDocument(Agenda.class,
					req.getParameterMap());
			Agenda agenda = DocumentHelper.parse(doc, Agenda.class);
			User user = (User) req.getSession().getAttribute("user");
			List<Agenda> agendas = user.getAgendas() == null ? new ArrayList<Agenda>()
					: user.getAgendas();
			agendas.add(agenda);
			user.setAgendas(agendas);
			req.getSession().setAttribute("agenda", agenda);

			req.getRequestDispatcher("/agenda.jsp").forward(req, resp);
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
