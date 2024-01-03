package com.example.song.controller.api;

import com.example.song.domain.Song;
import com.example.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongAPIController
{
    @Autowired
    SongService songService;

    @GetMapping("/getSongs")
    public ResponseEntity<List<Song>> getAllSongs()
    {
        return ResponseEntity.ok().body(songService.getAllSongs());
    }

    @GetMapping("/getSong/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable long id)
    {
        return ResponseEntity.ok().body(songService.getSongById(id));
    }

    @PostMapping("/addSong")
    public ResponseEntity<Song> createSong(@RequestBody Song song)
    {
        return ResponseEntity.ok().body(songService.saveSong(song));
    }

    @PostMapping("/addSongs")
    public List<Song> addSongs(@RequestBody List<Song> songs)
    {
        return songService.saveSongs(songs);
    }

    @PutMapping("/updateSong/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable long id, @RequestBody Song song)
    {
        return ResponseEntity.ok().body(songService.updateSong(song));
    }

    @DeleteMapping("/deleteSong/{id}")
    public HttpStatus deleteSong(@PathVariable long id)
    {
        songService.deleteSongById(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/deleteSongs")
    public String deleteSongs()
    {
        songService.deleteSongs();
        return "All Songs have been deleted";
    }
}
