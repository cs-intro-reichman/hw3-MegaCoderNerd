/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true
		System.out.println(isAnagram("str", "rest")); // False
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

		// nested loop going over all characters.
		// we are checking that they have the same amount of each
		for (int i = 0; i < str1.length(); i++){
			char currChar = str1.charAt(i);
			// we store the index to use to find all other instances of the char
			int index = -1;
			for (int j = 0; j < str2.length(); j++){
				if (currChar == ' ') break;
				index = str2.indexOf(currChar,index+1);
				// if the character doesn't exist in the other string
				// we need to check no more.
				if (index == -1 && j == 0){
					return false;
				}else if (index == -1){
					// if the character doesn't exist in the other string,
					// it can't be an anagram so we break the loop
					if (str1.indexOf(currChar, i+1) == -1) break;
					return false;
				}
				break;

			}
		}
		return true;
	}


	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		// turning str to undercase letters
		str = str.toLowerCase();
		// making new variables one which we return and is the edited string
		// and one which is all the characters we want to remain (alphabet only)
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
		
		// pre-process the string to remove all non-alphabetic characters
		str = preProcess(str);
		
		String newStr = "";
		
		// the length of the string
		int len = str.length();
		
		// the string that we will be taking characters from
		String temp = str;
		
		// generate the anagram
		for (int i = 0; i < len; i++){
			// get a random location in the string
			int randLocation = (int) (Math.random() * (temp.length()));
			char currChar = temp.charAt(randLocation);
			newStr += currChar;
			// remove the character from the original string
			// by replacing it's first instance by an empty string
			temp = temp.replaceFirst((String) ""+currChar, "");
			
		}
		return newStr;
	}
}
