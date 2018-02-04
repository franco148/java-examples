package com.fral.extreme.junit.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.fral.extreme.junit.helper.StringHelper;


/**
 * AACD => CD; ACD => CD;  CDEF => CDEF; CDAA => CDAA
 * @author Franco
 *
 */

@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {

	StringHelper helper = new StringHelper();
	
	private String input;
	private String expectedOutput;
	
	public StringHelperParameterizedTest(String input, String expectedOutput) {
		super();
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	/**
	 * {Input, output} pair array need to be sent
	 * @return
	 */
	@Parameters
	public static Collection<String[]> testConditions() {
		String expectedOutputs[][] = {{"AACD", "CD"}, {"ACD", "CD"}, {"CDEF", "CDEF"}, {"CDAA", "CDAA"}};
		
		return Arrays.asList(expectedOutputs);
	}
		
	/**
	 * As a good practice for names we need to call the functionality name + the condition
	 * 
	 * void testtruncateAInFirst2Positions_condition() for example, in that way we can have
	 * many unit tests for different condition. Actually it is a best practice instead
	 * of having many conditions in the same unit test.
	 */
	@Test
	public void testtruncateAInFirst2Positions_AinFirst2Positions() {		
		
		assertEquals(expectedOutput, helper.truncateAInFirst2Positions(input));		
	}
	
}
