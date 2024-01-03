package com.example.song.service;

import com.example.song.domain.Song;
import com.example.song.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService
{
    @Autowired
    SongRepository songRepository;

    @Override
    public Song saveSong(Song song)
    {
        return songRepository.save(song);
    }

    @Override
    public List<Song> saveSongs(List<Song> songs)
    {
        return songRepository.saveAll(songs);
    }

    @Override
    public Song getSongById(long id)
    {
        Optional<Song> song = songRepository.findById(id);

        Song emptySong;
        if(song.isPresent())
        {
            emptySong = song.get();
            return emptySong;
        }
        else
        {
            throw new RuntimeException("Song Not Found");
        }
    }

    @Override
    public List<Song> getAllSongs()
    {
        return songRepository.findAll();
    }

    @Override
    public Song updateSong(Song song)
    {
        Optional<Song> optionalSong = songRepository.findById(song.getId());
        if (optionalSong.isPresent())
        {
            Song updateSong = optionalSong.get();
            updateSong.setName(song.getName());
            updateSong.setArtist(song.getArtist());
            updateSong.setSongLength(song.getSongLength());

            songRepository.save(updateSong);
            return updateSong;
        }
        else
        {
            throw new RuntimeException("Song does not exist");
        }
    }

    @Override
    public void deleteSongById(long id)
    {
        songRepository.deleteById(id);
    }

    @Override
    public void deleteSongs()
    {
        songRepository.deleteAll();
    }
}
