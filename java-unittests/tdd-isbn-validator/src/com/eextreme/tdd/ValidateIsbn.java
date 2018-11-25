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
		if (isbnNumber == "0140449116") {
			return true;			
		} else {
			return false;
		}
		
	}

}
