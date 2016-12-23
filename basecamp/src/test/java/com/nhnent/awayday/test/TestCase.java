package com.nhnent.awayday.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.nhnent.awayday.MyRestController;

public class TestCase {
	@Test
	public void testIsEmail(){
		MyRestController myRestController = new MyRestController();
		assertTrue(myRestController.isEmail("test@test.com"));
	}

}
