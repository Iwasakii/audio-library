package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(
            @PathVariable("id") Long idAlbum
    ) {
        albumRepository.deleteById(idAlbum);
    }

}
