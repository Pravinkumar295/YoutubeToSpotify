package com.YTtoSpotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@Autowired
	private OAuth2AuthorizedClientService clientService;
	
	
	@GetMapping("/home")
	public String home(OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		String accessToken = client.getAccessToken().getTokenValue();
		System.out.println("Spotify Access Token : "+accessToken);
		return "test";
	}
	
}