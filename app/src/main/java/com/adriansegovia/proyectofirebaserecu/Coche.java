package com.adriansegovia.proyectofirebaserecu;

import java.util.Objects;

public class Coche {
    private String modelo;
    private String color;

    public Coche(String modelo, String color) {
        this.modelo = modelo;
        this.color = color;
    }
    public Coche() {
        this.modelo = "coche";
        this.color = "rojo";
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coche)) return false;
        Coche coche = (Coche) o;
        return modelo.equals(coche.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelo);
    }

    @Override
    public String toString() {
        return "Coche{" +
                "modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
