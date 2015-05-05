package com.db.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.db.crud.anno.Collection;
import com.db.model.Agenda;
import com.db.model.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

/**
 * @author qijunbo
 * 
 *         In a lot of OR mapping tool, values are populated into a POJO, and
 *         then storied into data base, but this class is different. In this
 *         class, POJO class is only used as a template, we get value for each
 *         field defined in template(parameter template) from the map (
 *         parameter values) to form a instance of org.bson.Document.
 * 
 */

public class MongoCRUDService {

	public static void main(String[] args) {
		User user = new User();
		user.setFirstname("tony");
		Agenda a1 = new Agenda();
		a1.setTitle("Title");
		Agenda a2 = new Agenda();
		a2.setTitle("Hello");
		user.setAgendas(Arrays.asList(a1, a2));

		MongoCRUDService service = new MongoCRUDService();
		service.insertOne(User.class, DocumentHelper.buildDocument(user));
	}

	private MongoDatabase database;

	public MongoCRUDService() {
	}

	public MongoCRUDService(MongoDatabase database) {
		this.database = database;
	}

	public <T> List<T> find(Class<T> template, Bson filter) {

		FindIterable<Document> docs = getCollection(template).find(filter);
		List<T> results = new ArrayList<T>();
		Iterator<Document> iterator = docs.iterator();
		while (iterator.hasNext()) {
			results.add(DocumentHelper.parse(iterator.next(), template));
		}
		return results;
	}

	public <T> T findOne(Class<T> template, Bson filter) {
		List<T> results = find(template, filter);
		return find(template, filter).size() > 0 ? results.get(0) : null;
	}

	public <T> T findOneAndUpdate(Class<T> template, Bson filter, Bson update) {
		Document doc = getCollection(template).findOneAndUpdate(filter, update);
		return DocumentHelper.parse(doc, template);
	}

	public void findOneAndUpdate(Bson filter, Object pojo) {
		Document doc = DocumentHelper.buildDocument(pojo);
		Document update = new Document("$set", doc);
		doc = getCollection(pojo.getClass()).findOneAndUpdate(filter, update);
	}

	public MongoCollection<Document> getCollection(Class<?> template) {

		Collection coll = template.getAnnotation(Collection.class);

		String collection = coll == null ? template.getSimpleName() : (coll
				.name() == null ? template.getSimpleName() : coll.name());

		return getDatabase().getCollection(collection);
	}

	public MongoDatabase getDatabase() {
		if (database == null) {
			database = DefaultDatabase.getDB();
		}
		return database;
	}

	public String insertOne(Class<?> template, Document doc) {

		if (doc.isEmpty()) {
			throw new NullDocumentException(template.getSimpleName());
		}

		ObjectId id = (ObjectId) doc.get(DocumentHelper.ID);

		if (id == null) {
			id = new ObjectId();
			doc.append(DocumentHelper.ID, id);
		}
		getCollection(template).insertOne(doc);
		return id.toHexString();

	}

	/**
	 * Get value of field defined in template to form a org.bson.Document, and
	 * save it to the default Mongodb.
	 * 
	 * @param template
	 *            a POJO Class. used to define the domain model.
	 * @param values
	 *            a map contain all the values of domain model, usually a Map
	 *            from the HttpServletRequest. use the method
	 *            HttpServletRequest.getParameterMap()
	 */
	public String insertOne(Class<?> template, Map<String, ?> values) {

		// TODO get Annotation of this field , if @Reference, then save this
		// field as a reference to another document.

		Document doc = DocumentHelper.buildDocument(template, values);

		return insertOne(template, doc);

	}

	public String insertOne(Object pojo) {

		Document doc = DocumentHelper.buildDocument(pojo);

		ObjectId id = (ObjectId) doc.get(DocumentHelper.ID);

		getCollection(pojo.getClass()).insertOne(doc);

		return id.toHexString();

	}

}
