package com.mongolia.website.util;

import org.doomdark.uuid.UUID;
import org.doomdark.uuid.UUIDGenerator;

public class UUIDMaker {
	private static UUIDGenerator generator = UUIDGenerator.getInstance();

	/**
	 * 获得一个基于随机数的uuid
	 * @return uuid
	 */
	public static String getRandomBasedUUID() {
		UUID uuid = generator.generateRandomBasedUUID();
		return uuid.toString();
	}

	/**
	 * 获得一个基于时间的UUID
	 * @return uuid
	 */
	public static String getTimeBasedUUID() {
		UUID uuid = generator.generateTimeBasedUUID();
		return uuid.toString();
	}

	/**
	 * 缺省情况下，使用基于时间的UUID
	 * @return uuid
	 */
	public static String getUUID() {
		return getTimeBasedUUID().toUpperCase();
	}
}
