package com.krone.ensLogin.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchesList {
	@JsonProperty("matches")private List<CricketMatches> matches = new ArrayList<CricketMatches>();
	@JsonProperty("v")	private String v;
	@JsonProperty("ttl")	private String ttl;
	@JsonProperty("provider")private Object provider;
	@JsonProperty("creditsLeft")private int creditsLeft;

public int getCreditsLeft() {
		return creditsLeft;
	}

	public void setCreditsLeft(int creditsLeft) {
		this.creditsLeft = creditsLeft;
	}

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

public List<CricketMatches> getMatches() {
	return matches;
}

public void setMatches(List<CricketMatches> matches) {
	this.matches = matches;
}
}
