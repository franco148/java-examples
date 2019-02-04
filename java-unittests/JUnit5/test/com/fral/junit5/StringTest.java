package com.fral.junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringTest {


    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all...");
    }

    @BeforeEach
    void beforeEach(TestInfo info) {
        System.out.println("Before each..." + info.getDisplayName());
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each...");
    }

    @Test
    public void split_basic() {
        String str = "abc def ghi";
        String actualResult[] = str.split(" ");
        String[] expectedResult = new String[] { "abc", "def", "ghi" };

        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void length_basic() {
        String str = "abc def ghi";
        int actualResult = str.length();
        int expectedResult = 11;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("When string is null, throw an exception")
    public void length_exception() {
        String str = null;
//        int actualLength = str.length();
        assertThrows(NullPointerException.class, ()-> str.length());
    }

    @Test
    public void length_greater_than_zero() {
        assertTrue("ABCD".length() > 0);
        assertTrue("ABC".length() > 0);
        assertTrue("C".length() > 0);
        assertTrue("DE".length() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCD", "ABC", "C", "DE"})
    public void length_greater_than_zero_parameterized(String str) {
        assertTrue(str.length() > 0);
    }
}
