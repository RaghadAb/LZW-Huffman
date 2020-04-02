package lzwhashmap;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String inputFileName = args[0];
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		String originalText = "";
		ArrayList<Integer> compressedTexts = new ArrayList<>();
		int count = 0;
		try {
		  FileReader reader = new FileReader(inputFileName);
		  Scanner in = new Scanner(reader);

		  while (in.hasNextLine()) {
			  String line = in.nextLine();
			  originalText+=line;
			  for (int i = 0; i < line.length(); i++) {
				  String ch = Character.toString(line.charAt(i));
				  if (!dictionary.containsKey(ch)) {
					  dictionary.put(ch, count);
					  count +=1;
				  }
			  }
		  }
		  in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(dictionary.size());
		
		String latestText = "";
		
		for (int i = 0; i < originalText.length(); i++) {
			if (!dictionary.containsKey(latestText + Character.toString(originalText.charAt(i)))) {
				dictionary.put(latestText + Character.toString(originalText.charAt(i)), count);
				count+=1;
				compressedTexts.add(dictionary.get(latestText));
				latestText = Character.toString(originalText.charAt(i));
			} else {
				latestText = latestText + Character.toString(originalText.charAt(i));
				if (i == originalText.length() - 1) {
					compressedTexts.add(dictionary.get(latestText));
				}
			}
			
		}
		
	   	int numCompressedBits = compressedTexts.size() * 8;
	   	int numOriginalBits =  originalText.length() * 8;
		
	   	System.out.println("Original file length in bits = " + numOriginalBits);
	   	System.out.println("Compressed file length in bits = " + numCompressedBits);
	   	System.out.println("Compression ratio = " + (float)numCompressedBits/numOriginalBits);
		
		// end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}
	
}
