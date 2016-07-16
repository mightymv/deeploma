package com.deeploma.bettingshop.domain.betting;

public enum SubGame {
	
	HOME_TEAM_WIN_FULL_TIME(1, Game.FINAL_TIME_SCORE), 
	VISITOR_TEAM_WIN_FULL_TIME(2, Game.FINAL_TIME_SCORE), 
	DRAW_FULL_TIME(0, Game.FINAL_TIME_SCORE);
		
	private Integer id;
	
	private Game game;
	
	private SubGame(Integer id , Game game) {
		this.id = id;
		this.setGame(game);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	

}
