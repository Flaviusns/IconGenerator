/*
 * Copyright (C) 2017 Flavius Stan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package UIPackage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author flaviusstan
 */
public class PreviewController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button closeButton, minimizeButton, optionsButton;
    @FXML
    private Label imageHere, sL, sR, iL, iR;
    @FXML
    private Pane myPane;
    
    private double xOffset, yOffset;
    private Boolean correct = false;
    private final KeyCodeCombination newFile= new KeyCodeCombination(KeyCode.N, KeyCombination.META_ANY);
    private final KeyCodeCombination exit= new KeyCodeCombination(KeyCode.Q, KeyCombination.META_ANY);

    @FXML
    private void handleKeyCombination(KeyEvent kEvent){
        System.out.println(kEvent.getCode().toString());
        if(newFile.match(kEvent)){
            initFileChooser();
        }
        else if(exit.match(kEvent)){
            Platform.exit();
        }
    }
    
    
    @FXML
    private void mouseDragDropped(DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasFiles() && correct) {
            imageHere.setText("Procesing...");
            try {
                File file = db.getFiles().get(0);
                FXMLLoader root = new FXMLLoader(getClass().getResource("View.fxml"));
                Stage stage = new Stage(StageStyle.TRANSPARENT);
                Scene sc = new Scene((AnchorPane) root.load());
                sc.setFill(Color.TRANSPARENT);
                stage.setScene(sc);
                ViewController vC = root.<ViewController>getController();
                vC.initData(file);
                stage.show();
                myPane.getScene().getWindow().hide();
                e.setDropCompleted(true);
                e.consume();
            } catch (IOException ex) {
                Logger.getLogger(PreviewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    public void handleMinimizeButtonAction(ActionEvent event) {
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    private void setOnClicked(MouseEvent ms) {
        Image img = new Image(getClass().getResourceAsStream("Icons/Xclosed.png"));
        closeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnClickedMinimized(MouseEvent ms) {
        Image img = new Image(getClass().getResourceAsStream("Icons/MinimizePressed.png"));
        minimizeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnClickedPressedClose(MouseEvent ms) {
        Image img = new Image(getClass().getResourceAsStream("Icons/Xclosed.png"));
        closeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnClickedRelease(MouseEvent ms) {
        Image img = new Image(getClass().getResourceAsStream("Icons/Xbutton.png"));
        closeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void handleClickedFileChooserButton(ActionEvent aE) {
        initFileChooser();
    }

    @FXML
    private void setOnClickedReleaseMinimized(MouseEvent ms) {
        Image img = new Image(getClass().getResourceAsStream("Icons/Minimize.png"));
        minimizeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnClickedPressedMinimized(MouseEvent ms) {
        Image img = new Image(getClass().getResourceAsStream("Icons/MinimizePressed.png"));
        minimizeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnClickedPressedPreferrences(MouseEvent ms) {
        Image img = new Image(getClass().getResourceAsStream("Icons/PreferencesPressed.png"));
        optionsButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnClickedReleasePreferrences(MouseEvent ms) {
        Image img = new Image(getClass().getResourceAsStream("Icons/Preferences.png"));
        optionsButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnDragEntered(DragEvent e) {
        if (e.getDragboard().hasFiles()) {
            e.acceptTransferModes(TransferMode.COPY);
            Dragboard db = e.getDragboard();
            if (db.getFiles().get(0).getName().endsWith(".png") || db.getFiles().get(0).getName().endsWith(".jpg")|| db.getFiles().get(0).getName().endsWith(".JPG")) {
                DropShadow dS = new DropShadow(BlurType.GAUSSIAN, Color.WHITE, 50, 0, 0, 10);
                sL.setEffect(dS);
                iL.setEffect(dS);
                sR.setEffect(dS);
                iR.setEffect(dS);
                correct = true;
                e.consume();
            } else {
                DropShadow dropShadow = new DropShadow();
                dropShadow.setRadius(100.0);
                dropShadow.setColor(Color.BLACK);
                imageHere.setEffect(dropShadow);
                sL.setEffect(dropShadow);
                iL.setEffect(dropShadow);
                sR.setEffect(dropShadow);
                iR.setEffect(dropShadow);
                myPane.setEffect(new InnerShadow(BlurType.GAUSSIAN, Color.BLACK, 50, 0, 0, 0));
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Your file isn´t accepted.");
                alert.showAndWait();
                correct = false;
                e.consume();
            }
        }
    }

    @FXML
    private void mouseDragOver(final DragEvent e) {
        if (e.getDragboard().hasFiles()) {
            e.acceptTransferModes(TransferMode.COPY);
        } else {
            e.consume();
        }
    }

    @FXML
    private void setOnDragExited(DragEvent e) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(1.0);
        dropShadow.setOffsetY(4.0);
        dropShadow.setColor(Color.BLACK);
        imageHere.setEffect(dropShadow);
        sL.setEffect(dropShadow);
        iL.setEffect(dropShadow);
        sR.setEffect(dropShadow);
        iR.setEffect(dropShadow);
        myPane.setEffect(null);
        correct = false;
    }

    @FXML
    private void setOnMouseEntered(MouseEvent mo) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.WHITE);
        closeButton.setEffect(dropShadow);
    }

    @FXML
    private void setOnMouseEnteredMinimize(MouseEvent mo) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.WHITE);
        minimizeButton.setEffect(dropShadow);
    }

    @FXML
    private void setOnMouseEnteredPreferrences(MouseEvent mo) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.WHITE);
        optionsButton.setEffect(dropShadow);
    }

    @FXML
    private void setOnMouseExited(MouseEvent mo) {
        closeButton.setEffect(null);
    }

    @FXML
    private void setOnMouseExitedMinimize(MouseEvent mo) {
        minimizeButton.setEffect(null);
    }

    @FXML
    private void setOnMouseExitedPreferrences(MouseEvent mo) {
        optionsButton.setEffect(null);
    }

    @FXML
    private void handleClickonOptionsButton(ActionEvent ae) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("OptionsPane.fxml"));
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            Scene sc = new Scene((AnchorPane) root.load());
            sc.setFill(Color.TRANSPARENT);
            stage.setScene(sc);
            OptionsPaneController vC = root.<OptionsPaneController>getController();
            stage.show();
            myPane.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(PreviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void handleMouseDraggedOnPane(MouseEvent mE) {
        myPane.getScene().getWindow().setX(mE.getScreenX() - xOffset);
        myPane.getScene().getWindow().setY(mE.getScreenY() - yOffset);
    }

    @FXML
    public void handleMousePressedOnPane(MouseEvent mE) {
        xOffset = mE.getSceneX();
        yOffset = mE.getSceneY();
    }
    private void initFileChooser(){
        imageHere.setText("Please \nwait....");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File f = fileChooser.showOpenDialog(myPane.getScene().getWindow());
        if (f == null) {
            imageHere.setText("Drop your\nimage here");
        } else {
            if (f.getName().endsWith(".png") || f.getName().endsWith(".jpg")) {
                try {
                    Stage stage = new Stage(StageStyle.TRANSPARENT);
                    FXMLLoader root = new FXMLLoader(getClass().getResource("View.fxml"));
                    Scene sc = new Scene((AnchorPane) root.load());
                    sc.setFill(Color.TRANSPARENT);
                    stage.setScene(sc);
                    ViewController vC = root.<ViewController>getController();
                    vC.initData(f);
                    stage.show();
                    myPane.getScene().getWindow().hide();
                } catch (IOException ex) {
                    Logger.getLogger(PreviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                imageHere.setText("Drop your\nimage here");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Your file isn´t accepted.");
                alert.showAndWait();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        imageHere.setText("Drop your\nimage here");
        Image img = new Image(getClass().getResourceAsStream("Icons/Xbutton.png"));
        Image imgSR = new Image(getClass().getResourceAsStream("Icons/SectorSuperiorDerecho.png"));
        Image imgSL = new Image(getClass().getResourceAsStream("Icons/SectorSuperiorIzquierdo.png"));
        Image imgIR = new Image(getClass().getResourceAsStream("Icons/SectorInferiorDerecho.png"));
        Image imgIL = new Image(getClass().getResourceAsStream("Icons/SectorInferiorIzquierdo.png"));
        Image imMini = new Image(getClass().getResourceAsStream("Icons/Minimize.png"));
        Image imOpt = new Image(getClass().getResourceAsStream("Icons/Preferences.png"));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(6.0);
        dropShadow.setOffsetX(1.0);
        dropShadow.setOffsetY(4.0);
        dropShadow.setColor(Color.BLACK);
        imageHere.setEffect(dropShadow);
        closeButton.setGraphic(new ImageView(img));
        minimizeButton.setGraphic(new ImageView(imMini));
        sL.setGraphic(new ImageView(imgSL));
        sR.setGraphic(new ImageView(imgSR));
        iL.setGraphic(new ImageView(imgIL));
        iR.setGraphic(new ImageView(imgIR));
        optionsButton.setGraphic(new ImageView(imOpt));
        sL.setEffect(dropShadow);
        iL.setEffect(dropShadow);
        sR.setEffect(dropShadow);
        iR.setEffect(dropShadow);

    }

}
