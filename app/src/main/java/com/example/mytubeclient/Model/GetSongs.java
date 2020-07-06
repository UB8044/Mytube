package com.example.mytubeclient.Model;

public class GetSongs {
    String songsCategory, songTitle, songLink, key;

    public GetSongs(String songsCategory, String songTitle, String songLink) {

        if (songTitle.equals("")){
            songTitle = "No Title";
        }

        this.songsCategory = songsCategory;
        this.songTitle = songTitle;
        this.songLink = songLink;
    }

    public GetSongs() {
    }

    public String getSongsCategory() {
        return songsCategory;
    }

    public void setSongsCategory(String songsCategory) {
        this.songsCategory = songsCategory;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
