package iagenda.rest.servlet;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import iagenda.common.IOUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.db.crud.MongoCRUDService;
import com.db.model.User;

@WebServlet(name = "restagenda", urlPatterns = { "/rest/agenda" })
public class RestAgendaServlet extends HttpServlet {

	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final long serialVersionUID = 8449722473845606740L;
	MongoCRUDService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Bson filter = and(eq(EMAIL, req.getParameter(EMAIL)),
				eq(PASSWORD, req.getParameter(PASSWORD)));
		Document user = getService().getDatabase()
				.getCollection(User.class.getSimpleName()).find(filter).first();
		String result = "";
		if (user != null) {
			result = user.toJson();
		}
		resp.getOutputStream().write(result.getBytes());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String json = IOUtil.fromStream(req.getInputStream());
		Document document = Document.parse(json);

		Bson filter = and(eq(EMAIL, document.getString(EMAIL)),
				eq(PASSWORD, document.getString(PASSWORD)));

		Document user = getService().getDatabase()
				.getCollection(User.class.getSimpleName()).find(filter).first();
		String result = "";
		if (user != null) {
			result = user.toJson();
		}
		resp.getOutputStream().write(result.getBytes());
	}

	public MongoCRUDService getService() {
		if (service == null) {
			service = new MongoCRUDService();
		}
		return service;
	}

	public void setService(MongoCRUDService service) {
		this.service = service;
	}

}
