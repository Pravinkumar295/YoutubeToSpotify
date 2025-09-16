🎵 YouTube to Spotify Playlist Converter

A Spring Boot application that converts your favorite YouTube playlists into Spotify playlists.
This project automates fetching YouTube videos and creating a new playlist in your Spotify account with the same songs.

🚀 Features

✅ Fetch videos from a YouTube playlist (title, artist, etc.)

✅ Search and match tracks in Spotify’s library

✅ Create a new Spotify playlist in your account

✅ Add matched tracks automatically

✅ Handle unmatched or unavailable songs gracefully

🛠️ Tech Stack

Backend: Java 17+, Spring Boot

APIs:

YouTube Data API v3

Spotify Web API

Authentication: OAuth 2.0 (Spotify + YouTube)

Build Tool: Maven

⚙️ Setup & Installation

Clone the repository

git clone https://[github.com/your-username/youtube-to-spotify.git](https://github.com/Pravinkumar295/YoutubeToSpotify)


Build the project

mvn clean install


Get API Keys

Create a project in Google Cloud Console, enable YouTube Data API v3, and get your API key.

Create an app in Spotify Developer Dashboard to get CLIENT_ID and CLIENT_SECRET.

Configure Environment Variables (inside application.properties or application.yml)

spotify.client.id=your_spotify_client_id
spotify.client.secret=your_spotify_client_secret
spotify.redirect.uri=http://localhost:8080/callback
youtube.api.key=your_youtube_api_key

▶️ Running the App

Run the application with:

mvn spring-boot:run


or, if you’ve built the JAR:

java -jar target/youtube-to-spotify-0.0.1-SNAPSHOT.jar

🔄 Flow Recap

User logs in with Spotify → access token stored in OAuth2AuthorizedClientService.

Get user ID → GET /v1/me

Create a playlist → POST /v1/users/{user_id}/playlists

For each YouTube song title:

Search track → get URI

Collect URIs

Add tracks → POST /v1/playlists/{playlist_id}/tracks

⚙️ Implementation Notes

Uses ExecutorService or CompletableFuture for concurrency.

HTTP calls made with Spring’s RestTemplate or WebClient.

Dynamic thread pool size (based on available processors).

Optional batching (Spotify allows max 100 tracks per add request).

Built using Spring Boot style (Service + Controller).

📌 Example Usage

Input (YouTube playlist URL):

https://www.youtube.com/playlist?list=PLxyz12345


Output (in Spotify):
🎶 A new playlist created: "My YouTube Favorites"

🧩 Future Improvements

Smarter track matching (handle covers/remixes).

Export playlist as JSON/CSV.

Add Web UI for easier interaction.

🤝 Contributing

Contributions are welcome! Fork the repo, create a branch, and submit a pull request.
