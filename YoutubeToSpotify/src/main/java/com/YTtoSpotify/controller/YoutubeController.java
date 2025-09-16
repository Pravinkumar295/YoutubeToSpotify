package com.YTtoSpotify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.YTtoSpotify.entity.Playlist;
import com.YTtoSpotify.service.PlaylistService;



@RestController
@RequestMapping("/youtube")
public class YoutubeController {
	
	@Autowired
	PlaylistService playlistService;
	
	@PostMapping("/save/playlist")
	public ResponseEntity<String> savePlaylist(@RequestParam String playlistURL) {
		Playlist playlist = new Playlist();
		List<String> titles = playlistService.getAllTitles(playlistURL);
		playlist.setSongTitles(titles);
		playlistService.save(playlist);
		return ResponseEntity.status(HttpStatus.CREATED).body("Data fetched Successfully ");
	}
	
	@GetMapping("/playlist/get")
	public ResponseEntity<List<Playlist>> getPlaylistTitles() {
		 List<Playlist> list = playlistService.getPlaylistSongsTitle();
		return ResponseEntity.status(HttpStatus.OK).body(list);
		//return new ModelAndView("songsFromYoutube");
	}
	
	
}
/* /youtube/save/playlist */