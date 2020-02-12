package com.ipiecoles.java.audio.repository;

import com.ipiecoles.java.audio.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(String name);
}
