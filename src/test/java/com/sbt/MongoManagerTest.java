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

public class MongoManagerTest {

	@Before
    public void initObjects() {
        System.out.println("Executing");
    }
	
	@Test
    public void validateMongoClientConnection() {
		MongoManager mongoManager = MongoManager.getInstance();
		MongoClient mongoClient = mongoManager.getMongoClient();
		
		assertNotNull(mongoClient);
		
		if(mongoClient != null) {
			mongoClient.close();
		}
    }
	
	@Test
	public void validateDb() {
		MongoManager mongoManager = MongoManager.getInstance();
		DB db = mongoManager.getDb();
		assertEquals("sbt", db.getName());
	}

	@Test
	public void validateCollection() {
		MongoManager mongoManager = MongoManager.getInstance();
		DBCollection dbCollection = mongoManager.getDBCollection();
		assertEquals("sbtcol", dbCollection.getName());
	}
	
	@Test
	public void validateCreate() {
		MongoManager mongoManager = MongoManager.getInstance();
		DBCollection dbCollection = mongoManager.getDBCollection();
		
		String name = "hara10";
		BasicDBObject document = new BasicDBObject();
		document.put("name", name);
		document.put("age", 30);
		document.put("createdDate", new Date());
		dbCollection.insert(document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", name);

		DBCursor cursor = dbCollection.find(searchQuery);

		assert(cursor.hasNext());
		
		DBObject dbObject = cursor.next();
		
		Object bson = dbObject.get("name");

		assertEquals(bson.toString(), name);
		
	}
	
}
