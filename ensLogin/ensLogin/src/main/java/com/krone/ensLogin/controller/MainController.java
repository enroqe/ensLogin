package com.krone.ensLogin.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krone.ensLogin.model.CricketMatches;
import com.krone.ensLogin.model.MatchScore;
import com.krone.ensLogin.model.MatchesList;
import com.krone.ensLogin.utils.WebUtils;

@Controller
public class MainController {
	
	@RequestMapping(value = { "/googlebf402497187534ad.html" }, method = RequestMethod.GET) public
	 String googlePage(Model model) { model.addAttribute("title", "Welcome");
	 model.addAttribute("message", "This is welcome page!"); return "googlebf402497187534ad"; }

	
	  @RequestMapping(value = { "/index.html" }, method = RequestMethod.GET) public
	  String indexPage(Model model) { model.addAttribute("title", "Welcome");
	  model.addAttribute("message", "This is welcome page!"); return "index"; }
	  
	  @RequestMapping(value = "/restricted.html", method = RequestMethod.GET)
	  public String adminPage(Model model) { return "restricted"; }
	  
	  
	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * loginPage(Model model) {
	 * 
	 * return "login"; }
	 */
	  
	  @RequestMapping(value = {"/","welcome" }, method = RequestMethod.GET) public
	  String welcomePage(Model model) { model.addAttribute("title", "Welcome");
	  model.addAttribute("message", "This is welcome page!"); return "loginPage";
	  }
	  
	  @RequestMapping(value = "/admin", method = RequestMethod.GET) public String
	  adminPage(Model model, Principal principal) {
	  
	  User loginedUser = (User) ((Authentication) principal).getPrincipal();
	  
	  String userInfo = WebUtils.toString(loginedUser);
	  model.addAttribute("userInfo", userInfo);
	  
	  return "adminPage"; }
	  
	  
	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * loginPage(Model model) {
	 * 
	 * return "loginPage"; }
	 */
	  
	  @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	  public String logoutSuccessfulPage(Model model) { model.addAttribute("title",
	  "Logout"); return "logoutSuccessfulPage"; }
	  
	  @RequestMapping(value = "/userInfo", method = RequestMethod.GET) public
	  String userInfo(Model model, Principal principal) {
	  
	  // (1) (en) // After user login successfully. // (vi)  
		  String userName = principal.getName();
	  
	  System.out.println("User Name: " + userName);
	  
	  User loginedUser = (User) ((Authentication) principal).getPrincipal();
	  
	  String userInfo = WebUtils.toString(loginedUser);
	  model.addAttribute("userInfo", userInfo);
	  return "userInfoPage"; }
	  
	@RequestMapping(value = "/cricketMatches", method = RequestMethod.GET)
	public String cricketMatches(Model model, Principal principal) {

		// (1) (en) // After user login successfully. // (vi)
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		final String uri = "http://cricapi.com/api/matches?apikey=69zaVLAxp8SY6E8FujQzIqZZ2bS2";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		MatchesList matchList = null;
		try {
			matchList = mapper.readValue(result, MatchesList.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (matchList != null) {
			System.out.println("matchList=" + matchList.toString());
		} else {
			System.out.println("result=" + result);
		}
		String matchesTable= createMapTable(matchList.getMatches());
		model.addAttribute("matchlist", matchesTable);

		return "cricketMatchesPage";
	}
	@RequestMapping(value = "/matchScores", method = RequestMethod.GET)
	public String matchScores(Model model, Principal principal,@RequestParam("uniqueId") String Id) {

		// (1) (en) // After user login successfully. // (vi)
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		final String uri = "http://cricapi.com/api/cricketScore?apikey=69zaVLAxp8SY6E8FujQzIqZZ2bS2&unique_id="+Id;

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		MatchScore matchList = null;
		try {
			matchList = mapper.readValue(result, MatchScore.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (matchList != null) {
			System.out.println("matchList=" + matchList.toString());
		} else {
			System.out.println("result=" + result);
		}
		//String matchesTable= createMapTable(matchList.get(0));
		model.addAttribute("matchlist", createBody(matchList));

		return "cricketMatchesPage";
	}
	  
	  private String createBody(MatchScore matchList) {
		  String body= matchList.getStat()+"</br>"+ matchList.getScore() 
		  				+"</br>";
		return body;
	}


	private String createMapTable(List<CricketMatches> list) {
		  StringBuilder stringMapTable = new StringBuilder();
		    stringMapTable.append("<table>");
		    stringMapTable.append("<tr>");
		    for (int i = 0; i < list.size(); i++) {
		        	stringMapTable.append("</tr><tr>");
		        
		        stringMapTable.append("<td id=\""+list.get(i).getUniqueId()+"\">" +
		        "<a href=\"http://myenslogin.com/matchScores?uniqueId="+list.get(i).getUniqueId()+"\">"
		        		+createTable(list.get(i))+"</a>" 
		        		+ "</td>");
		    }
		    stringMapTable.append("</tr>");
		    stringMapTable.append("</table>");
		  return stringMapTable.toString();
	}


	private String createTable(CricketMatches cricketMatches) {
		String table= cricketMatches.getDate()+" "+ cricketMatches.getTeam1()+" vs "+cricketMatches.getTeam2()+" win by "+ cricketMatches.getWinnerTeam();
		return table;
	}


	@RequestMapping(value = "/403", method = RequestMethod.GET) public String
	  accessDenied(Model model, Principal principal) {
	  
	  if (principal != null) { User loginedUser = (User) ((Authentication)
	  principal).getPrincipal();
	  
	  String userInfo = WebUtils.toString(loginedUser);
	  
	  model.addAttribute("userInfo", userInfo);
	  
	  String message = "Hi " + principal.getName() +
	  "<br> You do not have permission to access this page!";
	  model.addAttribute("message", message);
	  
	  }
	  
	  return "403Page"; }
	 

}
