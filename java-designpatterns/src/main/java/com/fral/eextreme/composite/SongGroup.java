package com.fral.eextreme.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SongGroup extends SongComponent {

    List songComponents = new ArrayList();

    String groupName;
    String groupDescription;

    public SongGroup(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    @Override
    public void add(SongComponent newSongComponent) {
        this.songComponents.add(newSongComponent);
    }

    @Override
    public void remove(SongComponent songComponent) {
        this.songComponents.remove(songComponent);
    }

    @Override
    public SongComponent getComponent(int componentIndex) {
        return (SongComponent) this.songComponents.get(componentIndex);
    }

    @Override
    public void displaySongInfo() {
        System.out.println(getGroupName() + " " + getGroupDescription());

        Iterator songIterator = songComponents.iterator();
        while (songIterator.hasNext()) {
            SongComponent songInfo = (SongComponent)songIterator.next();
            songInfo.displaySongInfo();
        }
    }
}
