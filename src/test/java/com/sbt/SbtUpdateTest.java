package com.sbt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sbt.ActiveFlag;
import com.sbt.Position;
import com.sbt.mongo.MongoManager;

public class SbtUpdateTest extends SbtAbstractTest {

	@Test
	public void validateUpdate() {
		
		String newEmailAddress = null;
		
		BasicDBObject query = new BasicDBObject();
		query.put("firstName", this.getFirstName());

		BasicDBObject newDocument = new BasicDBObject();
        Properties prop = new Properties();
        InputStream inputStream = SbtAbstractTest.class.getResourceAsStream("./resources/updatetestdata.txt");
        try {
			prop.load(inputStream);
			
			//  First Name
			String firstName = prop.getProperty("firstName");
			newDocument.put("firstName", firstName);
			
			//  Last Name
			String lastName = prop.getProperty("lastName");
			newDocument.put("lastName", lastName);
			
			//  Middle Initial
			String middleInitial = prop.getProperty("middleInitial");
			newDocument.put("middleInitial", middleInitial);
			
			//  Email Address
			newEmailAddress = prop.getProperty("emailAddress");
			newDocument.put("emailAddress", newEmailAddress);
			
			//  Phone Number
			String phoneNumber = prop.getProperty("phoneNumber");
			newDocument.put("phoneNumber", phoneNumber);
			
			//  Position Category (Indirect, Direct, Program Manager, Director, Executive)
			Position position = Position.getPosition(prop.getProperty("position"));
			newDocument.put("position", position.getDescription());
			
			String pattern = "MM/dd/yyyy";
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			//  Date Hired
			Date dateHired = null;
			try {
				dateHired = dateFormat.parse(prop.getProperty("dateHired"));
				newDocument.put("dateHired", dateHired);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			//  Address 1
			String address1 = prop.getProperty("address1");
			newDocument.put("address1", address1);
			
			//  Address 2
			String address2 = prop.getProperty("address2");
			newDocument.put("address2", address2);
			
			//  City
			String city = prop.getProperty("city");
			newDocument.put("city", city);
			
			//  State
			String state = prop.getProperty("state");
			newDocument.put("state", state);
			
			//  Zip Code
			String zipCode = prop.getProperty("zipCode");
			newDocument.put("zipCode", zipCode);
			
			//  Active Flag
			ActiveFlag activeFlag = ActiveFlag.getActiveFlag(prop.getProperty("activeFlag"));
			newDocument.put("activeFlag", activeFlag.getActiveFlag());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
					
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		
		MongoManager mongoManager = MongoManager.getInstance();
		mongoManager.updateData(query, newDocument);	
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("firstName", this.getFirstName());
		
		DBObject[] dbObjects = mongoManager.getObject(searchQuery);
		assertNotNull(dbObjects);
		System.out.println(dbObjects.length);
		assertTrue(1 == dbObjects.length);
		System.out.println("dbObject: " + dbObjects[0]);
		
		Object bson = dbObjects[0].get("emailAddress");
		assertEquals(bson.toString(), newEmailAddress);

	}
	
}
