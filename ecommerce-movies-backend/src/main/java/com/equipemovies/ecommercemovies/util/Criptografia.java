package com.equipemovies.ecommercemovies.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Criptografia {
	public static String encriptografar(String senha) {
		String retorno = "";
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			retorno = hash.toString(16);
			System.out.println(retorno);
		} catch (Exception e) {}
		
		
		return retorno;
	}
}
