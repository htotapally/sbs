package com.sbt;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sbt.mongo.MongoManager;

public class SbtReadTest extends SbtAbstractTest {

	@Test
	public void validateRead() {
		String firstName = this.getFirstName();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("firstName", firstName);
		
		MongoManager mongoManager = MongoManager.getInstance();
		DBObject[] dbObjects = mongoManager.getObject(searchQuery);
		assertNotNull(dbObjects);
		System.out.println(dbObjects.length);
		assertTrue(1 == dbObjects.length);
		System.out.println("dbObject: " + dbObjects[0]);
		
		Object bson = dbObjects[0].get("firstName");
		assertEquals(bson.toString(), firstName);
	}
	
}
