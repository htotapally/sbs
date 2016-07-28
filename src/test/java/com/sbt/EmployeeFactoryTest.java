package com.sbt;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.sbt.model.Employee;
import com.sbt.model.EmployeeFactory;

public class EmployeeFactoryTest {

	@Test
    public void validateJsonToEmployeeConversion() {
	       InputStream inputStream = EmployeeFactoryTest.class.getResourceAsStream("./resources/employee.json");
	       try {
			int available = inputStream.available();
			byte[] bytes = new byte[available];
			inputStream.read(bytes);
			
			String string = new String(bytes);
			JSON j = null; // new JSON(string);
			BasicDBObject dbObject = (BasicDBObject) JSON.parse(string);
			System.out.println(dbObject);
			
			Employee employee = EmployeeFactory.getEmployee(dbObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	}
}
