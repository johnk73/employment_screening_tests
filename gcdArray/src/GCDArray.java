import java.util.Scanner;

public class GCDArray {

  public static void main(String[] args) {
    GCDArray testClass = new GCDArray();
    Scanner scanner = new Scanner(System.in);
    long testCaseCount = scanner.nextLong();
    for (long idx=0; idx<testCaseCount; ++idx) {
      int listSize = scanner.nextInt();
      long[] list = new long[listSize];
      for (int listIdx=0; listIdx<listSize; ++listIdx) {
        list[listIdx] = scanner.nextLong();
      }
      System.out.println(testClass.gcdArray(list));
    }
  }

  public long gcdArray(long[] positiveIntegers) {
    long numberOfIntegers = positiveIntegers.length;
    if (numberOfIntegers == 0)
      return 0;
    long gcdValue = positiveIntegers[0];
    for (int idx=1; idx < numberOfIntegers; ++idx) {
      gcdValue = gcd(gcdValue, positiveIntegers[idx]);
    }
    return gcdValue;
  }

  // The solution I see for greatest common denominator is modulo and I had to think about it
  // before I understood _why_ it works.  When you divide you are repeatedly subtracting the
  // denominator.  And the number of times is the non-common factor.  In this example "2" is not
  // shared as a divisor between the numbers.  When we divide a "4" is removed.
  //   6 (2x3) and 27 (3x3x3).   27 % 6 = 27 - 6*4 = 3
  protected long gcd(long first, long second) {
    long modulo = first % second;
    if (modulo == 0) {
      return second;
    } else {
      return gcd(second, modulo);
    }
  }
}
