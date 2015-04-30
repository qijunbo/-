package com.db.crud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.db.crud.anno.Collection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

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
		// TODO Auto-generated method stub

	}

	private MongoDatabase database;

	public MongoCRUDService() {
	}

	public MongoCRUDService(MongoDatabase database) {
		this.database = database;
	}

	public MongoDatabase getDatabase() {
		if (database == null) {
			database = DefaultDatabase.getDB();
		}
		return database;
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

	public String insertOne(Class<?> template, Document doc) {
		Collection coll = template.getAnnotation(Collection.class);

		String collection = coll == null ? template.getSimpleName() : (coll
				.name() == null ? template.getSimpleName() : coll.name());
		ObjectId id = (ObjectId) doc.get(DocumentHelper.ID);

		if (id == null) {
			id = new ObjectId();
			doc.append(DocumentHelper.ID, id);
		}
		getDatabase().getCollection(collection).insertOne(doc);
		return id.toHexString();

	}

	public <T> List<T> find(Class<T> template, Bson filter) {

		Collection coll = template.getAnnotation(Collection.class);

		String collection = coll == null ? template.getSimpleName() : (coll
				.name() == null ? template.getSimpleName() : coll.name());

		FindIterable<Document> docs = getDatabase().getCollection(collection)
				.find(filter);
		List<T> results = new ArrayList<T>();
		Iterator<Document> iterator = docs.iterator();
		while (iterator.hasNext()) {
			results.add(DocumentHelper.parse(iterator.next(), template));
		}

		return results;

	}

	public <T> T findOne(Class<T> template, Bson filter) {

		Collection coll = template.getAnnotation(Collection.class);

		String collection = coll == null ? template.getSimpleName() : (coll
				.name() == null ? template.getSimpleName() : coll.name());

		FindIterable<Document> docs = getDatabase().getCollection(collection)
				.find(filter);
		Iterator<Document> iterator = docs.iterator();
		while (iterator.hasNext()) {
			return DocumentHelper.parse(iterator.next(), template);
		}

		return null;

	}

}
