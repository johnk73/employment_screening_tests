package com.johnk;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Given a list of integers find a contiguous (adjacent) subset of them that adds to the largest possible
// value.  Examples (correct answer in brackets):
//   [ 1, 2], -5   -> 3
//   1, -5, [ 2], -1  -> 2
//   [10, -5, -4, 10] -> 11
class LargestContiguousSubsetTest {

  // Test cases
  private static ArrayList<ArrayList<Integer>> inputs = new ArrayList<ArrayList<Integer>>();
  private static ArrayList<Integer> expectedList = new ArrayList<Integer>();
  @BeforeAll
  protected static void setUp() {
    inputs.add(new ArrayList<Integer>(Arrays.asList(1)));
    expectedList.add(1);
    inputs.add(new ArrayList<Integer>(Arrays.asList(1, 2, -5)));
    expectedList.add(3);
    inputs.add(new ArrayList<Integer>(Arrays.asList(-5, 1, 2)));
    expectedList.add(3);
    inputs.add(new ArrayList<Integer>(Arrays.asList(2, -5, 1)));
    expectedList.add(2);
    inputs.add(new ArrayList<Integer>(Arrays.asList(1, -5, 2)));
    expectedList.add(2);
    inputs.add(new ArrayList<Integer>(Arrays.asList(-5, 1, 2, -5)));
    expectedList.add(3);
    inputs.add(new ArrayList<Integer>(Arrays.asList(1, -1, 2)));
    expectedList.add(2);
    inputs.add(new ArrayList<Integer>(Arrays.asList(-5, -1, -6)));
    expectedList.add(-1);
    inputs.add(new ArrayList<Integer>(Arrays.asList(10, 8, -12, 5, -9, 100)));
    expectedList.add(102);
  }

  @DisplayName("largest sequence test passed")
  @Test
  void testSumBruteForce() {
    LargestContiguousSubset testClass = new LargestContiguousSubset();
    for (ArrayList<Integer> input : inputs) {
      int index = inputs.indexOf(input);
      long output = testClass.sumBruteForce(input);
      long expected = expectedList.get(index).longValue();
      assertEquals(expected, output, "sumOfLargest(" + input.toString() +
          "): was " + output + ", expected " + expected);
    }
  }
}