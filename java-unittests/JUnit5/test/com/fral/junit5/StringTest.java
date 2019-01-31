package com.fral.junit5;

import org.junit.jupiter.api.*;

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
    void beforeEach() {
        System.out.println("Before each...");
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
}
