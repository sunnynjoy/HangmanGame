package com.nexmo.hm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Sunny Ghosh
 *
 */

public class RandomWordsGenerator {
	private List<String> words;
	
	public String getRandonWord() {
		if (words == null || words.isEmpty()) {
			GetRandomWordsFromProperties();
		}
		int index = new Random().nextInt(words.size());
		return words.get(index);
	}
	
	private void GetRandomWordsFromProperties() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/dictionary.txt");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			words = new ArrayList<String>();
			while (reader.ready()) {
				words.add(reader.readLine());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
