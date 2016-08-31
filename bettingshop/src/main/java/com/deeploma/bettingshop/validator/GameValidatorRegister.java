package com.deeploma.bettingshop.validator;

import com.deeploma.bettingshop.domain.betting.Game;

public enum GameValidatorRegister {
	
	HALF_TIME_SCORE(Game.HALF_TIME_SCORE, new HalfTimeValidator()),
	
	FULL_TIME_SCORE(Game.FULL_TIME_SCORE, new FinalTimeValidator());
	
	private Game game ;
	
	private BetValidator validator;
	
	private GameValidatorRegister(Game game,  BetValidator validator) {
		this.game = game;
		this.validator = validator;
	}
	
	public static BetValidator findValidatorForGameId(Integer id) {
		for (GameValidatorRegister gmr : values()) {
			if (gmr.game.getId().equals(id)) {
				return gmr.validator;
			}
		}
		throw new IllegalArgumentException("Nepostojeca Game-Validator konfiguracija");
	}

}
