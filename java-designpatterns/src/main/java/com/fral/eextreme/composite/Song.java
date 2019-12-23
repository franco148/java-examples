package com.fral.eextreme.composite;

public class Song extends SongComponent {

    String songName;
    String bandName;
    int releaseYear;

    public Song(String songName, String bandName, int releaseYear) {
        this.songName = songName;
        this.bandName = bandName;
        this.releaseYear = releaseYear;
    }

    @Override
    public String getSongName() {
        return this.songName;
    }

    @Override
    public String getBandName() {
        return this.bandName;
    }

    @Override
    public int getReleaseYear() {
        return this.releaseYear;
    }

    @Override
    public void displaySongInfo() {
        System.out.println(getSongName() + " was recorded by " + getBandName() + " in " + getReleaseYear());
    }
}
