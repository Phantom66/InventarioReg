package com.inventario.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

public class SecurityPasswords {
	
	public static String Encriptar(String texto) {

	     MessageDigest md;
	     
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(texto.getBytes());
			byte[] digest = md.digest();

			byte[] encoded = Base64.encodeBase64(digest);

			String codec = new String(encoded);

			return codec.toString();
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}		
		
		return null;
	}


	
}
