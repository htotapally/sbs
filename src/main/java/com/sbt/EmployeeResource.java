package com.sbt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sbt.model.Employee;
import com.sbt.model.EmployeeFactory;
import com.sbt.mongo.MongoManager;

import javax.ws.rs.DELETE;

/**
 * Root resource (exposed at "employee" path)
 */
@Path("employee")
public class EmployeeResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees() {
		BasicDBObject searchQuery = new BasicDBObject();
		MongoManager mongoManager = MongoManager.getInstance();
		
		List<Employee> list = new LinkedList<>();
		
		DBObject[] dbObjects = mongoManager.getObject(searchQuery);
		
    	for(DBObject dbObject : dbObjects) {
    		list.add(EmployeeFactory.getEmployee(dbObject));
    	}
        return list;
    }
    
    @GET
    @Path("/{lastName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees(@PathParam(LASTNAME) String lastName) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(LASTNAME, lastName);
		
		MongoManager mongoManager = MongoManager.getInstance();
		DBObject[] dbObjects = mongoManager.getObject(searchQuery);
    	
		List<Employee> list = new LinkedList<>();
	  	for(DBObject dbObject : dbObjects) {
    		list.add(EmployeeFactory.getEmployee(dbObject));
    	}
	  	
        return list;
    }
    
    @DELETE
	@Produces( { "application/json" })
    @Path("/{lastName}")
	public Response deleteByLastName (@PathParam(LASTNAME) String lastName) {
    	Response response = Response.status(Status.PRECONDITION_FAILED).build();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("lastName", lastName);

		MongoManager mongoManager = MongoManager.getInstance();
		mongoManager.delete(searchQuery);
    	
		response = Response.status(Status.OK).build();
    	return response;
    }
    
    @PUT
	@Produces( { "application/json" })
	public Response updateEmployee (
 		    // First Name
 			@FormParam(FIRSTNAME) String firstName
 			
 			// Last Name
 			, @FormParam(LASTNAME) String lastName
 			
 			// Middle Initial
 			, @FormParam(MIDDLEINITIAL) String middleInitial
 			
 			// Email Address
 			, @FormParam(EMAILADDRESS) String emailAddress
 			
 			// Phone Number
 			, @FormParam(PHONENUMBER) String phoneNumber
 			
 			// Position Category (Indirect, Direct, Program Manager, Director, Executive)
 			, @FormParam(POSITION) String position
 			
 			// Date Hired
 			, @FormParam(DATEHIRED) String dateHired
 			
 			// Address 1
 			, @FormParam(ADDRESS1) String address1
 			
 			// Address 2
 			, @FormParam(ADDRESS2) String address2
 			
 			// City
 			, @FormParam(CITY) String city
 			
 			// State
 			, @FormParam(STATE) String state
 			
 			
 			// Zip Code
 			, @FormParam(ZIPCODE) String zipCode
 			
 			// Active Flag
 			, @FormParam(ACTIVEFLAG) String activeFlag
			
			) {
    	Response response = Response.status(Status.PRECONDITION_FAILED).build();
    	
		BasicDBObject query = new BasicDBObject();
		query.put("lastName", lastName);

		BasicDBObject newDocument = new BasicDBObject();	
		//  First Name
		newDocument.put("firstName", firstName);
		
		//  Last Name
		newDocument.put("lastName", lastName);
		
		//  Middle Initial
		newDocument.put("middleInitial", middleInitial);
		
		//  Email Address
		newDocument.put("emailAddress", emailAddress);
		
		//  Phone Number
		newDocument.put("phoneNumber", phoneNumber);
		
		//  Position Category (Indirect, Direct, Program Manager, Director, Executive)
		newDocument.put("position", Position.getPosition(position).toString());
		
		String pattern = "MM/dd/yyyy";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		
		//  Date Hired
		try {
			Date newDateHired = dateFormat.parse(dateHired);
			newDocument.put("dateHired", newDateHired);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//  Address 1
		newDocument.put("address1", address1);
		
		//  Address 2
		newDocument.put("address2", address2);
		
		//  City
		newDocument.put("city", city);
		
		//  State
		newDocument.put("state", state);
		
		//  Zip Code
		newDocument.put("zipCode", zipCode);
		
		//  Active Flag
		ActiveFlag activeFlagNew = ActiveFlag.getActiveFlag(activeFlag);
		newDocument.put("activeFlag", activeFlagNew.getActiveFlag());
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		
		MongoManager mongoManager = MongoManager.getInstance();
		mongoManager.updateData(query, newDocument);	

		response = Response.status(Status.OK).build();
    	return response;
    }
    
    @POST
 	@Produces( { "application/json" })
 	public Response createEmployee (
 		    // First Name
 			@FormParam(FIRSTNAME) String firstName
 			
 			// Last Name
 			, @FormParam(LASTNAME) String lastName
 			
 			// Middle Initial
 			, @FormParam(MIDDLEINITIAL) String middleInitial
 			
 			// Email Address
 			, @FormParam(EMAILADDRESS) String emailAddress
 			
 			// Phone Number
 			, @FormParam(PHONENUMBER) String phoneNumber
 			
 			// Position Category (Indirect, Direct, Program Manager, Director, Executive)
 			, @FormParam(POSITION) String position
 			
 			// Date Hired
 			, @FormParam(DATEHIRED) String dateHired
 			
 			// Address 1
 			, @FormParam(ADDRESS1) String address1
 			
 			// Address 2
 			, @FormParam(ADDRESS2) String address2
 			
 			// City
 			, @FormParam(CITY) String city
 			
 			// State
 			, @FormParam(STATE) String state
 			
 			
 			// Zip Code
 			, @FormParam(ZIPCODE) String zipCode
 			
 			// Active Flag
 			, @FormParam(ACTIVEFLAG) String activeFlag
 			
 			
 			) {
     	Response response = Response.status(Status.PRECONDITION_FAILED).build();
     	
		BasicDBObject document = new BasicDBObject();

		// First Name
		document.put("firstName", firstName);

		// Last Name
		document.put("lastName", lastName);
		
		// Middle Initial
		document.put("middleInitial", middleInitial);
		
		// Email Address
		document.put("emailAddress", emailAddress);
		
		// Phone Number
		document.put("phoneNumber", phoneNumber);
		
		// Position Category (Indirect, Direct, Program Manager, Director, Executive)
		document.put("position", position);
		
		// Date Hired
		document.put("dateHired", dateHired);
		
		// Address 1
		document.put("address1", address1);
		
		// Address 2
		document.put("address2", address2);
		
		// City
		document.put("city", city);
		
		// State
		document.put("state", state);
		
		// Zip Code
		document.put("zipCode", zipCode);
		
		// Active Flag
		document.put("activeFlag", activeFlag);

		MongoManager mongoManager = MongoManager.getInstance();
		mongoManager.addData(document);
		response = Response.status(Status.OK).build();
		
     	return response;
     }
    
    
    // First Name
	public static final String FIRSTNAME = "firstName";
	
	// Last Name
	public static final String LASTNAME = "lastName";
	
	// Middle Initial
	public static final String MIDDLEINITIAL = "middleInitial";
	
	// Email Address
	public static final String EMAILADDRESS = "emailAddress";
	
	// Phone Number
	public static final String PHONENUMBER = "phoneNumber";
	
	// Position Category (Indirect, Direct, Program Manager, Director, Executive)
	public static final String POSITION = "position";
	
	// Date Hired
	public static final String DATEHIRED = "dateHired";
	
	// Address 1
	public static final String ADDRESS1 = "address1";
	
	// Address 2
	public static final String ADDRESS2 = "address2";
	
	// City
	public static final String CITY = "city";
	
	// State
	public static final String STATE = "state";
	
	// Zip Code
	public static final String ZIPCODE = "zipCode";
	
	// Active Flag
	public static final String ACTIVEFLAG = "activeFlag";

}
