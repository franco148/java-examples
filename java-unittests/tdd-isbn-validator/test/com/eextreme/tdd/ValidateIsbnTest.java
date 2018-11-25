package com.eextreme.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * RULES OF TDD
 * 1. Test the expected outcome of an example.
 * 2. Do not pre-judge design... let your tests drive it.
 * 3. Write the minimum code required to get your tests to pass.
 * 4. Each test should validate one single piece of logic.
 *    So normally you would only expect to have one assert in a test method.
 *    But there are times when you can have more than one assert.
 *    But if you have more than one assert it needs to fit one of the following two examples. Either you need to test more than one value
 *    to check that something worked correctly. For example if you expect a particular method to set three or four variables in a class
 *    you can perfectly validly write tests that are searched each of those variables have got the correct value.
 *    The second version is when you want to test multiple values that are just different examples of the same thing.
 *    That is what we doing now. We have two different valid ISBN numbers and we expect both of them to return the value true. So we are
 *    testing the same principle of logic with both of these numbers. We've just got two different examples. So in this instance it is fine
 *    for them to go in the same method. So let's do that after our first assert will add in a second line which is wheelset.
 */
public class ValidateIsbnTest {

	@Test
	public void checkAValidISBN() {
		ValidateIsbn validator = new ValidateIsbn();
		boolean result = validator.checkIsbn("0140449116"); //0140449116
		assertTrue("First Value", result);
		// Now the variable results are to be equal to validate a dot check ISBN.
		result = validator.checkIsbn("0140177396"); //0140177396
		assertTrue("Second Value", result);
	}
	
	@Test
	public void checkAnInValidISBN() {
		ValidateIsbn validator = new ValidateIsbn();
		boolean result = validator.checkIsbn("0140449117"); //0140449117
		assertFalse(result);
	}
	
	/*
	 * Since this scenario should fail, it may be good to throw a NumberFormatException.
	 */
	@Test(expected = NumberFormatException.class)
	public void nineDigitIsbnAreNotAllowed() {
		ValidateIsbn validator = new ValidateIsbn();
		validator.checkIsbn("123456789");
	}
	
	@Test(expected = NumberFormatException.class)
	public void nonNumericIsbnAreNotAllowed() {
		ValidateIsbn validator = new ValidateIsbn();
		validator.checkIsbn("helloworld");
	}

}
