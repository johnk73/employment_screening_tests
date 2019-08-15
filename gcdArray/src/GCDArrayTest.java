import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GCDArrayTest {

  private GCDArray testClass;
  private static HashMap<List<Long>, Long> benchmark = new HashMap<>();
  @BeforeEach
  void setUp() {
    testClass = new GCDArray();
    benchmark.put(List.of(6L,9L), 3L);
    benchmark.put(List.of(6L,2L), 2L);
    benchmark.put(List.of(6L,15L,27L), 3L);
  }

  @Test
  void testgcdArray() {
    benchmark.forEach((input, expected) -> {
      long inputArray[] = new long[input.size()];
      for (int idx=0; idx < input.size(); ++idx) {
        inputArray[idx] = input.get(idx).longValue();
      }
      assertEquals(expected.longValue(), testClass.gcdArray(inputArray), input.toString() + "");
    });
  }
}