package com.db.crud;

import static com.mongodb.client.model.Filters.eq;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.db.model.Agenda;
import com.db.model.User;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

/**
 * Java MongoDB : Convert JSON data to DBObject
 * 
 */

public class Example {

	public static void main(String[] args) {

		try {

			User user = new User();
			user.setFirstname("tony");
			Agenda a1 = new Agenda();
			a1.setTitle("Title");
			Agenda a2 = new Agenda();
			a2.setTitle("Hello");
			user.setAgendas(Arrays.asList(a1, a2));

			Gson gson = new Gson();
			System.out.println(gson.toJson(user));

			Document doc = new Document("name", "MongoDB")
					.append("type", new Date())
					.append("count", 1)
					.append("info",
							Arrays.asList(new Document("x", 203).append("y",
									102)));
			System.out.println(doc.toJson());

			Object o = JSON.parse(doc.toJson());

			System.out.println(gson.toJson(o));
			if (gson != null) {
				return;
			}

			MongoClient client = new MongoClient();
			MongoDatabase database = client.getDatabase("test");
			MongoCRUDService notyet = new MongoCRUDService(database);
			Map values = new HashMap();
			values.put("start", new Date());
			values.put("duration", 12);
			values.put("breakout", 12.47);
			values.put("topic", "sdfd");

			// notyet.insertOne(Item.class, values);
			Bson filter = eq("title", "Day1");
			// FindIterable<Agenda> docs = notyet.find(Agenda.class, filter);
			// Iterator<Agenda> results = docs.iterator();
			// while(results.hasNext()){
			// results.next();
			// }

			// System.out.println(Agenda.class.getAnnotation(Collection.class));

		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}
