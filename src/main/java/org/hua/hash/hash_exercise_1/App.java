/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.hash.hash_exercise_1;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.hua.hash.hash_exercise_1.Dictionary.Entry;

public class App {

	public static void main(String args[]) {

		Dictionary<String, Integer> dict = new ChainingHashTable<>();
                Dictionary<String, Integer> dictt=new OpenAdressingHashTable<>();
		
		try (Scanner scanner = new Scanner(System.in)) {
			while(scanner.hasNext()) {
                            if (scanner.next().equals("s")){
                                break;
                            }
				String line = scanner.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				while(st.hasMoreTokens()) { 
					String word  = st.nextToken();
					Integer curFreq = dictt.get(word);
					if (curFreq == null) { 
						curFreq = 1;
					} else { 
						curFreq++;
					}
					dictt.put(word, curFreq);
                                        System.out.println(dictt.size());
				}
			}
                        
                        

		}
		System.out.println("wtf");
		for(Entry<String, Integer> e: dictt) { 
			System.out.println("Word " + e.getKey() + " appeared " + e.getValue() + " times");
		}

	}

}