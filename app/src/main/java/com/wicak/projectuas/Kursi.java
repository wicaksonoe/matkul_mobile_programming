package com.wicak.projectuas;

public class Kursi {
    private int id, idFilm, status;
    private String nomorSeat, createdBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNomorSeat() {
        return nomorSeat;
    }

    public void setNomorSeat(String nomorSeat) {
        this.nomorSeat = nomorSeat;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
