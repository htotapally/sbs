package com.sbt;

import static org.junit.Assert.*;

import java.util.Date;

import org.bson.BSONObject;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sbt.mongo.MongoManager;

public class SbtDeleteTest extends SbtAbstractTest {

	@Test
	public void validateDelete() {
		String firstName = this.getFirstName();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("firstName", firstName);

		MongoManager mongoManager = MongoManager.getInstance();
		mongoManager.delete(searchQuery);
		
		DBObject[] dbObjects = mongoManager.getObject(searchQuery);
		assertNotNull(dbObjects);
		assertTrue(0 == dbObjects.length);

	}
	
}
