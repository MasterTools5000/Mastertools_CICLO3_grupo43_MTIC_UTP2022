package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Alquiler;
import connection.DBConnection;

public class AlquilerController implements IAlquilerController {

    @Override
    public String listarAlquileres(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select l.id, l.titulo, l.tipo, l.novedad, a.fecha from herramientas l "
                + "inner join alquiler a on l.id = a.id inner join usuario u on a.username = u.username "
                + "where a.username = '" + username + "'";

        List<String> alquileres = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");
                boolean novedad = rs.getBoolean("novedad");
                Date fechaAlquiler = rs.getDate("fecha");

                Alquiler alquiler = new Alquiler(id, titulo, fechaAlquiler, novedad, tipo);

                alquileres.add(gson.toJson(alquiler));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.Desconectar();
        }
        return gson.toJson(alquileres);
    }
}