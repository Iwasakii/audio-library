package com.ipiecoles.java.audio.repository;

import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByTitle(String title);
}
