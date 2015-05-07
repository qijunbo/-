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
import com.db.crud.MongoCRUDService;
import com.db.model.Agenda;
import com.db.model.Item;
import com.db.model.User;

@WebServlet(name = "item", urlPatterns = { "/item" })
public class ItemServlet extends HttpServlet {

	private MongoCRUDService service;

	private static final long serialVersionUID = -8892622115326597538L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String start = req.getParameter("start");
		Agenda agenda = (Agenda) req.getSession().getAttribute("agenda");
		List<Item> items = agenda.getItems();
		if (items != null) {
			for (Item i : items) {
				if (i.getStart() == null || i.getStart() == ""
						|| i.getStart().equals(start)) {
					items.remove(i);
					saveAndClear(req, resp);
					return;
				}
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Document doc = DocumentHelper.buildDocument(Item.class,
					req.getParameterMap());
			if (!doc.isEmpty()) {
				Agenda agenda = (Agenda) req.getSession()
						.getAttribute("agenda");
				List<Item> items = agenda.getItems() == null ? new ArrayList<Item>()
						: agenda.getItems();
				items.add(DocumentHelper.parse(doc, Item.class));

				agenda.setItems(items);
			}
			String save = req.getParameter("save");
			if ("save".equals(save)) {
				saveAndClear(req, resp);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("/agenda.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			throw e;
			// TODO redirect to error page.
		}

	}

	private void saveAndClear(HttpServletRequest req, HttpServletResponse resp) {

		User user = (User) req.getSession().getAttribute("user");
		Bson filter = and(eq("email", user.getEmail()),
				eq("password", user.getPassword()));

		getService().findOneAndUpdate(filter, user);

	}

	public MongoCRUDService getService() {
		if (service == null) {
			service = new MongoCRUDService();
		}
		return service;
	}

	public void setService(AgendaService service) {
		this.service = service;
	}

}
