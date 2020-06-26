package com.wicak.projectuas;

public class Film {
    private int id;
    private String judulFilm, waktuTayang, createdBy;
    private float harga;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public void setJudulFilm(String judulFilm) {
        this.judulFilm = judulFilm;
    }

    public String getWaktuTayang() {
        return waktuTayang;
    }

    public void setWaktuTayang(String waktuTayang) {
        this.waktuTayang = waktuTayang;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }
}
