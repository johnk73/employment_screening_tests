package com.johnk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

// Given an ending integer "N", count the number of times that the numeral "2"
// appears in the base 10 representation of integers from 1 to N
class CountTwosTest {
  // {input, expected}
  private static HashMap<Long,Long> testData = new HashMap<Long,Long>();
  @BeforeEach
  void setUp() {
    testData.put(22L, 6L);
    testData.put(42L, 15L);
    testData.put(100L, 20L);
    testData.put(18468L, 7597L);
  }

  @DisplayName("brute force test succeeded")
  @Test
  void testBruteForce() {
    CountTwos testClass = new CountTwos();
    for (Long input : testData.keySet()) {
      Long expected = testData.get(input);
      Long output = testClass.bruteForce(input);
      assertEquals(expected, output, "input " + input + ", expected " +
          expected + " but was " + output);
    }
  }
}