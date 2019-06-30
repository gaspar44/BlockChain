package com.gaspar44.init;

import java.security.MessageDigest;
import java.util.Date;

public class Block {
	private String hash;
	private String previousHash;
	private String data;
	private Long timeStampData;
	private int nonce;
	
	public Block(String data, String previousHash) throws Exception {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStampData = new Date().getTime();
		this.hash = applyHash(previousHash + Long.toString(timeStampData) + data + Integer.toString(nonce));
	}
	
	private String applyHash(String inputData) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		byte[] hash = digest.digest(inputData.getBytes());
		StringBuffer hexHash = new StringBuffer();
		
		
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			
			if(hex.length() == 1) 
				hexHash.append('0');
			hexHash.append(hex);
		}
		return hexHash.toString();
		
	}
	
	public String calculateHash() throws Exception {
		return applyHash(this.previousHash + Long.toString(this.timeStampData) + this.data + Integer.toString(this.nonce));
	}
	
	public void minedBlock(int difficulty) throws Exception {
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		while(!this.hash.substring( 0, difficulty).equals(target)) {
			nonce++;
			this.hash = calculateHash();
		}
		
		System.out.println("Block Mined!!! : " + hash);
		
	}
	
	public String getHash() {
		return this.hash;
	}
	
	public String getPreviousHash() {
		return this.previousHash;
	}

}
