package com.db.crud;

import static com.mongodb.client.model.Filters.eq;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.db.model.Agenda;
import com.db.model.Item;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

/**
 * Java MongoDB : Convert JSON data to DBObject
 * 
 */

public class Example {

	public static void main(String[] args) {

		try {

			MongoClient client = new MongoClient();
			MongoDatabase database = client.getDatabase("test");
			MongoCRUDService notyet = new MongoCRUDService(database);
			Map values = new HashMap();
			values.put("start", new Date());
			values.put("duration", 12);
			values.put("breakout", 12.47);
			values.put("topic", "sdfd");

			//notyet.insertOne(Item.class, values);
			Bson filter = eq("title", "Day1");
			// FindIterable<Agenda> docs = notyet.find(Agenda.class, filter);
			// Iterator<Agenda> results = docs.iterator();
			// while(results.hasNext()){
			// results.next();
			// }

			// System.out.println(Agenda.class.getAnnotation(Collection.class));

			Document doc = new Document("name", "MongoDB")
					.append("type", "database").append("count", 1)
					.append("info", new Document("x", 203).append("y", 102));
			System.out.println(doc.toJson());

		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}
