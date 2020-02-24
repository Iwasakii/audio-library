package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.AlbumRepository;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

//    @PostMapping
//    public Artist addAlbumToArtist(
//         @PathVariable("idArtist") Long idArtist,
//         @RequestBody String title
//    ) {
//        Artist artist= artistRepository.findById(idArtist).get();
//        Album album = albumRepository.findByTitle(title);
//
//        album.setArtist(artist);
//
//        albumRepository.save(album);
//
//        return artist;
//    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }


}
