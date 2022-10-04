package controller;

import java.util.Map;

public interface IUsuarioController {

    public String login(String username, String contrasena);

    public String register(String username, String contrasena,
            String nombre, String apellido, String email, double saldo, boolean premium);

    public String pedir(String username);

    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevosApellido, String nuevoEmail,
            double nuevoSaldo, boolean nuevoPremium);

    public String verCopias(String username);

    public String devolverHerramientas(String username, Map<Integer, Integer> copias);

    public String eliminar(String username);

    public String restarDinero(String username, double nuevoSaldo);

}
