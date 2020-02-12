package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.exception.ConflictException;
import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    public static final String REGEX_NAME = "[A-Z][a-z]";

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long countArtist() {
        //Récupère le nb d'artist et l'envoyer au client
        return artistRepository.count();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable(value = "id") Long id) {
        return artistRepository.findById(id).get();
    }

    @GetMapping(value = "", params = "name")
    public Artist getNomArtist(@RequestParam("name") String name) {
        // Marche pas trop
        if(name.length() > 120) {
            throw new IllegalArgumentException("Le nom fourni est incorrect !");
        }
        Artist artist = artistRepository.findByName(name);
        return artist;
    }

    @GetMapping
    public Page<Artist> getListeArtist(
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction direction,
        @RequestParam(value = "sortProperty") String sortProperty
    ) {

        if(size <= 0 || size > 50) {
            throw new IllegalArgumentException("La taille des pages doit être comprise entre 0 et 50");
        }
        Long maxPage = artistRepository.count() / size;
        if(page < 0 || page > maxPage ) {
            throw new IllegalArgumentException(("La page" + page + "doit être comprise entre 0 et " + maxPage));
        }

        return artistRepository.findAll(PageRequest.of(page, size, direction, sortProperty));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Artist createArtist(
            @RequestBody Artist artist
    ) throws ConflictException {
        if(artistRepository.findByName(artist.getName()) != null) {
            throw new ConflictException("Un artist existe déjà pour le nom : " + artist.getName());
        }
        return artistRepository.save(artist);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Artist modifierArtist(
            // Pour gérer les erreurs plus tard
            @PathVariable("id") Long idEmploye,
            @RequestBody Artist artist
    ) {
        return artistRepository.save(artist);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(
            @PathVariable("id") Long idArtist
    ) {
        artistRepository.deleteById(idArtist);
    }




}
