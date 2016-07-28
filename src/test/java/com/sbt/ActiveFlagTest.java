package com.sbt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ActiveFlagTest {

	@Test
	public void validateActiveFlag() {
		String activeFlagString = "Active";
		ActiveFlag activeFlag = ActiveFlag.getActiveFlag(activeFlagString);
		assertEquals(activeFlag.getActiveFlag() , activeFlagString);
	}
	
}
