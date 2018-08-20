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

import icongenerator.Options;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author flaviusstan
 */
public class OptionsPaneController implements Initializable {

    @FXML
    private Button doneButton, tripleButton, watchExport, iMessExport, automaticGenerate;
    @FXML
    private TextField intText;
    @FXML
    private AnchorPane myPane;

    private final Options opt = new Options();
    private double xOffset, yOffset;
    private boolean watchPressed, iMessPressed, automaticGeneratePressed;

    @FXML
    public void handleDoneButtonCliked(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("Preview.fxml"));
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            Scene sc = new Scene((Pane) root.load());
            sc.setFill(Color.TRANSPARENT);
            stage.setScene(sc);
            stage.show();
            myPane.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(OptionsPaneController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    public void handlePressedOnDone(MouseEvent mE) {
        Image img = new Image(getClass().getResourceAsStream("Icons/DoneButtonPressed.png"));
        doneButton.setGraphic(new ImageView(img));
    }

    @FXML
    public void handleReleasedOnDone(MouseEvent mE) {
        Image img = new Image(getClass().getResourceAsStream("Icons/DoneButton.png"));
        doneButton.setGraphic(new ImageView(img));
    }

    @FXML
    public void handleMouseMovedOnDone(MouseEvent mE) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.WHITE);
        doneButton.setEffect(dropShadow);
    }

    @FXML
    public void handleMouseExitedOnDone(MouseEvent mE) {
        doneButton.setEffect(null);
    }

    @FXML
    public void handleMouseClickedOnPane(MouseEvent mE) {
        xOffset = mE.getSceneX();
        yOffset = mE.getSceneY();
    }

    @FXML
    public void handleClickWatchToogle(MouseEvent mE) {
        Image img;
        if (watchPressed) {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(off).png"));
            watchExport.setGraphic(new ImageView(img));
            opt.setiOSwatch(false);
            watchPressed = false;
        } else {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(on).png"));
            watchExport.setGraphic(new ImageView(img));
            opt.setiOSwatch(true);
            watchPressed = true;
        }
    }

    @FXML
    public void handleClickMessToogle(MouseEvent mE) {
        Image img;
        if (iMessPressed) {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(off).png"));
            iMessExport.setGraphic(new ImageView(img));
            opt.setiMessage(false);
            iMessPressed = false;
        } else {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(on).png"));
            iMessExport.setGraphic(new ImageView(img));
            opt.setiMessage(true);
            iMessPressed = true;
        }
    }

    @FXML
    private void handleClickAutomaticGenerateToogle(MouseEvent mE) {
        Image img;
        if (automaticGeneratePressed) {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(off).png"));
            automaticGenerate.setGraphic(new ImageView(img));
            opt.setAutomaticGeneration(false);
            automaticGeneratePressed = false;
        } else {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(on).png"));
            automaticGenerate.setGraphic(new ImageView(img));
            opt.setAutomaticGeneration(true);
            automaticGeneratePressed = true;
        }
    }

    @FXML
    private void handleClickTripleButton(ActionEvent aE) {
        Image img;
        switch (opt.getpreferResizing()){
            case 1:
                opt.setPreferResizing(2);
                img= new Image(getClass().getResourceAsStream("Icons/AndroidButton.png"));
                tripleButton.setGraphic(new ImageView(img));
                break;
            case 2:
                opt.setPreferResizing(3);
                img= new Image(getClass().getResourceAsStream("Icons/AllButton.png"));
                tripleButton.setGraphic(new ImageView(img));
                break;
            case 3:
                opt.setPreferResizing(1);
                img= new Image(getClass().getResourceAsStream("Icons/iOSButton.png"));
                tripleButton.setGraphic(new ImageView(img));
                break;
                
        }
                
     
        }
    

    private void setTripleButton() {
        Image img;
        int n = opt.getpreferResizing();
        switch (n) {
            case 0:
                img = new Image(getClass().getResourceAsStream("Icons/iOSButton.png"));
                break;
            case 1:
                img = new Image(getClass().getResourceAsStream("Icons/AndroidButton.png"));
                break;
            default:
                img = new Image(getClass().getResourceAsStream("Icons/AllButton.png"));
                break;
        }
        tripleButton.setGraphic(new ImageView(img));
    }

    private void setAutomaticGenerationToggle() {
        Image img;
        if (opt.isAutomaticGeneration()) {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(on).png"));
            automaticGeneratePressed = true;
        } else {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(off).png"));
            automaticGeneratePressed = false;
        }
        automaticGenerate.setGraphic(new ImageView(img));
    }

    private void setDoneButton() {
        Image img = new Image(getClass().getResourceAsStream("Icons/DoneButton.png"));
        doneButton.setGraphic(new ImageView(img));
    }

    private void setToggleWatch() {
        Image img;
        if (opt.isiOSwatch()) {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(on).png"));
            watchPressed = true;
        } else {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(off).png"));
            watchPressed = false;
        }
        watchExport.setGraphic(new ImageView(img));
    }

    private void setToggleMessage() {
        Image img;
        if (opt.isiMessage()) {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(on).png"));
            iMessPressed = true;
        } else {
            img = new Image(getClass().getResourceAsStream("Icons/Toggle(off).png"));
            iMessPressed = false;
        }
        iMessExport.setGraphic(new ImageView(img));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setToggleMessage();
        setToggleWatch();
        setDoneButton();
        setTripleButton();
        setAutomaticGenerationToggle();

    }

}
