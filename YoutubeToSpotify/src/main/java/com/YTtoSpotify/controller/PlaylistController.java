package com.YTtoSpotify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.YTtoSpotify.service.PlaylistService;
import com.YTtoSpotify.service.SpotifyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

	@Controller
	@RequestMapping("/spotify")
	public class PlaylistController {
	
	    @Autowired
	    private PlaylistService playlistService;
	
	    @Autowired
	    private SpotifyService spotifyService;
	
	    /**
	     * Convert YouTube playlist to Spotify playlist
	     * @throws JsonProcessingException 
	     * @throws JsonMappingException 
	     */
	    @PostMapping("/convert")
	    public String convertPlaylist(
	            @RequestParam String playlistURL,
	            @RequestParam(defaultValue = "My YouTube Playlist") String playlistName,
	            OAuth2AuthenticationToken authentication) throws JsonMappingException, JsonProcessingException {
	
	        // 1. Get Spotify access token
	        String accessToken = spotifyService.getAccessToken(authentication);

        // 2. Get Spotify user ID
        String userId = spotifyService.getUserId(accessToken);

        // 3. Fetch YouTube titles
        List<String> titles = playlistService.getAllTitles(playlistURL);

        // 4. Search tracks on Spotify
        //List<String> trackUris = spotifyService.searchAndCollectUris(titles, accessToken);

        // 5. Create Spotify playlist
        String playlistId = spotifyService.createPlayList(userId, accessToken, playlistName);

        // 6. Add tracks
        //spotifyService.addTracksToPlaylist(playlistId, trackUris, accessToken);
       spotifyService.processAndAddTracks(playlistId, titles, accessToken);

        return "Multimedia";
    }
}

