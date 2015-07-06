package com.ethor.testbed.api.test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.exception.ValidationException;

public class Assert {

	/**
	 * Asserts whether give object is not null, if null throws
	 * ValidationException with the given message.
	 * 
	 * @param object
	 *            Object to check.
	 * @param message
	 *            Message to display when object is null
	 */
	public static void assertNotNull(final Object object, final String message) {
		if (object == null) {
			throw new ValidationException(message);
		}
	}

	/**
	 * Asserts whether give object is not null, if null append the given message
	 * to message list.
	 * 
	 * @param object
	 *            Object to check.
	 * @param message
	 *            Message to display when object is null.
	 * @param messageList
	 *            Message list to append the message.
	 */
	public static void assertNotNull(final Object object, final String message, final List<String> messageList) {
		if (object == null) {
			messageList.add(message);
		}
	}

	/**
	 * Asserts whether given two object equals. If not ValidationException will
	 * be thrown with the given message.
	 * 
	 * @param expected
	 *            Object which is expecting.
	 * @param actual
	 *            Actual object.
	 * @param message
	 *            Message to display when two object are not equal.
	 */
	public static void assertSame(final Object expected, final Object actual, final String message) {
		if (!expected.equals(actual)) {
			throw new ValidationException(message);
		}
	}

	/**
	 * Asserts whether given two object equals. If not given message will be
	 * append to the message list.
	 * 
	 * @param expected
	 *            Object which is expecting.
	 * @param actual
	 *            Actual object.
	 * @param message
	 *            Message to display when two object are not equal.
	 * @param messageList
	 *            Message list to append the message.
	 */
	public static void assertSame(final Object expected, final Object actual, final String message,
			final List<String> messageList) {
		if (!expected.equals(actual)) {
			messageList.add(message);
		}
	}

	public static void assertSame(final String expected, final String actual, final String attribute,
			List<UnExpectedValue> expectedValues) {
		if (!isStringNullOrEmpty(expected) || !isStringNullOrEmpty(actual)) {
			if ((isStringNullOrEmpty(expected) && !isStringNullOrEmpty(actual))
					|| (!isStringNullOrEmpty(expected) && isStringNullOrEmpty(actual))) {
				expectedValues.add(new UnExpectedValue(attribute, expected, actual));
			} else if (!expected.equals(actual)) {
				expectedValues.add(new UnExpectedValue(attribute, expected, actual));
			}
		}
	}

	private static boolean isStringNullOrEmpty(String value) {
		if (value == null) {
			return true;
		} else {
			return value.isEmpty();
		}
	}

	public static void assertSame(final Double expected, final Double actual, final String attribute,
			List<UnExpectedValue> expectedValues) {
		if (expected != null || actual != null) {
			if (expected == null && actual != null) {
				expectedValues.add(new UnExpectedValue(attribute, null, String.valueOf(actual)));
			} else if (expected != null && actual == null) {
				expectedValues.add(new UnExpectedValue(attribute, String.valueOf(expected), null));
			} else if (!expected.equals(actual)) {
				expectedValues.add(new UnExpectedValue(attribute, String.valueOf(expected), String.valueOf(actual)));
			}
		}
	}

	public static void assertSame(final Integer expected, final Integer actual, final String attribute,
			List<UnExpectedValue> expectedValues) {
		if (expected != null || actual != null) {
			if (expected == null && actual != null) {
				expectedValues.add(new UnExpectedValue(attribute, null, String.valueOf(actual)));
			} else if (expected != null && actual == null) {
				expectedValues.add(new UnExpectedValue(attribute, String.valueOf(expected), null));
			} else if (!expected.equals(actual)) {
				expectedValues.add(new UnExpectedValue(attribute, String.valueOf(expected), String.valueOf(actual)));
			}
		}
	}

	public static void assertSame(final Calendar expected, final Calendar actual, final String attribute,
			List<UnExpectedValue> expectedValues) {
		if (!expected.equals(actual)) {
			expectedValues.add(new UnExpectedValue(attribute, String.valueOf(expected), String.valueOf(actual)));
		}
	}

	public static void assertSame(final Boolean expected, final Boolean actual, final String attribute,
			List<UnExpectedValue> expectedValues) {
		if (expected != null || actual != null) {
			if (expected == null && actual != null) {
				expectedValues.add(new UnExpectedValue(attribute, null, String.valueOf(actual)));
			} else if (expected != null && actual == null) {
				expectedValues.add(new UnExpectedValue(attribute, String.valueOf(expected), null));
			} else if (!expected.equals(actual)) {
				expectedValues.add(new UnExpectedValue(attribute, String.valueOf(expected), String.valueOf(actual)));
			}
		}
	}

	public static void assertSame(final List<?> expected, final List<?> actual, final String attribute,
			List<UnExpectedValue> expectedValues) {
		if (expected == null && actual == null) {
			return;
		} else if (expected != null && actual != null) {

			if (expected.size() != actual.size()) {
				expectedValues.add(new UnExpectedValue("# of " + attribute, listToString(expected),
						listToString(actual)));
				return;
			} else {
				for (int i = 0; i < expected.size(); i++) {

					if (!actual.contains(expected.get(i))) {
						expectedValues
								.add(new UnExpectedValue(attribute, listToString(expected), listToString(actual)));
						return;
					}
				}
				return;
			}
		}
		expectedValues.add(new UnExpectedValue(attribute, listToString(expected), listToString(actual)));
	}
	
	public static void assertSame(final Set<?> expected, final Set<?> actual, final String attribute,
			List<UnExpectedValue> expectedValues) {
		if (expected == null && actual == null) {
			return;
		} else if (expected != null && actual != null) {

			if (expected.size() != actual.size()) {
				expectedValues
						.add(new UnExpectedValue("# of " + attribute, setToString(expected), setToString(actual)));
				return;
			} else {

				Iterator<?> iterator = expected.iterator();
				while (iterator.hasNext()) {
					if (!actual.contains(iterator.next())) {
						expectedValues.add(new UnExpectedValue(attribute, setToString(expected), setToString(actual)));
						return;
					}

				}
				return;
			}
		}
		expectedValues.add(new UnExpectedValue(attribute, setToString(expected), setToString(actual)));
	}

	/**
	 * Asserts whether given expression is true. If not Validation exception
	 * would be thrown with given message.
	 * 
	 * @param expression
	 *            boolean expression
	 * @param message
	 *            validation message.
	 */
	public static void assertTrue(final boolean expression, final String message) {
		if (!expression) {
			throw new ValidationException(message);
		}
	}

	/**
	 * Asserts whether given expression is true. If not given message will
	 * append to the message list.
	 * 
	 * @param expression
	 *            boolean expression.
	 * @param message
	 *            validation message
	 * @param messageList
	 *            message list.
	 */
	public static void assertTrue(final boolean expression, final String message, final List<String> messageList) {
		if (!expression) {
			messageList.add(message);
		}
	}

	private static String listToString(final List list) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			builder.append(String.valueOf(list.get(i)));
			if (i != list.size() - 1) {
				builder.append(" | ");
			}

		}

		return builder.toString();
	}
	
	private static String setToString(final Set list) {
		StringBuilder builder = new StringBuilder();
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			builder.append(iterator.next());
			builder.append(" | ");
		}
		return builder.toString().substring(0, builder.toString().length() - 1);
	}
}
