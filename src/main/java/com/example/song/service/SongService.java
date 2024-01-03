package com.example.song.service;

import com.example.song.domain.Song;

import java.util.List;

public interface SongService
{
    Song saveSong(Song song);
    List<Song> saveSongs(List<Song> songs);
    Song getSongById(long id);
    List<Song> getAllSongs();
    Song updateSong(Song song);
    void deleteSongById(long id);
    void deleteSongs();
}
