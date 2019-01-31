package com.fral.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringTest {

    @Test
    public void split_basic() {
        String str = "abc def ghi";
        String actualResult[] = str.split(" ");
        String[] expectedResult = new String[] { "abc", "def", "ghi" };

        assertArrayEquals(expectedResult, actualResult);
    }
}
