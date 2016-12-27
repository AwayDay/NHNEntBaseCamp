package com.nhnent.awayday.test;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.nhnent.awayday.MyRestController;

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
		assertTrue(!myRestController.isEmail("test@"));
	}
	
	@Test
	public void testIsEmail3(){
		assertTrue(!myRestController.isEmail("test"));
	}
	@Test
	public void testIsEmail4(){
		assertTrue(!myRestController.isEmail("@"));
	}
	@Test
	public void testIsEmail5(){
		assertTrue(!myRestController.isEmail("@test.com"));
	}
	@Test
	public void testIsEmail6(){
		assertTrue(!myRestController.isEmail(""));
	}
	
	/////////////////////////////////////////////////
	
	@Test
	public void testIsCorrectForm(){
		assertTrue(!myRestController.isCorrectForm("", "", ""));
	}
	@Test
	public void testIsCorrectForm1(){
		assertTrue(!myRestController.isCorrectForm("asd", "", ""));
	}
	@Test
	public void testIsCorrectForm2(){
		assertTrue(!myRestController.isCorrectForm("", "123wqe", ""));
	}
	@Test
	public void testIsCorrectForm3(){
		assertTrue(!myRestController.isCorrectForm("", "", "123wqe"));
	}
	@Test
	public void testIsCorrectForm4(){
		assertTrue(!myRestController.isCorrectForm("123wqe", "123wqe", ""));
	}
	@Test
	public void testIsCorrectForm5(){
		assertTrue(!myRestController.isCorrectForm("123wqe", "", "123wqe"));
	}
	@Test
	public void testIsCorrectForm6(){
		assertTrue(!myRestController.isCorrectForm("", "123wqe", "123wqe"));
	}
	@Test
	public void testIsCorrectForm7(){
		assertTrue(myRestController.isCorrectForm("123wqe", "123wqe", "123wqe"));
	}

}
