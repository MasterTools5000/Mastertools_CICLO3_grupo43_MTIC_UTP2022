
package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Herramienta;
import connection.DBConnection;

public class HerramientaController implements IHerramientaController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();
        DBConnection con = new DBConnection();
        String sql = "Select * from herramienta";

        if (ordenar == true) {
            sql += " order by tipo " + orden;
        }

        List<String> herramientas = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");
                String marca = rs.getString("marca");
                int copias = rs.getInt("copias");
                boolean novedad = rs.getBoolean("novedad");
                Herramienta herramienta = new Herramienta(id, titulo, tipo, marca, copias, novedad);
                herramientas.add(gson.toJson(herramienta));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    finally {
            con.Desconectar();

        }
        return gson.toJson(herramientas);

    }

 
    
     @Override
    public String devolver(int id, String username) {

        DBConnection con = new DBConnection();
        String sql = "Delete from alquiler where id= " + id + " and username = '" 
                + username + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.Desconectar();
        }

        return "false";
    }

    @Override
    public String sumarCantidad(int id) {

        DBConnection con = new DBConnection();

        String sql = "Update herramienta set copias = (Select copias from herramienta where id = " 
                + id + ") + 1 where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.Desconectar();
        }

        return "false";

    }
    
    
    @Override
    public String alquilar(int id, String username) {

        Timestamp fecha = new Timestamp(new Date().getTime());
        DBConnection con = new DBConnection();
        String sql = "Insert into alquiler values ('" + id + "', '" + username + "', '" + fecha + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.Desconectar();
        }
        return "false";
    }

     @Override
    public String modificar(int id) {

        DBConnection con = new DBConnection();
        String sql = "Update herramienta set copias = (copias - 1) where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.Desconectar();
        }

        return "false";

    }

}


