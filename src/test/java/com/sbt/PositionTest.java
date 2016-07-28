package com.sbt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PositionTest {

	@Test
	public void validatePosistion() {
		String description = "Indirect";
		Position position = Position.getPosition(description);
		assertEquals(position.getDescription(), description);
	}
}
