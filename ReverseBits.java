import java.util.Scanner;

class Solution {
  // Reverse bits of a given 32 bits unsigned integer. Note that in some
  // languages, such as Java, there is no unsigned integer type. In this case, the
  // input will be given as a signed integer type. You should treat it as an
  // unsigned integer and reverse its bits.
  //
  // Example:
  //
  // Input: n = 00000010100101000001111010011100
  // Output: 964176192 (00111001011110000010100101000000)
  // Explanation: The input binary string 00000010100101000001111010011100
  // represents the unsigned integer 43261596, so return 964176192 which its
  // binary representation is 00111001011110000010100101000000.
  //
  // Input: n = 11111111111111111111111111111101
  // Output: 3221225471 (10111111111111111111111111111111)
  // Explanation: The input binary string 11111111111111111111111111111101
  // represents the unsigned integer 4294967293, so return 3221225471 which its
  // binary representation is 10111111111111111111111111111111.
  public static int reverseBits(int n) {
    int result = 0;
    for (int i = 0; i < n; i++) {
      result = result << 1 | (n & 1);
      n >>= 1;
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseUnsignedInt(scanner.nextLine());
    scanner.close();
    int res = reverseBits(n);
    System.out.println(Integer.toUnsignedLong(res));
  }
}
