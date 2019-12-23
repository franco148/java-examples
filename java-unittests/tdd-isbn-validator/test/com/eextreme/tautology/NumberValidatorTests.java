package com.eextreme.tautology;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberValidatorTests {

	
	// Within this test method, for each of the numbers whether or not it is prime so that we can
	// compare it to whether our isItPrime method of the NumberValidator class also thinks it's prime 
	// is pretty much identical to the code within the class that we are trying to test.
	// So in other words the code that we are testing is repeated in our test method. And that means
	// if there is a mistake in this code, we might have these same mistakes in our test method
	// and in our isItPrime method in a class under test.
	
//	@Test
//	public void checkPrimeNumbers()
//	{
//		
//		Integer numbers[] = {1,15,23,25,60,61,63,79,207};
//		NumberValidator validator = new NumberValidator();
//		
//		for (int i = 0; i < numbers.length; i++) {
//			boolean isPrime = true;
//			int maxDivisor = (int)Math.sqrt(numbers[i]); 
//			for(int counter =2;counter < maxDivisor; counter ++) {
//		        if(numbers[i] % counter ==0)
//		        	isPrime = false;
//		    }
//				
//		assertEquals(isPrime, validator.isItPrime(numbers[i]));
//		
//		}
//		
//	}
	
	@Test
	public void checkPrimeNumbers() 
	{
		Integer numbers[] = {1,23,61,79};
		
		NumberValidator validator = new NumberValidator();
		
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(true,validator.isItPrime(numbers[i]));
		}
		
	}
	
	
	@Test
	public void checkNonPrimeNumbers() 
	{
		Integer numbers[] = {15,25,60,63,207};
		
		NumberValidator validator = new NumberValidator();
		
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(false,validator.isItPrime(numbers[i]));
		}
	}

}
