package com.johnk;
import java.util.ArrayList;
import java.util.Scanner;

// Given a list of integers find a contiguous (adjacent) subset of them that adds to the largest possible
// value.  Examples (correct answer in brackets):
//   [ 1, 2], -5   -> 3
//   1, -5, [ 2], -1  -> 2
//   [10, -5, -4, 10] -> 11
//   -5, [-1,] -1, -1 -> -1
public class LargestContiguousSubset {
  public static void main(String[] args) {
    LargestContiguousSubset m = new LargestContiguousSubset();
    Scanner in = new Scanner(System.in);
    long testCaseCount = in.nextLong();
    for (int testIndex=0; testIndex < testCaseCount; testIndex++) {
      ArrayList<Integer> numberList = new ArrayList<Integer>();
      long arraySize = in.nextLong();
      for (int arrayIndex=0; arrayIndex < arraySize; arrayIndex++) {
        int element = in.nextInt();
        numberList.add(element);
      }
      long sum = m.sumBruteForce(numberList);
      System.out.println(sum);
    }
  }

  // Sum from beginning to end, checking for lowest total along the way
  // Then sum from second element to end, again checking for lowest total
  // And so on, until the loop is just the last element by itself
  public long sumBruteForce(ArrayList<Integer> numberList) {
    long highestSum = Long.MIN_VALUE;
    long listLength;
    if (numberList != null && (listLength = numberList.size()) > 0) {
      for (int startIndex = 0; startIndex < listLength; ++startIndex) {
        long currentSum = 0;
        for (int index = startIndex; index < listLength; ++index) {
          currentSum += numberList.get(index);
          if (currentSum > highestSum) {
            highestSum = currentSum;
          }
        }
      }
    }
    return highestSum;
  }
}
