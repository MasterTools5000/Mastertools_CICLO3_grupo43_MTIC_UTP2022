/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author ANDRES
 */
public class Herramienta {

private int id;
private String titulo;
private String tipo;
private String marca;
private int copias;
private boolean novedad;

    public Herramienta(int id, String titulo, String tipo, String marca, int copias, boolean novedad) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.marca = marca;
        this.copias = copias;
        this.novedad = novedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public boolean isNovedad() {
        return novedad;
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }

    @Override
    public String toString(){
        return "Herramienta{" + "id=" + id + ", titulo=" + titulo + ", tipo=" + tipo + ", marca=" + marca + ", oopias=" + copias + ", novedad" + novedad + '}';
}




    
}
