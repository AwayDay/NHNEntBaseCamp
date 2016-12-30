package com.nhnent.awayday.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.nhnent.awayday.controller.MyRestController;

public class TestCase {
	static MyRestController myRestController;
	
	@BeforeClass
	public static void beforeTask(){
		myRestController = new MyRestController();
	}
	
	@Test
	public void testIsEmail(){
		assertTrue(myRestController.isEmail("test@test.com"));
	}
	
	@Test
	public void testIsEmail2(){
		assertFalse(myRestController.isEmail("test@"));
	}
	
	@Test
	public void testIsEmail3(){
		assertFalse(myRestController.isEmail("test"));
	}
	@Test
	public void testIsEmail4(){
		assertFalse(myRestController.isEmail("@"));
	}
	@Test
	public void testIsEmail5(){
		assertFalse(myRestController.isEmail("@test.com"));
	}
	@Test
	public void testIsEmail6(){
		assertFalse(myRestController.isEmail(""));
	}
	
	/////////////////////////////////////////////////
	
	@Test
	public void testIsCorrectForm(){
		assertFalse(myRestController.isCorrectForm("", "", ""));
	}
	@Test
	public void testIsCorrectForm1(){
		assertFalse(myRestController.isCorrectForm("asd", "", ""));
	}
	@Test
	public void testIsCorrectForm2(){
		assertFalse(myRestController.isCorrectForm("", "123wqe", ""));
	}
	@Test
	public void testIsCorrectForm3(){
		assertFalse(myRestController.isCorrectForm("", "", "123wqe"));
	}
	@Test
	public void testIsCorrectForm4(){
		assertFalse(myRestController.isCorrectForm("123wqe", "123wqe", ""));
	}
	@Test
	public void testIsCorrectForm5(){
		assertFalse(myRestController.isCorrectForm("123wqe", "", "123wqe"));
	}
	@Test
	public void testIsCorrectForm6(){
		assertFalse(myRestController.isCorrectForm("", "123wqe", "123wqe"));
	}
	@Test
	public void testIsCorrectForm7(){
		assertTrue(myRestController.isCorrectForm("123wqe", "123wqe", "123wqe"));
	}
	
	/////////////////////////////////////////////////////

}
