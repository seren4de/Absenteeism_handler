package amsapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.*;

public class Modify {
    int fingerid;
    String name, na, dprt;
    Boolean state = false;

    public Modify() {

    }

    public void addid(int fingerid) {
        this.fingerid = fingerid;

    }

    public void add(int fingerid, String name, String na, boolean state, String dprt) {
        this.fingerid = fingerid;
        this.name = name;
        this.na = na;
        this.state = state;
        this.dprt = dprt;

        String url = "jdbc:mysql://localhost:3306/testing?serverTimezone=UTC";
        String user = "root";
        String password = "QUXs@e.K.U@k3gX*";

        String query = "INSERT INTO " + dprt + " (fingerid,name,na,state) VALUES(?,?,?,?) ";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement st = con.prepareStatement(query)) {
            // ResultSet rs = st.executeQuery(query))
            st.setInt(1, fingerid);
            st.setString(2, name);
            st.setString(3, na);
            st.setBoolean(4, state);
            st.executeUpdate();
            System.out.print(" Successfully added,");

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(Modify.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public void remove(String na) {
        this.na = na;

        String url = "jdbc:mysql://localhost:3306/testing?serverTimezone=UTC";
        String user = "root";
        String password = "QUXs@e.K.U@k3gX*";

        String query = "DELETE FROM ge WHERE na=?";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement st = con.prepareStatement(query)) {
            // ResultSet rs = st.executeQuery(query))
            st.setString(1, na);
            st.executeUpdate();
            System.out.print(" Successfully removed,");

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(Modify.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public static void main(String[] args) {
        // static method for add check
        /*
         * Scanner sc = new Scanner(System.in); int fingerid = sc.nextInt(); String name
         * = sc.next(); String na = sc.next(); Boolean state = sc.nextBoolean();
         * modify.add(fingerid, name, na, state); sc.close();
         */

        // static method for remove check
        /*
         * Scanner sc = new Scanner(System.in); String na = sc.next();
         * modify.remove(na); sc.close();
         */
    }

}
