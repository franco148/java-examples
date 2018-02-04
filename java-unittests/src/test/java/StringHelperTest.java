import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fral.extreme.junit.helper.StringHelper;

class StringHelperTest {

	@Test
	void test() {
		StringHelper helper = new StringHelper();
		
		assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
		assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
	}

}
