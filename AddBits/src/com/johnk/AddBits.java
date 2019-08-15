package com.johnk;

import java.util.Scanner;

// Count the number of bits in the binary representation of integers from 1 to N
// e.g. 6 would be
// 000, 001, 010, 011, 100, 101, 110
//   0 +  1 + 1 +  2 +  1  + 2  + 2 = 9
public class AddBits {

    public static void main(String[] args) {
        AddBits m = new AddBits();
      /*
        long values[] = {1, 2, 6, 15, 16};
        for (long value : values) {
            // different methods
            long brute_force = m.brute_force(1, value);
            long gauss = m.gauss(value);
            System.out.println(value + "->" + brute_force + "," + gauss + " (brute force, Gauss)" +
                                (brute_force != gauss ? " different!" : ""));
        }
       */
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        long testCaseCount = in.nextLong();
        for (long idx=0; idx < testCaseCount; ++idx) {
            long value = in.nextLong();
            //long bruteForce = m.bruteForce(1, value);
            long gauss = m.gauss(value);
            System.out.println(gauss);
        }
    }

    protected long bruteForce(long start, long end) {
        long bit_count = 0;
        // 1 - N
        for (long current_integer = start; current_integer <= end; ++current_integer) {
            // Test if lowest bit is turned on, then shift right and check again
            // e.g. 6 = 110 (lowest bit 0), 11 (1), 1 (1) = 0 + 1 + 1 = 3
            for (long shifted_integer = current_integer; shifted_integer > 0; shifted_integer >>= 1) {
                //System.out.println("shifted_Int = " + shifted_integer + "("+shifted_integer%2+")");
                if (shifted_integer % 2 != 0) {
                    bit_count++;
                }
            }
        }
        return bit_count;
    }

    // Gauss computes part of the sequence by pairing the beginning and end numbers
    // The operator in the case of counting bits is "OR"
    // e.g. 0 - 7
    //  000 | 111 = 111
    //  001 | 110 = 111
    //  010 | 101 = 111
    //  100 | 011 = 111
    // So we count 3 bits * 4 = 12.  log2(7+1) * (7+1)/2
    protected long gauss(long n) {
        long gauss_value = 0, brute_force_offset = 0;
        // Get distance from power of 2, to reduce brute force sequence
        long bits = (long)this.log2(n);
        long pow2_down = (long)Math.pow(2, bits)-1, pow2_up = (long)Math.pow(2, bits+1)-1;
        //System.out.println("bits = "+bits+"  pow2_down = "+pow2_down+"  pow2_up = "+pow2_up);
        if (pow2_up - n < n - pow2_down) {
            gauss_value = (bits+1) * (pow2_up+1)/2;
            brute_force_offset = -bruteForce(n + 1, pow2_up);
        } else {
            gauss_value = bits * (pow2_down+1)/2;
            brute_force_offset = bruteForce(pow2_down + 1, n);
        }
        //System.out.println("gauss = " + gauss_value + "  offset = " +brute_force_offset);
        return gauss_value + brute_force_offset;
    }

    private static double logbase(double value, long base) {
        return Math.log(value) / Math.log(base);
    }
    private static double log2(double value) {
        return AddBits.logbase(value, 2);
    }
}
