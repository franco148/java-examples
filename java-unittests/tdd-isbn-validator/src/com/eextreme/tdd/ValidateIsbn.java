package com.eextreme.tdd;

/*
 * 1. At each step we need to write the code that is needed to get our test to pass. Nothing else even if we know that longer term, that is not the right code.
 * 2. We should always start with a failing test (Red part of our red green refactor cycle). 
 * 3. Sometimes we know that our code is going to be like an rubbish code. It is not what we want, there is not sensible logic in it at all. But the code should
 *    made the tests pass. So it is the right thing today for the purposes of test driven development.
 *    If we are not going to try and do any kind of shortcuts let's save and run this test again.
 */
public class ValidateIsbn {

	private static final int LONG_ISBN_MULTIPLIER = 10;
	private static final int SHORT_ISBN_MULTIPLIER = 11;
	private static final int SHORT_ISBN_LENGTH = 10;
	private static final int LONG_ISBN_LENGTH = 13;

	public boolean checkIsbn(String isbnNumber) {
		
		//Adding a new feature. 13 digits number ISBN should be valid
		if (isbnNumber.length() == LONG_ISBN_LENGTH) {
			return isThisAValidLongIsbn(isbnNumber);
		} else if (isbnNumber.length() == SHORT_ISBN_LENGTH) {
			// This was implemented when I needed to verify the number of digits of a ISBN number.
//			if (isbnNumber.length() != SHORT_ISBN_LENGTH) {
//				throw new NumberFormatException("ISBN numbers must be 10 digits long.");
//			}
			
			return isThisAValidShortIsbn(isbnNumber);
		}
		
		throw new NumberFormatException("ISBN numbers must be 10 digits long.");
		
	}

	private boolean isThisAValidShortIsbn(String isbnNumber) {
		/*
		 * REMEMBER: The quality of this code is not important yet. It is the tests that are important, and as we build
		 * them up this code will improve as we add more tests and refactor.
		 */
		int total = 0;
		
		for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
			if (!Character.isDigit(isbnNumber.charAt(i))) {
				if (i == 9 && isbnNumber.charAt(i) == 'X') {
					// This scenario is ok
					// According the rules, when the last number is X, it will be replaced by 10
					total += 10;
				} else {
					throw new NumberFormatException("ISBN numbers can only contain numeric digits");					
				}
			} else {
				total += Character.getNumericValue(isbnNumber.charAt(i)) * (SHORT_ISBN_LENGTH - i);
			}			
		}
		
		return total % SHORT_ISBN_MULTIPLIER == 0;
	}

	private boolean isThisAValidLongIsbn(String isbnNumber) {
		int total = 0;
		
		for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
			if (i % 2 == 0) {
				total += Character.getNumericValue(isbnNumber.charAt(i));
			} else {
				total += Character.getNumericValue(isbnNumber.charAt(i)) * 3;
			}
		}
		
		return total % LONG_ISBN_MULTIPLIER == 0;
	}

}
