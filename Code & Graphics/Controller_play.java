package sample;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_play implements Initializable {

    public static Controller_play controller_play = null;
    @FXML
    public ImageView pea;
    @FXML
    public ImageView zomb;
    @FXML
    public ImageView zomb1;
    @FXML
    public ImageView zombF;
    @FXML
    public ImageView sunt;
    @FXML
    public GridPane gridPane;
    @FXML
    public ImageView sunFlower;
    @FXML
    public ImageView peaShooter;
    @FXML
    public ImageView wallnut;
    @FXML
    public ImageView cherry;
    @FXML
    public AnchorPane overall;
    @FXML
    public Button score;

    @FXML public Button level;

    public static ImageView suntoken;

    public FXMLLoader playController;

    public void handle(MouseEvent event) {
        System.out.println("clicked");
        overall.getChildren().remove(sunt);
    }

    public Controller_play(){
        System.out.println(12);
        suntoken = sunt;
    }

    public static Controller_play getController_play() {
        return controller_play;
    }

    public void hand1(ActionEvent actionEvent) {
        AudioClip audio = new AudioClip("file:src/music/buttonclick.wav");
        audio.play();
        try {
            Parent root = Main.PauseController.ap;
            Main.stg.setScene(new Scene(root, 1280, 800));
            Main.stg.show();
        } catch(Exception e) {
            System.out.println("Error1");
        }
    }

    public void hand2(ActionEvent actionEvent) {
        AudioClip audio = new AudioClip("file:src/music/buttonclick.wav");
        audio.play();
    }

    public void moveZombie(Node zomb1 ,int time,int cycleCount,int setToX){
        TranslateTransition tt2;
        tt2 = new TranslateTransition();
        tt2.setNode(zomb1);
        tt2.setDuration(new Duration(time));
        tt2.setCycleCount(cycleCount);
        tt2.setCycleCount(Timeline.INDEFINITE);
        tt2.setToX(setToX);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.a.pause();
        Main.b.play();

        gridPane.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                event.acceptTransferModes( TransferMode.COPY_OR_MOVE );
                event.consume();
            }
        });


        for(Node node: gridPane.getChildren()){
            node.setOnDragDropped(new EventHandler<DragEvent>() {
                Node n = node;
                @Override
                public void handle(DragEvent e)  {
                    Image files = e.getDragboard().getImage();
                    ImageView imageView =new ImageView();
                    imageView.setImage(files);
                    Integer cIndex = gridPane.getColumnIndex(node);
                    Integer rIndex = gridPane.getRowIndex(node);
                    int x = cIndex == null ? 0 : cIndex;
                    int y = rIndex == null ? 0 : rIndex;
                    Bounds boundsInScene = gridPane.localToScene(node.getBoundsInLocal());
                    Main.plants.get(Main.plants.size()-1).setX(n.getLayoutX()+boundsInScene.getMinX());
                    Main.plants.get(Main.plants.size()-1).setY(n.getLayoutY()+boundsInScene.getMinY());
                    gridPane.add(imageView,x,y);
                    e.consume();
                }
            });
        }



    }
}