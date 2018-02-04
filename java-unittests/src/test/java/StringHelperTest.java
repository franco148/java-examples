import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fral.extreme.junit.helper.StringHelper;


/**
 * AACD => CD; ACD => CD;  CDEF => CDEF; CDAA => CDAA
 * @author Franco
 *
 */
class StringHelperTest {

	StringHelper helper = new StringHelper();
	
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

}
