/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Davide Mauri
 */
public class DbOperations {

    public DbOperations() {
    }

    public static ArrayList<Impegno> getImpegno() {
        String nome = "";
        int durata = 0;
        int importanza = 0;
        String data = "";
        String materia = "";
        String tipologia = "";
        ArrayList<Impegno> res = new ArrayList<Impegno>(); // result of the query
        try {
            // create our mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            String myUrl = "jdbc:mysql://localhost:3306/dbimpegni_tep?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            
            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            //String query = "SELECT * FROM impegni WHERE Nome = " + nomeImpegno + "";
            // create the java statement
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM impegni";
            // execute the query, and get a java resultset
            ResultSet rs = stmt.executeQuery(query);
            // iterate through the java resultset
            while (rs.next()) {
                nome = rs.getString("Nome");
                data = rs.getString("Data");
                durata = rs.getInt("Durata");
                importanza = rs.getInt("Importanza");
                materia = rs.getString("Materia");
                tipologia = rs.getString("Tipologia");
                Impegno tempImpegno = new Impegno(nome, data, materia, tipologia, durata, importanza);
                res.add(tempImpegno);
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
        return res;
    }

    /
    public static String printImpegni(ArrayList<Impegno> listaImpegni) {
        String res = "";
        res += "<numeroImpegni>" + listaImpegni.size() + "</numeroImpegni>";
        for (Impegno i : listaImpegni) { // build the string
            res += "<impegno>" + i.myToString() + "</impegno>";
        }
        return res;
    }
    
    
    public static void addUtente(String username, String password){
        try {
            // create our mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            String myUrl = "jdbc:mysql://localhost:3306/dbimpegni_tep?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            
            // insert on db
            String query = "INSERT INTO utenti (username, password) values(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString (1, username);
            stmt.setString (2, password);
            // execute the query, and get a java resultset
            ResultSet rs = stmt.executeQuery(query);
            // iterate through the java resultset
            stmt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }
}