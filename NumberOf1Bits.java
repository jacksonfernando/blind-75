import java.util.Scanner;

class Solution {

  // Write a function that takes an unsigned integer and returns the number of '1'
  // bits it has (also known as the Hamming weight). Note that in some languages,
  // such as Java, there is no unsigned integer type. In this case, the input will
  // be given as a signed integer type. You should treat it as an unsigned integer
  // and return the number of '1' bits it has.
  //
  // Example:
  // Input: n = 00000000000000000000000000001011
  // Output: 3
  // Explanation: The input binary string 00000000000000000000000000001011 has
  // three '1' bits.
  //
  // Input: n = 00000000000000000000000010000000
  // Output: 1
  // Explanation: The input binary string 00000000000000000000000010000000 has one
  // '1' bit.
  //
  // Input: n = 11111111111111111111111111111101
  // Output: 31
  // Explanation: The input binary string 11111111111111111111111111111101 has
  // thirty one '1' bits.
  public static int numberOf1Bits(int n) {
    int result = 0;
    while (n != 0) {
      result += (n & 1);
      n >>= 1;
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    scanner.close();
    int res = numberOf1Bits(n);
    System.out.println(res);
  }
}
