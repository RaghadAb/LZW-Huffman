package lzw;
import java.io.*;
import java.util.*;

/** program to find compression ratio using LZW compression
 */
public class Main {

	public static void main(String[] args) throws IOException {

		// create a trie
		Trie trie = new Trie();
		
		// add a single character string to it
		for( int i=0; i<256; i++)
			// add this character to the trie
			trie.insert("" + (char)i,i); //insert takes in parameter code
		int code = 256;
		// the output code
		ArrayList<Integer> outputCode = new ArrayList<>();
		int originalFileLength = 0;

		// the output code
		// String outputCode = "";
		
		long start = System.currentTimeMillis();
		String inputFileName = "/Users/raghadabdulrab/Downloads/2359025/large.txt"; //args[0];
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data and do the work here
        // read a line at a time to enable newlines to be detected and included in the compression
		
		try
		{
			String line = in.nextLine();
			// get the first character
			String P = "" + line.charAt(0);
			originalFileLength++;
			String C = "";
			// as long as there is an input line
			do
			{
				// get this line
				line = line == null? in.nextLine():line.substring(1);
				//if( in.hasNextLine() )
				//	line += "\n";
				// for each character in this line
				for( int i=0; i<line.length(); i++,originalFileLength++ )
				{
					if (i != line.length() - 1) 
			            C += line.charAt(i + 1); 
					// get the next input
					// if p + c in thetable
					if( trie.search(P + C))
					{
						P = P + C;
					}
					else
					{
						// this is the code for P
						outputCode.add(trie.find(P));
						trie.insert(P + C, code);
						code++;
						P = C;
					}
					C = "";
				}
				// add the extra
				line = null;
			}
			while( in.hasNextLine() );
			outputCode.add(trie.find(P));
		}
		catch(Exception e)
		{
			
		}
		in.close();
		reader.close();
		// end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		
		System.out.println("Original file length in bits : " + (originalFileLength*8)); //codewords of length 8
		// for( Integer i : outputCode )
		// 	System.out.println(i);
		
		// output file length in bits
		int outputFileBits = 0;
		for( Integer i : outputCode )
		{
			String str = "";
			if( i == 0)
				str = "0";
			else
			{
				while(i!=0)
				{
					str = (i%2) + str;
					i = i/2;
				}
			}
			outputFileBits += str.length();
		}
		System.out.println("Compressed file length in bits = " + outputFileBits);
		System.out.println("Compression ratio : " + ( (double)(outputFileBits)/(originalFileLength*8)));
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
		// print compression ratio
	}

}
