package amazon;

/*
 * 
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words. 
 *  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are 
not case sensitive.  The answer is in lowercase.
 * 
 * https://leetcode.com/problems/most-common-word/submissions/
 * Amazon archives
 * 
 * */

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

/**
 * @author PRAG KAMRA
 *
 */
public class MostCommonBannedWord {
	
	
	//In java 8
	 public static String mostCommonWord(String paragraph, String[] banned) {
		 
		 List bann = Arrays.asList(banned);		 
		 //\\p{Punct} is to replace all Punctuation with space and then split string by space: One of !"#$%&'()*+,-./:;<=>?@[]^_`{|}~
		 String arr[] = paragraph.replaceAll("\\p{Punct}", " ").toLowerCase().split("\\s++");
		 
		 //find frequency of each word in String
	     Map<String,Long> mp = Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	     
	     //Sort the Map in descending in Java 8
	     Map<String,Long>mp1 = mp.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed()).
	    		 collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
	     
	     //check word is not in banned list
	     for (Map.Entry<String, Long> ml:mp1.entrySet()) {
	    	 if (!(bann.contains(ml.getKey()))){
	    		 return ml.getKey();
	    	 }
	     }
	     return null;
	    }
	 
	 //In java 7
	 public static String mostCommonWord1(String paragraph, String[] banned) {
		  paragraph += ".";

	        Set<String> banset = new HashSet();
	        for (String word: banned) banset.add(word);
	        Map<String, Integer> count = new HashMap();

	        String ans = "";
	        int ansfreq = 0;

	        StringBuilder word = new StringBuilder();
	        for (char c: paragraph.toCharArray()) {
	            if (Character.isLetter(c)) {
	                word.append(Character.toLowerCase(c));
	            } else if (word.length() > 0) {
	                String finalword = word.toString();
	                if (!banset.contains(finalword)) {
	                    count.put(finalword, count.getOrDefault(finalword, 0) + 1);
	                    if (count.get(finalword) > ansfreq) {
	                        ans = finalword;
	                        ansfreq = count.get(finalword);
	                    }
	                }
	                word = new StringBuilder();
	            }
	        }

	        return ans;
	    }
	
	 
	 
	 
	 public static void main (String args[]) {
		 String[] banned= {"hit"};
		 //String[] banned = {"a"};
	String paragraph="Bob hit a ball, the hit BALL flew far after it was hit.";	
		 //String paragraph = "a, a, a, a, b,b,b,c, c";
				 
		 System.out.print(mostCommonWord1(paragraph,banned));
	 }

}
