package amsapp;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

// import java.util.*;

public class Customer {

    public Customer() {
    }

    public String pw;
    public String un;
    public Boolean rrs = false;
    public String set[] = { "", "" };
    public Boolean b = false;
    public int irrs;

    public Boolean login(String un, String pw) {
        this.pw = pw;
        this.un = pw;
        // Customer customer = new Customer();
        // Boolean rrs;

        String url = "jdbc:mysql://localhost:3306/testing?serverTimezone=UTC";
        String user = "root";
        String password = "QUXs@e.K.U@k3gX*";

        String query = "SELECT EXISTS ( SELECT * FROM customers WHERE ( USERNAME='" + un + "' AND PASSWORD='" + pw
                + "'));";

        try (Connection con = DriverManager.getConnection(url, user, password);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                // this.rrs=rrs;
                // System.out.println("result set ok");
                rrs = rs.getBoolean(1);
                // System.out.println("rrs ok");

            }
            // rrs = rs.getBoolean(1);

            // if (rrs == true) {

            // // } catch (SQLException ex) {
            // // Logger lgr = Logger.getLogger(customer.class.getName());
            // // lgr.log(Level.SEVERE, ex.getMessage(), ex);
            // // System.out.println("second catch err !");
            // // }

            // }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Customer.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            System.out.println("first catch err !");

        }
        return rrs;

    }

    public int typeLogin(String un, String pw) {
        this.pw = pw;
        this.un = pw;

        String url = "jdbc:mysql://localhost:3306/testing?serverTimezone=UTC";
        String user = "root";
        String password = "QUXs@e.K.U@k3gX*";

        String rquery = "SELECT state FROM customers WHERE ( USERNAME='" + un + "' AND PASSWORD='" + pw + "');";

        try (Connection rcon = DriverManager.getConnection(url, user, password);
                Statement rst = rcon.createStatement();
                ResultSet result = rst.executeQuery(rquery)) {
            while (result.next()) {

                // set[0] = result.getString(1);
                // set[1] = result.getString(2);

                if (result.getBoolean("state") == true) {

                    irrs = 1;
                } else if (result.getBoolean("state") == false) {
                    irrs = 0;
                }

            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Customer.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            System.out.println("second catch err !");
            irrs = 2;

        }
        return irrs;
    }

    // public static void main(String[] args) {
    // String un, pw;
    // Scanner sc = new Scanner(System.in);
    // System.out.println("USERNAME : ");
    // un = sc.next();
    // System.out.println("PASSWORD : ");
    // pw = sc.next();
    // customer.login(un, pw);
    // sc.close();

    // }

}
