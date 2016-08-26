package com.deeploma.bettingshop.domain.betting;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum SubGame {
	
	HOME_TEAM_WIN_FULL_TIME(1, "1", Game.FULL_TIME_SCORE), 
	VISITOR_TEAM_WIN_FULL_TIME(2, "2", Game.FULL_TIME_SCORE), 
	DRAW_FULL_TIME(0, "0" , Game.FULL_TIME_SCORE),
	HOME_TEAM_WIN_HALF_TIME(3, "h1", Game.HALF_TIME_SCORE), 
	VISITOR_TEAM_WIN_HALF_TIME(4, "h2", Game.HALF_TIME_SCORE), 
	DRAW_HALF_TIME(5, "h0" , Game.HALF_TIME_SCORE);
		
	private Integer id;
	
	private String shortName;
	
	private Game game;
	
	private SubGame(Integer id , String shortName, Game game) {
		this.id = id;
		this.setShortName(shortName);
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public static SubGame getForId(Integer id) {
		for (SubGame val : values()) {
			if (val.getId().equals(id))
				return val;
		}
		
		return null;
		
	}
	
	public String getName() {
		return name();
	}

}
