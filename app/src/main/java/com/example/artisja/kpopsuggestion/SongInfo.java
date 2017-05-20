package com.example.artisja.kpopsuggestion;

/**
 * Created by artisja on 5/20/17.
 */

public class SongInfo {

    String song,artist,album, imageURL;

    public SongInfo(String songName, String artistName, String albumName, String imageURLName){
        this.song = songName;
        this.artist = artistName;
        this.album = albumName;
        this.imageURL = imageURLName;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getSong() {
        return song;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
