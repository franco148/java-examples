package com.fral.extreme.junit.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fral.extreme.junit.helper.StringHelper;


/**
 * AACD => CD; ACD => CD;  CDEF => CDEF; CDAA => CDAA
 * @author Franco
 *
 */

public class StringHelperTest {

	StringHelper helper;
	
	@Before
	public void before( ) {
		helper = new StringHelper();
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
		
		assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));		
	}
	
	@Test
	public void testtruncateAInFirst2Positions_AinFirstPosition() {
				
		assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
	}
	
	@Test
	public void testtruncateAInFirst2Positions_AisNotPresent() {
				
		assertEquals("CDEF", helper.truncateAInFirst2Positions("CDEF"));
	}
	
	@Test
	public void testtruncateAInFirst2Positions_AinLastPosition() {
				
		assertEquals("CDAA", helper.truncateAInFirst2Positions("CDAA"));
	}
	
	
	/**
	 * ABCD => false;  ABAB => true;  AB => true;  A => false
	 */
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicNegativeScenario() {
		assertFalse(helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
	}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicPositiveScenario() {
		assertTrue(helper.areFirstAndLastTwoCharactersTheSame("ABAB"));
	}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_SpecialTwoCharactersScenario() {
		assertTrue(helper.areFirstAndLastTwoCharactersTheSame("AB"));
	}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_SpecialOneCharacterScenario() {
		assertFalse(helper.areFirstAndLastTwoCharactersTheSame("A"));
	}

}
