package amsapp;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.DriverManager;
//import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Monitor {

    public Monitor() {

     }

    public int fingerid;
    public int state;

    public int hours(int fingerid) {

        this.fingerid = fingerid;
        String url = "jdbc:mysql://localhost:3306/testing?serverTimezone=UTC";
        String user = "root";
        String password = "QUXs@e.K.U@k3gX*";
        String[] dprt = { "ge", "info" };

        for (int i = 0; i < dprt.length; i++) {

            String query = "SELECT state from " + dprt[i] + " WHERE fingerid=" + fingerid + ";";

            try (Connection con = DriverManager.getConnection(url, user, password);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query)) {

                while (rs.next()) {
                    state = rs.getInt("state");
                    state = state + 2;
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Monitor.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }

        }
        return state;
    }

    public void update(int state, int fingerid) {
        this.state = state;

        String url = "jdbc:mysql://localhost:3306/testing?serverTimezone=UTC";
        String user = "root";
        String password = "QUXs@e.K.U@k3gX*";
        String[] dprt = { "ge", "info" };

        for (int j = 0; j < dprt.length; j++) {

            String query = "UPDATE " + dprt[j] + " SET state = " + state + " WHERE fingerid=" + fingerid + ";";

            try (Connection con = DriverManager.getConnection(url, user, password);
                    Statement st = con.createStatement()) {

                st.executeUpdate(query);

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Monitor.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

    }

    // public static void main(String[] args) {
    // Monitor moni = new Monitor();
    // int state = moni.hours(2);
    // moni.update(state, 2);

    // }

}
