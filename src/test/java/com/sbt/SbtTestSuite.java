package com.sbt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SbtCreateTest.class,
	SbtReadTest.class,
	SbtUpdateTest.class,
	SbtDeleteTest.class
})

public class SbtTestSuite {

}
