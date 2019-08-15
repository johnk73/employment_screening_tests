package com.johnk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

// Count the number of bits in the binary representation of integers from 1 to N
// e.g. 6 would be 9
// 000, 001, 010, 011, 100, 101, 110
//   0 +  1 + 1 +  2 +  1  + 2  + 2 = 9
class AddBitsTest {
    private Map<Integer,Integer> input_output = Map.of(1,1, 2,2, 6,9, 15,32, 16,33);

    @DisplayName("brute force test successful")
    @Test
    void brute_force() {
        AddBits m = new AddBits();
        input_output.forEach((input,expected) -> {
            long output = m.bruteForce(1,input.longValue());
            assertEquals(expected.longValue(), output, "brute force expected " + output + ", actual" + output);
        }); }

    @DisplayName("Gauss test successful")
    @Test
    void gauss() {
        AddBits m = new AddBits();
        input_output.forEach((input,expected) -> {
            long output = m.gauss(input.longValue());
            assertEquals(expected.longValue(), output, "brute force expected " + output + ", actual" + output);
        }); }
}