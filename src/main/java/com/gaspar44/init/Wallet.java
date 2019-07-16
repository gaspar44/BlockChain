package com.gaspar44.init;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
	
	private PrivateKey privateKey;
	public PublicKey publicKey;
	
	public Wallet() {
		generateKeys();
	}

	private void generateKeys() {
		try {
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("ECDSA","BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			
			keyGenerator.initialize(ecSpec, random);   
			KeyPair keyPair = keyGenerator.generateKeyPair();
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
			
		}
		
		catch (Exception e) {}
		
	}

}
