package com.example.song.controller;

import com.example.song.domain.Song;
import com.example.song.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class SongController
{
    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public String showHomePage()
    {
        return "home page";
    }

    @GetMapping("/songs")
    public String getAllSongs(Model model)
    {
        List<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        return "song list";
    }

    @GetMapping("/addForm")
    public String addSongForm(Model model)
    {
        Song song = new Song();
        model.addAttribute("song",song);
        return "add song";
    }

    @PostMapping("/save")
    public String saveSong(@ModelAttribute("song") Song song)
    {
        songRepository.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/updateForm/{id}")
    public String updateSongForm(@PathVariable Long id, Model model) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            model.addAttribute("song", optionalSong.get());
            return "update song";
        } else {
            return "redirect:/songs";
        }
    }

    @PostMapping("/update/{id}")
    public String updateSong(@PathVariable Long id, @ModelAttribute("song") Song updateSong)
    {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent())
        {
            Song existingSong = optionalSong.get();
            existingSong.setName(updateSong.getName());
            existingSong.setArtist(updateSong.getArtist());
            existingSong.setSongLength(updateSong.getSongLength());

            songRepository.save(existingSong);
        }
        return "redirect:/songs";
    }

    @GetMapping("/deleteForm/{id}")
    public String deleteSongForm(@PathVariable Long id, Model model) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            model.addAttribute("song", optionalSong.get());
            return "delete song";
        } else {
            return "redirect:/songs";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            songRepository.deleteById(id);
        }
        return "redirect:/songs";
    }
}
