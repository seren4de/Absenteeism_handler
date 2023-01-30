package amsapp.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.Container;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JLabel;
// import javax.swing.JComponent.*;

import com.jfoenix.controls.*;
import amsapp.Customer;
import amsapp.FXRouter;
import amsapp.Modify;
import amsapp.log;
import amsapp.UdpBase;
import amsapp.Monitor;

// @Singleton
public class MainController implements Initializable {

    @FXML
    JFXButton Submit;
    @FXML
    JFXButton Clear;
    @FXML
    JFXTextField Username;
    @FXML
    JFXPasswordField Password;
    @FXML
    Label lab;
    @FXML
    JFXButton AddStudent;
    @FXML
    JFXButton RemoveStudent;
    @FXML
    JFXButton ListStudents;
    @FXML
    JFXButton Exit;
    @FXML
    JFXButton ListPresentStudents;
    @FXML
    JFXButton ListStudentsGe;
    @FXML
    JFXButton List;
    @FXML
    JFXButton ListStudentsInfo;
    @FXML
    JFXButton GoBack;
    @FXML
    JFXButton ClearStudents;
    @FXML
    JFXListView list2;
    @FXML
    Label lab0;
    @FXML
    Label lab1;

    @FXML
    Label lab2;
    @FXML
    Label lab3;

    @FXML
    JFXComboBox jfxComboBox;
    @FXML
    FXCollections observableArrayList;
    @FXML
    Hyperlink listge;
    @FXML
    Hyperlink listinfo;

    @FXML
    JFXTextField StudentName;
    @FXML
    JFXTextField StudentID;
    @FXML
    JFXTextField Departement;

    @FXML
    JFXButton Add;
    @FXML
    JFXButton GoBackToOptions;
    @FXML
    Label lab9;
    @FXML
    JFXButton ClearInput;
    @FXML
    JFXButton Remove;
    @FXML
    Label lab10;

    @FXML
    JFXListView list3;
    @FXML
    Label lab11;

    @FXML
    JFXListView list4;
    @FXML
    JFXComboBox jfxComboBox2;
    @FXML
    FXCollections observableArrayList2;

    // @FXML
    // JFXButton
    // @FXML
    // JFXButton
    // @FXML
    // JFXButton
    // @FXML
    // JFXButton

    // @FXML
    // JFXButton Next;

    // @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void submit() {
        String un = Username.getText(), pw = Password.getText();

        Customer cust = new Customer();
        Boolean bOO = cust.login(un, pw);
        if (Boolean.TRUE.equals(bOO)) {
            int irrs = cust.typeLogin(un, pw);
            if (irrs == 1) {
                lab.setText("root login");
                System.out.println("root login");
                try {
                    FXRouter.goTo("rootapage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (irrs == 0) {
                lab.setText("user login");
                System.out.println("user login");
                try {
                    FXRouter.goTo("userapage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // Next.setVisible(true);

        } else {
            lab.setText("check ur login info");
            System.out.println("check ur login info");

        }

    }

    @FXML
    private void clear() {
        Username.setText("");
        Password.setText("");
        // stageService.setFxmlPath("/view/amsapp/view/secondary.fxml");

    }

    // @FXML
    // private void next() throws IOException {
    // FXRouter.goTo("secondPage");
    // }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// 7
    @FXML
    private void addStudent() {
        try {
            FXRouter.goTo("addpage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clears() {
        StudentName.setText("");
        StudentID.setText("");
        Departement.setText("");
    }

    @FXML
    private void adds() throws IOException, SQLException {
        String sn = StudentName.getText(), sid = StudentID.getText(), dprt = Departement.getText();
        UdpBase socket2 = new UdpBase();
        socket2.javasendudppacket("1");
        UdpBase socket1 = new UdpBase();
        int fingerid = socket1.javarecieveudppacket();
        Modify Student = new Modify();
        Student.add(fingerid, sn, sid, false, dprt);
        lab9.setText("Student " + sn + " Successfully added ! ");

    }

    @FXML
    private void GOBACKS() {
        try {
            FXRouter.goTo("rootapage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////// 7777
    @FXML
    private void removeStudent() {
        try {
            FXRouter.goTo("removepage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clearsrm() {
        StudentID.setText("");
    }

    @FXML
    private void removes() {
        String sid = StudentID.getText();
        Modify Student = new Modify();
        Student.remove(sid);
        lab10.setText("Successfully removed !");

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 7
    @FXML
    private void listStudents() {
        try {
            FXRouter.goTo("lssapage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void listStudentsGe() throws SQLException {
        log log = new log();
        String[] rs = log.show("ge");
        // System.out.println(rs);

        list2.getItems().add(0, "Electrical Engineering Departement");
        list2.getItems().add(1,
                "          Full Name                               ID                       Number of hours ");
        for (int i = 0; i < rs.length; i++) {
            list2.getItems().add(i + 2, rs[i]);
        }

        // tableView.getItems().set(0, sa);
        // tableView.getItems().set(1, sb);
    }

    @FXML
    private void listStudentsInfo() throws SQLException {
        log log = new log();
        String[] rs = log.show("info");
        list2.getItems().add(0, "Computer Engineering Departement");
        list2.getItems().add(1,
                "          Full Name                               ID                       Number of hours ");
        for (int i = 0; i < rs.length; i++) {
            list2.getItems().add(i + 2, rs[i]);
        }

    }

    @FXML
    private void clearStudents() throws SQLException {

        // log loginfo = new log();
        // String[] rs = loginfo.show("info");

        // log logge = new log();
        // String[] sr = logge.show("ge");
        list2.getItems().clear();
        list2.getItems().add(0, "");
        list2.getItems().add(1, "");

        // for (int i = 0; i < rs.length + sr.length + 4; i++) {
        // list2.getItems().remove(i);
        // }

    }

    @FXML
    private void GOBACK() {
        try {
            FXRouter.goTo("rootapage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void exit() {
        try {
            FXRouter.goTo("firstPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //////////////////////////////////////////////////////////////////// 7777
    @FXML
    private void listPresentStudents() {
        try {
            FXRouter.goTo("lss2ppage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GOBACKP() {
        try {
            FXRouter.goTo("userapage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void BeginCheck() throws IOException, SQLException {
        list3.getItems().add(0, "          Full Name                               ID        ");

        String set[] = new String[127];
        for (int i = 0; i < 3; i++) {
            log log = new log();
            UdpBase socket2 = new UdpBase();
            socket2.javasendudppacket("2");
            UdpBase socket1 = new UdpBase();
            int fingerid = socket1.javarecieveudppacket();
            Monitor monitor = new Monitor();
            int state = monitor.hours(fingerid);
            monitor.update(state, fingerid);
            set[i] = log.checkID(fingerid);

        }
        for (int i = 0; i < 3; i++) {
            list3.getItems().add(i + 1, set[i]);
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////// 77
    @FXML
    private void list2Students() {
        try {
            FXRouter.goTo("lss2page");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void listStudentsGe2() throws SQLException {
        log logg = new log();
        String[] rsge = logg.show("ge");
        // System.out.println(rs);
        list4.getItems().add(0, "Electrical Engineering Departement");
        list4.getItems().add(1,
                "          Full Name                               ID                       Number of hours ");
        for (int i = 0; i < rsge.length; i++) {
            list4.getItems().add(i + 2, rsge[i]);
        }

    }

    @FXML
    private void listStudentsInfo2() throws SQLException {
        log logg = new log();
        String[] rsinfo = logg.show("info");
        // System.out.println(rs);
        list4.getItems().add(0, "Computer Engineering Departement");
        list4.getItems().add(1,
                "          Full Name                               ID                       Number of hours ");
        for (int i = 0; i < rsinfo.length; i++) {
            list4.getItems().add(i + 2, rsinfo[i]);
        }

    }

    @FXML
    private void GOBACK2() {
        try {
            FXRouter.goTo("userapage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clearStudents2() throws SQLException {
        log loginfo = new log();
        String[] rs = loginfo.show("info");

        log logge = new log();
        String[] sr = logge.show("ge");

        for (int i = 0; i < rs.length + sr.length + 4; i++) {
            list4.getItems().remove(i);
        }

    }

}