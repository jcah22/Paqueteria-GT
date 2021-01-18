package com.example.paqueteriagt;

public class Destinos {

    private Double latitude;
    private Double longitude;
    private String codigo;
    private String billete;
    private String telefono;

    public Destinos() {
        super();
    }

    public Destinos(Double latitude, Double longitude, String codigo, String billete, String telefono) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.codigo = codigo;
        this.billete = billete;
        this.telefono = telefono;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getBillete() {
        return billete;
    }

    public void setBillete(String billete) {
        this.billete = billete;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
