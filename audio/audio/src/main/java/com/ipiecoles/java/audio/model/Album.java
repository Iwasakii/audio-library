package com.ipiecoles.java.audio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) &&
                Objects.equals(title, album.title) &&
                Objects.equals(artist, album.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                '}';
    }
}
