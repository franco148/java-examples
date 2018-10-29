package com.fral.eextreme.composite;

public class SongListGenerator {

    public static void main(String[] args) {
        SongComponent industrialMusic = new SongGroup("Industrial", "aaaaaaaaaaaa");
        SongComponent heavyMetalMusic = new SongGroup("Heavy Metal", "bbbbbbbbbbb");
        SongComponent dubstepMusic = new SongGroup("Dubstep", "cccccccccccc");

        SongComponent everySong = new SongGroup("Song List", "Every song available");
        everySong.add(industrialMusic);

        industrialMusic.add(new Song("Head Like a Hole", "NIN", 1990));
        industrialMusic.add(new Song("Headhunter", "Front 242", 1988));
        industrialMusic.add(dubstepMusic);

        everySong.add(heavyMetalMusic);

        heavyMetalMusic.add(new Song("War Pigs", "Black Sabath", 1970));
        heavyMetalMusic.add(new Song("Ace of Spades", "Motorhead", 1980));

        DiscJockey crazyLarry = new DiscJockey(everySong);
        crazyLarry.getSongList();
    }
}
