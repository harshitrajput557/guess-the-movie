package com.example.gtmyt.services;

import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Service
public class GameService {
	
	private String givenMovie;
	private String actorName;
	private char[] allCharInMovie;
	private Set<Character> wrongGuesses;
	private int triesRemaining;
	
	
	
	
	
	public GameService() {
		this.wrongGuesses = new HashSet<>();
		
		
	}
	public String getGivenMovie() {
		return givenMovie;
	}
	public void setGivenMovie(String givenMovie) {
		this.givenMovie = givenMovie;
		this.allCharInMovie = new char[givenMovie.length()];
		this.triesRemaining = (int) givenMovie.chars().filter(ch -> ch != ' ').count();
		this.wrongGuesses.clear();
		
		
	}
	public int reduceTry() {
		return --triesRemaining;
	}
	
	
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for(int i = 0 ; i < givenMovie.length(); i++) {
			char c = givenMovie.charAt(i);
			if(c == ' ') {
				ret.append("/ ");
			}
			else if(allCharInMovie[i] == '\u0000'){
				ret.append("_ ");			
			}
			else {
				ret.append(c).append(" ");
			}		
		}
		return ret.toString().trim();	
		
	}
	
	public boolean addGuess(char guessedChar) {
		guessedChar = Character.toLowerCase(guessedChar);
		boolean isGuessCorrect = false;
		
		for(int i = 0 ; i < givenMovie.length() ; i ++) {
			if(guessedChar == Character.toLowerCase(givenMovie.charAt(i))) {
				allCharInMovie[i] = givenMovie.charAt(i);
				isGuessCorrect = true;
			}
		}
		
		if(!isGuessCorrect) {
			wrongGuesses.add(guessedChar);
			
			if(Character.isLetter(guessedChar)) {
				triesRemaining = reduceTry();
			}
			
		}
		
		return isGuessCorrect;
		
		
	}
	
	
	public boolean isWordGuessed() {
		for(int i = 0 ; i < givenMovie.length() ; i++) {
			if(givenMovie.charAt(i) != ' ' && allCharInMovie[i] == '\u0000') {
				
				return false;
			}
			
		}
		return true;
		
		
		
	}
	
	public int getTriesRemaining() {
		return triesRemaining;
	}
	
	
	public String getWrongGuesses() {
		StringBuilder ret = new StringBuilder();
		for(char c : wrongGuesses) {
			ret.append(c).append(" ");
		}
		return ret.toString().trim();
			
	}
	
	
	public void resetGame() {
		this.givenMovie = null;
		this.actorName = null;
		this.allCharInMovie = null;
		this.wrongGuesses.clear();
		this.triesRemaining = 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
