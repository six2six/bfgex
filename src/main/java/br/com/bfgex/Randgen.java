package br.com.bfgex;

import java.util.Collection;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberRange;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;


public class Randgen {
	
	private static NumberRange WORDS_PER_SENTENCE = new NumberRange(3, 20);
	private static NumberRange SENTENCES_PER_PARAGRAPH = new NumberRange(3, 8);

	public static boolean pickBoolean() {
		return RandomUtils.nextBoolean();
	}
	
	public static String pickChar() {
		return RandomStringUtils.randomAlphabetic(1);
	}

	public static String pickLowerChar() {
		return pickChar().toLowerCase();
	}

	public static String pickUpperChar() {
		return pickChar().toUpperCase();
	}

	public static String pickWhiteSpace() {
		return pickArray(new String[] {"\t", "\n", "\r"});
	}
	
	public static Integer pickDigit() {
		return new RandomDataImpl().nextInt(0, 9);
	}
	
	public static <T> T pickArray(T[] array) {
		return array[RandomUtils.nextInt(array.length)];
	}

	public static <T> T pickOne(T...args) {
		return (T) pickArray(args);
	}
	
	public static Number pickRange(NumberRange range) {
		Number result = null;
		RandomData randomData = new RandomDataImpl();
		
		if (range.getMinimumNumber() instanceof Integer && range.getMaximumNumber() instanceof Integer) {
			result = randomData.nextInt(range.getMinimumInteger(), range.getMaximumInteger());
		}
	
		if (range.getMinimumNumber() instanceof Long && range.getMaximumNumber() instanceof Long) {
			result = randomData.nextLong(range.getMinimumLong(), range.getMaximumLong());
		}
		
		if (range.getMinimumNumber() instanceof Float && range.getMaximumNumber() instanceof Float) {
			result = randomData.nextUniform(range.getMinimumFloat(), range.getMaximumFloat());
		}

		if (range.getMinimumNumber() instanceof Double && range.getMaximumNumber() instanceof Double) {
			result = randomData.nextUniform(range.getMinimumDouble(), range.getMaximumDouble());
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T pickCollection(Collection<T> collection) {
		T result = null;
		if (!collection.isEmpty()) {
			result = (T) pickArray(collection.toArray());
		}
		return result;
	}

	public static String pickAlphaNumeric() {
		return RandomStringUtils.randomAlphanumeric(1);
	}
	
	public static String sentence(Integer length) {
		if (length == null) {
			length = pickRange(WORDS_PER_SENTENCE).intValue();
		}
		String[] result = new String[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = word();
		}
		
		return StringUtils.join(result, " ");
	}
	
	public static String sentence() {
		return sentence (null);
	}

	public static String paragraph(Integer length) {
		if (length == null) {
			length = pickRange(SENTENCES_PER_PARAGRAPH).intValue();
		}
		String[] result = new String[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = sentence();
		}
		
		return StringUtils.join(result, ".");
	}
	
	
	public static String email(Integer emailLength, String domain) {
		emailLength = getValidLength(emailLength, 40);
		domain = domain == null ? "example.org" : domain;
		return word(emailLength - domain.length() - 1) + "@" + domain;
	}
	
	public static String email(Integer emailLength) {
		return email(emailLength, null);
	}
	
	public static String email(String domain) {
		return email(null, domain);
	}

	public static String email() {
		return email(null, null);
	}
	
	public static String word(Integer length) {
		length = getValidLength(length, 20);

		String word = pickCollection(Dictionary.getWordsByLength(length));
		
		if (word == null) {
			word = RandomStringUtils.randomAlphanumeric(length);
		}
		
		return word;
	}
	
	public static String word() {
		return word(null);
	}
	
	public static String firstName(Integer length, Gender gender) {
		length = getValidLength(length, 10);
		return gender.equals(Gender.MALE) ? 
				pickCollection(Dictionary.getMaleNameByLength(length)) : pickCollection(Dictionary.getFemaleNameByLength(length));
	}

	public static String firstName(Integer length) {
		return firstName(length, pickArray(Gender.values()));
	}

	public static String firstName() {
		return firstName(null, pickArray(Gender.values()));
	}
	
	public static String firstName(Gender gender) {
		return firstName(null, gender);
	}
	
	public static String lastName(Integer length) {
		length = getValidLength(length, 10);
		return pickCollection(Dictionary.getLastNameByLength(length));
	}
	
	public static String lastName() {
		return lastName(null);
	}

	public static String name(Integer length, Gender gender) {
		return firstName(length, gender) + " " + lastName(length);
	}

	public static String name(Gender gender) {
		return firstName(null, gender) + " " + lastName(null);
	}
	
	public static String name(Integer length) {
		return firstName(length) + " " + lastName(length);
	}

	public static String name() {
		return firstName() + " " + lastName();
	}
	
	private static Integer getValidLength(Integer length, Integer maxRandomLength) {
		if (length == null) {
			length = RandomUtils.nextInt(maxRandomLength);
		}
		return length;
	}
	
	public static void main(String[] args) {
		System.out.println(name());
	}
}
