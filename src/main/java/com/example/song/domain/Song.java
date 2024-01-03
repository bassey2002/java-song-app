package com.example.song.domain;

import jakarta.persistence.*;

@Entity(name = "songs")
public class Song
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String artist;
    private float songLength;

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public float getSongLength()
    {
        return songLength;
    }

    public void setSongLength(float songLength)
    {
        this.songLength = songLength;
    }

    public Song(){}

    public Song(Long id, String name, String artist, float songLength)
    {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.songLength = songLength;
    }
}
