package com.YTtoSpotify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	@Autowired
	OAuth2AuthorizedClientService clientService;
	
	public String getAccessToken(Authentication authentication) {
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				oauthToken.getAuthorizedClientRegistrationId(),oauthToken.getName());
		return client.getAccessToken().getTokenValue();
	}
	
	public String getSpotifyUserId(OAuth2AuthenticationToken authentication) {
		OAuth2User user = authentication.getPrincipal();
		return user.getAttribute("id");
	}
}