/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

/**
 *
 * @author ANDRES
 */
public interface IHerramientaController {
    public String listar(boolean ordenar, String orden);

    public String devolver(int id, String username);

    public String sumarCantidad(int id);

    public String alquilar(int id, String username);

    public String modificar(int id);
}
