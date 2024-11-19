
/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 1000; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		if (str1.length() != str2.length()) return false;
		for (int i = 0; i < str1.length(); i++){
			char curr = str1.charAt(i);
			if (str2.indexOf(curr, i) == -1) return false;
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	private static String preProcess(String str) {
		// turning str to undercase letters
		str = str.toLowerCase();
		// making new variables one which we return and is the edited string
		// and one which is all the characters we want to remain (spaces and alphabet)
		String acceptedChars = "abcdefghijklmnopqrstuvwxyz ";
		String editedStr = "";
		// going over all the characters and only add those which are accepted
		// to the processed string
		for (int i=0; i < str.length(); i++){
			char curr = str.charAt(i);
			if (acceptedChars.indexOf(curr) != -1){
				editedStr += curr;
			}
		}
		return editedStr;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		// no other anagram if the string is empty or only one character
		if (str.length() <= 1) return str; 
		str = preProcess(str);
		String newStr = "";
		int len = str.length();
		
		// we go over all of str and make random indexes which
		// are unique to access characters from
		for (int i = 0; i < len; i++){
			int j = (int) Math.min((Math.random()*str.length() + i), str.length()-i-1);
			newStr += str.charAt(j+i);
			//str.replace(str.charAt(j+i),"")
		}
		return newStr;
	}
}