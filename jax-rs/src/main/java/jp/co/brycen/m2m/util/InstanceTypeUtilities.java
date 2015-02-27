/*
 * InstanceTypeUtilities.java
 * Author: bnhien
 */
package jp.co.brycen.m2m.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class InstanceTypeUtilities. Utilities for INSTANCE_TYPE
 */
public class InstanceTypeUtilities {

	/** The Constant devDBFolder. */
	public static final String devDBFolder = "/usr/local/linter/m2m/devdb/";

	/** The Constant repDBFolder. */
	public static final String repDBFolder = "/usr/local/linter/m2m/repdbbase/";

	/**
	 * Gets the info of given path.
	 *
	 * @param folderPath
	 *            the folder path
	 * @return	�o�^����(�o�^�����F[date] if valid
	 * 			�o�^�Ȃ� if invalid
	 */
	public static String getInfo(String folderPath) {
		String result = "";
		try {
			Path path = Paths.get(folderPath);
			result = "�o�^����(�o�^�����F"
					+ new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(Date
							.from(Files
									.readAttributes(path,
											BasicFileAttributes.class)
									.creationTime().toInstant()));
		} catch (IOException e) {
			// Not found
			result = "�o�^�Ȃ�";
		}
		return result;
	}
}
