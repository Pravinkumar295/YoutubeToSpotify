package com.YTtoSpotify.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.YTtoSpotify.dto.YoutubePlaylistResponse;
import com.YTtoSpotify.entity.Playlist;
import com.YTtoSpotify.repository.PlaylistRepository;

@Service
public class PlaylistService {
	private static final String API_KEY = "YOUTUBE_API_KEY";
	
	private static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/playlistItems";

	@Autowired
	PlaylistRepository playlistRepository;
	
	public void save(Playlist playlist) {
		playlistRepository.save(playlist);
	}

	public List<String> getAllTitles(String playlistURL) {
		String playlistId = extractPlaylistId(playlistURL);
		List<String> titles = new ArrayList<>();
		String nextPageToken = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			do {
				URI url = buildApiUri(playlistId,nextPageToken);
				YoutubePlaylistResponse response = restTemplate.getForObject(url, YoutubePlaylistResponse.class);
				if(response ==null || response.getItems()== null )break;
				response.getItems().forEach(item -> {
					if(item.getSnippet() != null  && item.getSnippet().getTitle() != null ) {
						titles.add(item.getSnippet().getTitle());
					}
				});
				nextPageToken = response.getNextPageToken();
			}while(nextPageToken!=null);
		}
		catch(RestClientException e){
			throw new RuntimeException("Failed tofetch data from Youtube API",e);
		}
		return titles;
	}

	private URI buildApiUri(String playlistId, String nextPageToken) {
		// TODO Auto-generated method stub
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(YOUTUBE_URL)
				.queryParam("part", "snippet")
				.queryParam("maxResults", "50")
				.queryParam("playlistId", playlistId)
				.queryParam("key", API_KEY);
		
		if(nextPageToken != null ) {
			builder.queryParam("pageToken", nextPageToken);
		}
		return builder.build().toUri();
	}

	private String extractPlaylistId(String playlistURL) {
		// TODO Auto-generated method stub
		String parts[] = playlistURL.split("list=");
		if(parts.length>1) {
			return parts[1].split("&")[0];
		}
 		throw new IllegalArgumentException("Invalid Youtube Playlist URL");
	}
	
	public List<Playlist> getPlaylistSongsTitle(){
		return playlistRepository.findAll(); 
	}
	
}

