package amsapp;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.DocFlavor.STRING;

import com.mysql.cj.protocol.Resultset;

import org.graalvm.compiler.core.common.util.IntList;

//import java.util.*;

public class log {

    public log() {

    }

    public int fingerid;
    public String name, na, dprt;
    public Boolean state = false;

    public String[] show(String dprt) {
        this.dprt = dprt;
        String url = "jdbc:mysql://localhost:3306/testing?serverTimezone=UTC";
        String user = "root";
        String password = "QUXs@e.K.U@k3gX*";
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> nas = new ArrayList<String>();
        ArrayList<String> state = new ArrayList<String>();

        String query = "SELECT name, na, state from " + dprt + ";";

        try (Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                names.add(rs.getString(1));
                nas.add(rs.getString(2));
                state.add(String.valueOf(rs.getInt(3)));
            }

            String[] nameArr = new String[names.size()];
            nameArr = names.toArray(nameArr);

            int[] s = new int[nameArr.length];
            int[] t = new int[nameArr.length];

            for (int i = 0; i < nameArr.length; i++) {
                s[i] = 30 - nameArr[i].length();
                String f = " ";
                for (int j = 0; j < s[i]; j++) {
                    nameArr[i] = nameArr[i] + f;
                }
            }

            String[] naArr = new String[nas.size()];
            naArr = nas.toArray(naArr);

            for (int i = 0; i < naArr.length; i++) {

                t[i] = (s[i] * 4) / 7;
                String f = " ";
                for (int j = 0; j < t[i]; j++) {
                    naArr[i] = f + naArr[i];
                }
                naArr[i] = naArr[i] + "                               ";
            }

            String[] stateArr = new String[state.size()];
            stateArr = state.toArray(stateArr);

            String[] holder = new String[names.size()];

            for (int i = 0; i < names.size(); i++) {

                holder[i] = nameArr[i] + naArr[i] + stateArr[i];

            }
            return holder;

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(log.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            String[] cholder = new String[1];
            return cholder;

        }

    }

    public static String checkID(int fingerid) {

        String url = "jdbc:mysql://localhost:3306/testing?serverTimezone=UTC";
        String user = "root";
        String password = "QUXs@e.K.U@k3gX*";
        String[] dprt = { "ge", "info" };

        String set = "", s, s1, s2;

        for (int i = 0; i < dprt.length; i++) {

            String query = "SELECT name, na from " + dprt[i] + " WHERE fingerid=" + fingerid + ";";
            try (Connection con = DriverManager.getConnection(url, user, password);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    s = rs.getString("name");
                    s1 = rs.getString("na");
                    int z = s.length();
                    int t = 30 - z;
                    int v = (t * 4) / 7;
                    String f = " ";

                    for (int j = 0; j < t; j++) {
                        s = s + f;
                    }
                    for (int j = 0; j < v; j++) {
                        s1 = f + s1;
                    }

                    set = s + s1;
                }

                System.out.println(set);

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(log.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        return set;
    }

    public static void main(String[] args) {

    }
}