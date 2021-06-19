package com.spring.util.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {

	private BcryptUtil() {
	}

	public static String getHash(String password) {

		System.out.println("password bcry : " + password);
		if (password == null) {
			return null;
		}

		if (BcryptUtil.isEncrypted(password)) {
			return password;
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(password);
	}

	/**
	 * Method check password Encoder ?
	 * 
	 * @since 06/06/2021
	 * 
	 * @param password
	 * @return boolean
	 */
	public static boolean isEncrypted(String password) {
		return password.startsWith("$2a$");
	}

	public static boolean decode(String password, String encrypted) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		boolean isPasswordMatches = bcrypt.matches(password, encrypted);

//		if (!isPasswordMatches)
//			throw new Exception("Password does not match.");

		return isPasswordMatches;
	}
}
