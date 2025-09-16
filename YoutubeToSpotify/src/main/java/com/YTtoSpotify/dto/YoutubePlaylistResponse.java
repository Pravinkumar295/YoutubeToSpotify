package com.YTtoSpotify.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class YoutubePlaylistResponse {
	private List<PlaylistItem> items;
	private String nextPageToken;
}
