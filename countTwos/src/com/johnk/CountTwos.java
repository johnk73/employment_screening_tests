package com.johnk;
import java.util.Scanner;

// Given an ending integer "N", count the number of times that the numeral "2"
// appears in the base 10 representation of integers from 1 to N
public class CountTwos {

    public static void main(String[] args) {
      CountTwos m = new CountTwos();
        Scanner in = new Scanner(System.in);
        long testCaseCount = in.nextLong();
        for (int testIndex=0; testIndex < testCaseCount; testIndex++) {
            long endingNumber = in.nextLong();
            long numberOfTwos = m.bruteForce(endingNumber);
            System.out.println(numberOfTwos);
        }
    }

    // Print all the numbers, then scan for 2's
    protected long bruteForce(long endingNumber) {
      StringBuffer buffer = new StringBuffer();
      for (int index = 1; index <= endingNumber; ++index) {
        buffer.append(index);
      }
      String digits = buffer.toString();
      int matches = 0, offset = 0;
      while ((offset = digits.indexOf('2', offset)) != -1) {
        matches++;
        offset++;
      }
      return matches;
    }
}
