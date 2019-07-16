package com.gaspar44.init;

import java.io.FileOutputStream;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class InitialBlockChain {
	public static ArrayList<Block> blockChain = new ArrayList<Block>();
	public static int difficulty = 1;
	public static void main(String[] args) throws Exception {
	
		Block genesisBlock = new Block("holis", "0");
		blockChain.add(genesisBlock);
		blockChain.get(0).minedBlock(difficulty);
		
		Block secondBlock = new Block("como",genesisBlock.getHash());
		blockChain.add(secondBlock);
		blockChain.get(1).minedBlock(difficulty);
		
		validateChain(blockChain);
		
		String jsonBlockChain = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println(jsonBlockChain);
		FileOutputStream os = new FileOutputStream("./blockChian.json");
		os.write(jsonBlockChain.getBytes());
		os.close();
		
	}
	
	
	public static Boolean validateChain(ArrayList<Block> blockChainToValidate) throws Exception { 
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for (int i = 1; i < blockChainToValidate.size(); i++) {
			Block currentBlock = blockChainToValidate.get(i);
			Block previousBlock = blockChainToValidate.get(i-1);
			
			if (! previousBlock.getHash().equals(currentBlock.getPreviousHash()))
				throw new Exception();
			
			if( ! currentBlock.calculateHash().equals(currentBlock.getHash())) 
				throw new Exception();
			
			if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				throw new Exception();
			}
			
		}
		
		return true;
		
	}

}
