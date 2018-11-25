package com.eextreme.tdd;

/*
 * 1. At each step we need to write the code that is needed to get our test to pass. Nothing else even if we know that longer term, that is not the right code.
 * 2. We should always start with a failing test (Red part of our red green refactor cycle). 
 * 3. Sometimes we know that our code is going to be like an rubbish code. It is not what we want, there is not sensible logic in it at all. But the code should
 *    made the tests pass. So it is the right thing today for the purposes of test driven development.
 *    If we are not going to try and do any kind of shortcuts let's save and run this test again.
 */
public class ValidateIsbn {

	public boolean checkIsbn(String isbnNumber) {
		
		//Adding a new feature. 13 digits number ISBN should be valid
		if (isbnNumber.length() == 13) {
			int total = 0;
			
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0) {
					total += Character.getNumericValue(isbnNumber.charAt(i));
				} else {
					total += Character.getNumericValue(isbnNumber.charAt(i)) * 3;
				}
			}
			
			return total % 10 == 0;
		} else {
			// This was implemented when I needed to verify the number of digits of a ISBN number.
			if (isbnNumber.length() != 10) {
				throw new NumberFormatException("ISBN numbers must be 10 digits long.");
			}
					

			/*
			 * REMEMBER: The quality of this code is not important yet. It is the tests that are important, and as we build
			 * them up this code will improve as we add more tests and refactor.
			 */
			int total = 0;
			
			for (int i = 0; i < 10; i++) {
				if (!Character.isDigit(isbnNumber.charAt(i))) {
					if (i == 9 && isbnNumber.charAt(i) == 'X') {
						// This scenario is ok
						// According the rules, when the last number is X, it will be replaced by 10
						total += 10;
					} else {
						throw new NumberFormatException("ISBN numbers can only contain numeric digits");					
					}
				} else {
					total += Character.getNumericValue(isbnNumber.charAt(i)) * (10 - i);
				}			
			}
			
			return total % 11 == 0;
		}
		
	}

}
