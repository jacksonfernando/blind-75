import java.util.HashMap;

class Solution {
  public int romanToInt(String s) {
    int result = 0;
    HashMap<Character, Integer> roman = new HashMap<Character, Integer>();
    roman.put('I', 1);
    roman.put('V', 5);
    roman.put('X', 10);
    roman.put('L', 50);
    roman.put('C', 100);
    roman.put('D', 500);
    roman.put('M', 1000);
    Character previousRoman = s.charAt(0);
    Integer previousRomanValue = roman.get(previousRoman);
    result += previousRomanValue;
    for (int i = 1; i < s.length(); i++) {
      Character currentRoman = s.charAt(i);
      Integer currentRomanValue = roman.get(currentRoman);
      if (previousRoman == 'I' && (currentRoman == 'V' || currentRoman == 'X')) {
        result -= currentRomanValue;
      } else if (previousRoman == 'X' && (currentRoman == 'L' || currentRoman == 'C')) {
        result -= currentRomanValue;
      } else if (previousRoman == 'C' && (currentRoman == 'D' || currentRoman == 'M')) {
        result -= currentRomanValue;
      } else {
        result += currentRomanValue;
      }
      previousRoman = currentRoman;
      previousRomanValue = currentRomanValue;
    }
    return result;
  }
}
