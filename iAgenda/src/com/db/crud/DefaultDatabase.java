package com.db.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DefaultDatabase {

	static MongoDatabase database;

	private DefaultDatabase() {

	}

	public static MongoDatabase getDB() {

		if (database == null) {
			MongoClient client = new MongoClient();
			database = client.getDatabase("test");
		}
		return database;
	}
	

}
