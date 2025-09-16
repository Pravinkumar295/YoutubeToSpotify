package com.YTtoSpotify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.YTtoSpotify.entity.Playlist;
import com.YTtoSpotify.service.AuthService;
import com.YTtoSpotify.service.PlaylistService;
import com.YTtoSpotify.service.SpotifyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/spotify/playlists")
public class SpotifyController {
	
	@Autowired
	SpotifyService spotifyService;
	
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createPlaylist(Authentication authentication,
								 @RequestParam String playlistName) throws JsonMappingException, JsonProcessingException {
		String accessToken = authService.getAccessToken(authentication);
		String userId = authService.getSpotifyUserId((OAuth2AuthenticationToken) authentication);
		return ResponseEntity.status(HttpStatus.CREATED).body(spotifyService.createPlayList(userId,accessToken,playlistName));
	}
	
	@GetMapping("/search/uris")
	public ResponseEntity<List<String>> seachAndCollectUri(List<String> trackName,String accessToken){
		List<String> list = spotifyService.searchAndCollectUris(trackName, accessToken);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
