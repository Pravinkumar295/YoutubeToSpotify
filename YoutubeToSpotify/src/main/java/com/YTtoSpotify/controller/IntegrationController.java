package com.YTtoSpotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.YTtoSpotify.service.PlaylistService;
import com.YTtoSpotify.service.SpotifyService;

@RestController
public class IntegrationController {
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	SpotifyService service;
	
	
}
