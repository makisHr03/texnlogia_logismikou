package com.festival.music_festival.Artist;

public class Artist {

    private Long id;
    private String name;
    private String permision;


    public Artist() {
    }

    public Artist(Long id, String name, String permision) {
        this.id = id;
        this.name = name;
        this.permision = permision;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermision() {
        return permision;
    }

    public void setPermision(String permision) {
        this.permision = permision;
    }


    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permision='" + permision + '\'' +
                '}';
    }
}
