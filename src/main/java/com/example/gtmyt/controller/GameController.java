package com.example.gtmyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gtmyt.services.GameService;

@org.springframework.stereotype.Controller
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	
	@GetMapping("/game-home")
	public String showGameHome() {
		return "game-home"; 
	}
	
	@PostMapping("/word")
	public String submitWord(@RequestParam("word") String word , @RequestParam("actor") String actor) {
		gameService.setGivenMovie(word);
		gameService.setActorName(actor);
		return "redirect:/guess-game";	
	}
	
	@GetMapping("/guess-game")
	public String showGuessGameForm(Model model) {
		String displayWord = gameService.toString();
		model.addAttribute("displayWord", displayWord);
		model.addAttribute("actorName", gameService.getActorName());
		model.addAttribute("wrongGuesses", gameService.getWrongGuesses());
		model.addAttribute("triesRemaining", gameService.getTriesRemaining());
		model.addAttribute("gameWon", gameService.isWordGuessed());
		return "guess-game";		
	}
	
	@PostMapping("/guess-game")
	public String processGuess(Model model, @RequestParam("guessedChar") String guessedChar) {
		boolean isGuessCorrect = gameService.addGuess(guessedChar.charAt(0));
		String displayWord = gameService.toString();
		model.addAttribute("displayWord", displayWord);
		model.addAttribute("isGuessCorrect", isGuessCorrect);
		model.addAttribute("actorName", gameService.getActorName());
		model.addAttribute("wrongGuesses", gameService.getWrongGuesses());
		model.addAttribute("triesRemaining", gameService.getTriesRemaining());
		model.addAttribute("gameWon", gameService.isWordGuessed());
		return "guess-game";
		
		
		
		
		
		
		
		
	}
	
	@PostMapping("/reset-game")
	public String resetGame() {
		gameService.resetGame();
		return "redirect:/game-home";
	}

}
