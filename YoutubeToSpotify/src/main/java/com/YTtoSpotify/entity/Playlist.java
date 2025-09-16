package com.YTtoSpotify.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	
	@ElementCollection
    @CollectionTable(name = "playlist_songs", joinColumns = @JoinColumn(name = "playlist_id"))
    @Column(name = "song_title")
    private List<String> songTitles = new ArrayList<>();
}