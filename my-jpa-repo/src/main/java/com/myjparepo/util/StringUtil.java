package main.java.com.myjparepo.util;

/**
 * The Class StringUtil consists required string manipulation methods.
 */
public class StringUtil {

	/**
	 * Instantiates a new string util.
	 */
	private StringUtil() {

	}

	/**
	 * converts input string To pascal case.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String toPascalCase(String str) {
		String convertedString = str.toLowerCase();
		for (int i = 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				char c = (char) (ch + 'a' - 'A');
				convertedString = convertedString.substring(0, i) + "_" + c + convertedString.substring(i + 1);
			}
		}
		return convertedString;
	}

	/**
	 * converts the input string to upper case from start index to end index.
	 *
	 * @param str   the str
	 * @param start the start
	 * @param end   the end
	 * @return the string
	 */
	public static String toUppercase(String str, int start, int end) {
		char[] ch = str.toCharArray();
		for (int i = start; i < end; i++) {
			ch[i] = (char) (ch[i] - 'a' + 'A');
		}
		return new String(ch);
	}

}
