package com.krone.ensLogin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchScore {
	@JsonProperty("v")	private String v;
	@JsonProperty("ttl")	private String ttl;
	@JsonProperty("provider")private Object provider;
	@JsonProperty("creditsLeft")private int creditsLeft;
	@JsonProperty("stat")		private String stat;
	@JsonProperty("score")		private String score;
	@JsonProperty("description")		private String description;
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	public Object getProvider() {
		return provider;
	}
	public void setProvider(Object provider) {
		this.provider = provider;
	}
	public int getCreditsLeft() {
		return creditsLeft;
	}
	public void setCreditsLeft(int creditsLeft) {
		this.creditsLeft = creditsLeft;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public boolean isIsmatchStarted() {
		return ismatchStarted;
	}
	public void setIsmatchStarted(boolean ismatchStarted) {
		this.ismatchStarted = ismatchStarted;
	}
	@JsonProperty("team-1")		private String team1;
	@JsonProperty("team-2")		private String team2;
	@JsonProperty("matchStarted")private boolean ismatchStarted;
}
