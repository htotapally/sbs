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
import com.sbt.ActiveFlag;
import com.sbt.Position;
import com.sbt.mongo.MongoManager;

public class SbtCreateTest extends SbtAbstractTest {

	@Test
	public void validateCreate() {
	
		BasicDBObject document = new BasicDBObject();

		// First Name
		String firstName = this.getFirstName();
		document.put("firstName", firstName);

		// Last Name
		String lastName = this.getLastName();
		document.put("lastName", lastName);
		
		// Middle Initial
		String middleInitial = this.getMiddleInitial();
		document.put("middleInitial", middleInitial);
		
		// Email Address
		String emailAddress = this.getEmailAddress();
		document.put("emailAddress", emailAddress);
		
		// Phone Number
		String phoneNumber = this.getPhoneNumber();
		document.put("phoneNumber", phoneNumber);
		
		// Position Category (Indirect, Direct, Program Manager, Director, Executive)
		Position position = this.getPosition();
		document.put("position", position.getDescription());
		
		// Date Hired
		Date dateHired = this.getDateHired();
		document.put("dateHired", dateHired);
		
		// Address 1
		String address1 = this.getAddress1();
		document.put("address1", address1);
		
		// Address 2
		String address2 = this.getAddress2();
		document.put("address2", address2);
		
		// City
		String city = this.getCity();
		document.put("city", city);
		
		// State
		String state = this.getState();
		document.put("state", state);
		
		// Zip Code
		String zipCode = this.getZipCode();
		document.put("zipCode", zipCode);
		
		// Active Flag
		ActiveFlag activeFlag = this.getActiveFlag();
		document.put("activeFlag", activeFlag.getActiveFlag());

		MongoManager mongoManager = MongoManager.getInstance();
		mongoManager.addData(document);
	}
	
}
