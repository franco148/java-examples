package com.fral.junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

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

    @ParameterizedTest
    @CsvSource(value = { "abcde, ABCDE", "ab, AB", "'', ''", "abcdefg, ABCDEFG" })
    public void uppercase_test(String word, String capitalizedWord) {
        assertEquals(capitalizedWord, word.toUpperCase());
//        assertEquals("DEF", "def".toUpperCase());
    }

    @ParameterizedTest(name = "{0} length is {1}")
    @CsvSource(value = { "abcde, 5", "ab, 2", "'', 0", "abcdefg, 7" })
    public void length_test(String word, int strLength) {
        assertEquals(strLength, word.length());
    }

    @Test
    @RepeatedTest(10)
    public void contains_basic() {
        assertFalse("abcdefghijk".contains("lmn"));
    }

    @Test
    @Disabled //@Ignore in JUnit 4 //Disabled annotation can be used in class level as well.
    public void performance_test() {
        assertTimeout(Duration.ofSeconds(10), () -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(i);
            }
        });
    }
}
