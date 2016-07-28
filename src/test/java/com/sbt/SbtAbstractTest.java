package com.sbt;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Before;

import com.sbt.ActiveFlag;
import com.sbt.Position;

public class SbtAbstractTest {

	
	// First Name
	private String firstName;
	
	// Last Name
	private String lastName;
	
	// Middle Initial
	private String middleInitial;
	
	// Email Address
	private String emailAddress;
	
	// Phone Number
	private String phoneNumber;
	
	// Position Category (Indirect, Direct, Program Manager, Director, Executive)
	private Position position;
	
	// Date Hired
	private Date dateHired;
	
	// Address 1
	private String address1;
	
	// Address 2
	private String address2;
	
	// City
	private String city;
	
	// State
	private String state;
	
	
	// Zip Code
	private String zipCode;
	
	// Active Flag
	private ActiveFlag activeFlag;
	
	@Before
    public void initObjects() {
        Properties prop = new Properties();
        InputStream inputStream = SbtAbstractTest.class.getResourceAsStream("./resources/testdata.txt");
        try {
			prop.load(inputStream);
			
			//  First Name
			this.firstName = prop.getProperty("firstName");
			
			//  Last Name
			this.lastName = prop.getProperty("lastName");

			//  Middle Initial
			this.middleInitial = prop.getProperty("middleInitial");

			//  Email Address
			this.emailAddress = prop.getProperty("emailAddress");

			//  Phone Number
			this.phoneNumber = prop.getProperty("phoneNumber");

			//  Position Category (Indirect, Direct, Program Manager, Director, Executive)
			this.position = Position.getPosition(prop.getProperty("position"));

			String pattern = "MM/dd/yyyy";
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			//  Date Hired
			try {
				this.dateHired = dateFormat.parse(prop.getProperty("dateHired"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			//  Address 1
			this.address1 = prop.getProperty("address1");

			//  Address 2
			this.address2 = prop.getProperty("address2");

			//  City
			this.city = prop.getProperty("city");

			//  State
			this.state = prop.getProperty("state");


			//  Zip Code
			this.zipCode = prop.getProperty("zipCode");

			//  Active Flag
			this.activeFlag = ActiveFlag.getActiveFlag(prop.getProperty("activeFlag"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

    }
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Position getPosition() {
		return position;
	}

	public Date getDateHired() {
		return dateHired;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public ActiveFlag getActiveFlag() {
		return activeFlag;
	}

	/*
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
	*/
	
}
