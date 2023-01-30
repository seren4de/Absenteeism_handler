package amsapp;

import javafx.application.Application;
// import javafx.geometry.Insets;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
// import javafx.scene.layout.FlowPane;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// import com.jfoenix.controls.JFXButton;
// import com.jfoenix.controls.JFXDecorator;
// import com.jfoenix.controls.JFXTextField;
// import com.jfoenix.validation.RequiredFieldValidator;

import io.datafx.controller.ViewController;
// import io.datafx.controller.flow.container.DefaultFlowContainer;
// import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.fxml.FXMLLoader;
import amsapp.FXRouter;

@ViewController(value = "myLayout.fxml", title = "Material Design Example")
public class App extends Application {

    // private Parent root;
    // private String fxmlPath;
    // @Inject
    // private StageService stageService;

    // public App() {
    // System.out.println(this.stageService.getFxmlPath());
    // }

    @Override
    public void start(Stage stage) throws Exception {
        // Parent root =
        // FXMLLoader.load(getClass().getResource("/view/amsapp/view/main.fxml"));

        // stage.setTitle("AMS");
        // stage.setScene(new Scene(root));
        // stage.show();

        FXRouter.bind(this, stage, "AMS", 500, 500); // bind FXRouter
        FXRouter.setAnimationType("fade");
        FXRouter.when("firstPage", "/view/amsapp/view/main.fxml"); // set "firstPage" route
        // FXRouter.when("secondPage", "/view/amsapp/view/secondary.fxml");
        // FXRouter.when("thirdPage", "/view/amsapp/view/third.fxml");
        FXRouter.when("rootapage", "/view/amsapp/view/root1.fxml");
        FXRouter.when("userapage", "/view/amsapp/view/user1.fxml");
        FXRouter.when("lssapage", "/view/amsapp/view/lss1.fxml");
        FXRouter.when("addpage", "/view/amsapp/view/add.fxml");
        FXRouter.when("removepage", "/view/amsapp/view/remove.fxml");
        FXRouter.when("lss2ppage", "/view/amsapp/view/lss2p.fxml");
        FXRouter.when("lss2page", "/view/amsapp/view/lss2.fxml");
        FXRouter.goTo("firstPage");
    }

    public static void main(String[] args) {
        launch(args);
    }
}