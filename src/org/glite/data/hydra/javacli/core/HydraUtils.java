package org.glite.data.hydra.javacli.core;

import java.math.BigInteger;
import java.util.Arrays;
/**
 * Misc. Utils
 * @author ekorhone
 *
 */
public class HydraUtils {
	/**
	 * Grabs the file-extension of a file-path given as string.
	 * @param fullPath The path to make the lookup
	 * @return The file-extension
	 */
	public static String extension(String fullPath) {
		int dot = fullPath.lastIndexOf(".");
		return fullPath.substring(dot + 1);
	}
	
	
	/**
	 * Used in HydraConnection
	 * 
	 * Converts our byte-arrays to hex/binary string-representations
	 * from http://helpdesk.objects.com.au/java/how-can-i-convert-a-byte-array-to-a-hex-string-representation
	 * @param bytes the byte-array to be converted
	 * @return A string representing byte-array hex-encoded
	 */
	public static String bytesToString(byte[] bytes) {
	    BigInteger bi = new BigInteger(1,bytes); // this makes sure the BigInteger is postive!!
	    String hex2 = bi.toString(16); 
            // System.out.println("bytesToString: file has is: " + hex2);
	    return hex2;
	}

	/*
	 * Moved from tools/PaddingTools.java
	 * length = length in bytes
	 */
	/**
	 * Converts a BigInt (What is stored in Hydra-keystore and what 
	 * our version of SSSS is using...) to a padded byte-array. 
	 * Which is compatible with JCE SecretKeySpecs
	 * 
	 * @param bi The big-int to be parsed
	 * @param length The desired length
	 * @return byte[] including the bigint in padded format.
	 * @throws Exception
	 */
	public static byte[] BigIntToPaddedByteArray(BigInteger bi, int length) throws Exception{
		byte[] biAsBytes = bi.toByteArray();
		return paddedByteArray(biAsBytes, length);	
	}
	
	
	/**
	 * Pads a byte-array with zeroes to a specific length.
	 * Used to make sure that reconstructed keys are extended to a JCE-compatible key-size if the randomly generated key
	 * has many zero-bits in the beginning.
	 * 
	 * @param original The original array to be padded
	 * @param length The desired length in bytes.
	 * @return The key padded with zero-bits
	 * @throws Exception
	 */
	public static byte[] paddedByteArray(byte[] original, int length) throws Exception {
		// ok, it might be easier to just use this (System.arraycopy) function directly, 
		// and leave the tool unused
		byte[] returnArray = new byte[length];
		int difference = length - original.length;
		
		// checks that the length is longer than original array
		if (difference < 0) {
			throw new Exception("New array length must be longer than original length.");
		} else {
			Arrays.fill(returnArray, (byte)00);
			System.arraycopy(original, 0, returnArray, difference, original.length);
			return returnArray;
		}
	}
	
}
