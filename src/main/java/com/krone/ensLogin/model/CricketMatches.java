package com.krone.ensLogin.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CricketMatches {
@JsonProperty("unique_id")	private String uniqueId;
@JsonProperty("date")		private Date date;
@JsonProperty("dateTimeGMT")		private Date dateTimeGMT;
@JsonProperty("squad")		private boolean squad;
@JsonProperty("team-1")		private String team1;
@JsonProperty("team-2")		private String team2;
@JsonProperty("matchStarted")private boolean ismatchStarted;
@JsonProperty("type")		private String type;
@JsonProperty("toss_winner_team")		private String tossWinnerTeam;
public Date getDateTimeGMT() {
	return dateTimeGMT;
}
public void setDateTimeGMT(Date dateTimeGMT) {
	this.dateTimeGMT = dateTimeGMT;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getTossWinnerTeam() {
	return tossWinnerTeam;
}
public void setTossWinnerTeam(String tossWinnerTeam) {
	this.tossWinnerTeam = tossWinnerTeam;
}
public String getWinnerTeam() {
	return winnerTeam;
}
public void setWinnerTeam(String winnerTeam) {
	this.winnerTeam = winnerTeam;
}
@JsonProperty("winner_team")		private String winnerTeam;
public String getUniqueId() {
	return uniqueId;
}
public void setUniqueId(String uniqueId) {
	this.uniqueId = uniqueId;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public boolean isSquad() {
	return squad;
}
public void setSquad(boolean squad) {
	this.squad = squad;
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

}
