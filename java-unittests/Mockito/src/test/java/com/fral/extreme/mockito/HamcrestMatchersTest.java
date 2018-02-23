package com.fral.extreme.mockito;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.text.IsEmptyString;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {

	@Test
	public void testHamcrestMatchers_Initial() {
		List<Integer> scores = Arrays.asList(99, 100, 101, 105);
		
		//scores has 4 items
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(99, 100));
		
		//every item : > 90
		assertThat(scores, everyItem(greaterThan(90)));
		assertThat(scores, everyItem(lessThan(190)));
		
		//String
		assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());
		
		//Arrays
		Integer[] marks = { 1, 2, 3 };
		assertThat(marks, arrayWithSize(3));
		assertThat(marks, arrayContaining(1,2,3));
		assertThat(marks, arrayContainingInAnyOrder(3,2,1));
	}

}
