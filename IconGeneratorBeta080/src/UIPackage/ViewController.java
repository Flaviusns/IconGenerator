/*
 * Copyright (C) 2018 Flavius Stan
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

import com.sun.javafx.PlatformUtil;
import icongenerator.Creator;
import icongenerator.ImageContainer;
import icongenerator.ImageRescaling;
import icongenerator.Options;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author flaviusstan
 */
public class ViewController implements Initializable {

    @FXML
    private Button export, closeButton, minimizeButton, newImage;
    @FXML
    private AnchorPane myPane;
    @FXML
    private TilePane tilePane = new TilePane();
    @FXML
    private ImageView previewImage;
    @FXML
    private ScrollPane sp;
    @FXML
    private Pane imagePane;

    private double xOffset, yOffset;
    private Options opt;
    private Creator creator;
    private Color color;
    private ImageRescaling imRes;
    private boolean exported = false;
    private boolean automaticGenerate, toBeExported = false;
    private Timeline timeline;
    private File file;
    final Circle circle = new Circle(20, Color.rgb(252, 61, 68));
    KeyValue keyValueX;
    KeyValue keyValueY;
    Duration duration;
    private final KeyCodeCombination exportKey = new KeyCodeCombination(KeyCode.E, KeyCombination.META_ANY);
    private final KeyCodeCombination exit = new KeyCodeCombination(KeyCode.Q, KeyCombination.META_ANY);

    private final Task taskInitProcesing = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            timeline.play();
            tilePane.setEffect(new GaussianBlur(20));
            sp.setEffect(new GaussianBlur(20));
            imagePane.setEffect(new GaussianBlur(20));
            creator.isFinished();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    fillTile();
                }
            });
            return null;
        }

    };

    private final Task taskGetColor = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            getColor(getFile());
            imagePane.setStyle("-fx-background-color: " + colorToHEx());
            BufferedImage im = ImageIO.read(getFile());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Image newImage = SwingFXUtils.toFXImage(imRes.res75x75x2(im), null);
                    previewImage.setImage(newImage);
                }
            });
            return null;
        }
    };

    @FXML
    private void handleKeyCombination(KeyEvent kEvent) {
        System.out.println(kEvent.getCode().toString());
        if (exportKey.match(kEvent)) {
            if (toBeExported) {
                exported = true;
                sp.setEffect(new GaussianBlur(20));
                imagePane.setEffect(new GaussianBlur(20));
                tilePane.setEffect(new GaussianBlur(20));
                creator.export();
                sp.setEffect(null);
                imagePane.setEffect(null);
                tilePane.setEffect(null);
                Image im = new Image(getClass().getResourceAsStream("Icons/DoneJob.png"));
                export.setGraphic(new ImageView(im));
                timeline = new Timeline();
                timeline.setCycleCount(2);
                timeline.setAutoReverse(true);
                keyValueX = new KeyValue(export.scaleXProperty(), 0.7);
                keyValueY = new KeyValue(export.scaleYProperty(), 0.7);
                duration = Duration.millis(850);
                KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            }
        } else if (exit.match(kEvent)) {
            Platform.exit();
        }
    }

    @FXML
    public void handleExportButtonCliked(ActionEvent event) {
        if (!exported && automaticGenerate == true) {
            exported = true;
            sp.setEffect(new GaussianBlur(20));
            imagePane.setEffect(new GaussianBlur(20));
            tilePane.setEffect(new GaussianBlur(20));
            creator.export();
            sp.setEffect(null);
            imagePane.setEffect(null);
            tilePane.setEffect(null);
            Image im = new Image(getClass().getResourceAsStream("Icons/DoneJob.png"));
            export.setGraphic(new ImageView(im));
            timeline = new Timeline();
            timeline.setCycleCount(2);
            timeline.setAutoReverse(true);
            keyValueX = new KeyValue(export.scaleXProperty(), 0.7);
            keyValueY = new KeyValue(export.scaleYProperty(), 0.7);
            duration = Duration.millis(850);
            KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        } else {
            automaticGenerate = true;
            creator = new Creator(file);
            creator.create();
            new Thread(taskInitProcesing).start();
        }
    }

    @FXML
    public void handleExitButtonCliked(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    public void handleMinimizeButtonAction(ActionEvent event) {
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void handlenewImageButtonCliked(ActionEvent event) {

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
    public void handleMouseClickedOnPane(MouseEvent mE) {
        xOffset = mE.getSceneX();
        yOffset = mE.getSceneY();
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
    private void setOnMouseExited(MouseEvent mo) {
        closeButton.setEffect(null);
    }

    @FXML
    private void setOnMouseEnteredExportB(MouseEvent mo) {
        if (!exported && !automaticGenerate) {
            Image img = new Image(getClass().getResourceAsStream("Icons/ExportButtonMoved.png"));
            export.setGraphic(new ImageView(img));
        }

    }

    @FXML
    private void setOnMouseEnteredMinimized(MouseEvent mo) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.WHITE);
        minimizeButton.setEffect(dropShadow);
    }

    @FXML
    private void setOnMouseExitedExportB(MouseEvent mo) {
        if (!exported) {
            Image img = new Image(getClass().getResourceAsStream("Icons/ExportButton.png"));
            export.setGraphic(new ImageView(img));
        }
    }

    @FXML
    private void setOnMouseExitedMinimized(MouseEvent mo) {
        minimizeButton.setEffect(null);
    }

    @FXML
    private void setOnMousePressed(MouseEvent mo) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.BLACK);
        closeButton.setEffect(dropShadow);
        Image img = new Image(getClass().getResourceAsStream("Icons/Xclosed.png"));
        closeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnMousePressedExportB(MouseEvent mo) {
        if (!exported) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(7.0);
            dropShadow.setOffsetX(0.0);
            dropShadow.setOffsetY(0.0);
            dropShadow.setColor(Color.BLACK);
            export.setEffect(dropShadow);
            Image img = new Image(getClass().getResourceAsStream("Icons/ExportButtonPressed.png"));
            export.setGraphic(new ImageView(img));
        }
    }

    @FXML
    private void setOnMousePressedMinimized(MouseEvent mo) {
        Image img = new Image(getClass().getResourceAsStream("Icons/MinimizePressed.png"));
        minimizeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnMouseReleasedExportB(MouseEvent mo) {
        if (!exported) {
            Image img = new Image(getClass().getResourceAsStream("Icons/ExportButton.png"));
            export.setGraphic(new ImageView(img));
        }
    }

    @FXML
    private void setOnMouseReleased(MouseEvent mo) {
        Image img = new Image(getClass().getResourceAsStream("Icons/Xbutton.png"));
        closeButton.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnMouseReleasedMinimized(MouseEvent mo) {
        Image img = new Image(getClass().getResourceAsStream("Icons/Minimize.png"));
        minimizeButton.setGraphic(new ImageView(img));

    }

    @FXML
    private void newImageClicked(ActionEvent mo) {
        try {
            if (PlatformUtil.isWindows()) {
                File f = new File(System.getProperty("java.class.path"));
                File dir = f.getAbsoluteFile().getParentFile();
                String path = dir.toString();
                System.out.println(path);
                String command = "java -jar " + path + "/IconGeneratorBeta081.jar";
                command = command.replace("/", "\\");
                System.out.println(command);
                Runtime.getRuntime().exec(command);
                System.exit(0);
            } else {
                File f = new File(System.getProperty("java.class.path"));
                File dir = f.getAbsoluteFile().getParentFile();
                String path = dir.toString();
                Runtime.getRuntime().exec("java -jar " + path + "/IconGeneratorBeta081.jar");
                System.exit(0);
            }
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setOnMousenewImageReleased(MouseEvent mo) {
        Image img = new Image(getClass().getResourceAsStream("Icons/NewImageButton.png"));
        newImage.setGraphic(new ImageView(img));
    }

    @FXML
    private void setOnMousePressednewImage(MouseEvent mo) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.BLACK);
        newImage.setEffect(dropShadow);
        Image img = new Image(getClass().getResourceAsStream("Icons/NewImageButtonPressed.png"));
        newImage.setGraphic(new ImageView(img));
    }
    
    @FXML
    private void setMouseEntedernewImage(MouseEvent mo){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.WHITE);
        newImage.setEffect(dropShadow);
    }
    @FXML
    private void setOnMouseExitednewImage(MouseEvent mo) {
        newImage.setEffect(null);
    }
    private void getColor(File f) {
        try {
            int c, blue, red, green;
            imRes = new ImageRescaling();
            BufferedImage image = ImageIO.read(f);
            image = imRes.res1x1x1(image);
            c = image.getRGB(0, 0);
            blue = c & 0xff;
            green = (c & 0xff00) >> 8;
            red = (c & 0xff0000) >> 16;
            checkBlackColorAll(red, green, blue);
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkBlackColorAll(int red, int green, int blue) {
        if ((red + green + blue) <= 150) {
            color = Color.rgb((int) (red * 1.5), (int) (green * 1.5), (int) (blue * 1.5), 1);
        } else {
            color = Color.rgb((int) (red * 0.8), (int) (green * 0.8), (int) (blue * 0.8), 1);
        }
    }

    private String colorToHEx() {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
    EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //Hello.
        }
    };

    private File getFile() {
        return file;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        opt = new Options();
        setCloseButton();
        setExportButton();
        setMinimizeButton();
        setNewImageButton();
        setScrollPaneHbarPrefferences();
        setTilePanePropeties();
        setAnimationPropeties();
        export.setDisable(true);
    }

    public void initData(File f) {
        file = f;
        if (opt.isAutomaticGeneration()) {
            automaticGenerate = true;
            creator = new Creator(f);
            creator.create();
            new Thread(taskGetColor).start();
            new Thread(taskInitProcesing).start();
        } else {
            export.setDisable(false);
            setGenerateButton();
            automaticGenerate = false;
            new Thread(taskGetColor).start();
        }
    }

    private void fillTile() {
        ArrayList<ImageContainer> ims = creator.getList();
        ims.forEach((imageContainer) -> {
            final boolean printed = false;
            Timeline timeline1 = new Timeline();
            Timeline timeline2 = new Timeline();
            ImageView imageView;
            Image im = setImageViewPropeties(imageContainer.getImage());
            imageView = new ImageView(im);
            imageView.setEffect(new DropShadow(6.0, 0, 7.0, Color.BLACK));
            imageView.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    KeyValue keyValueX1;
                    KeyValue keyValueY1;
                    Duration duration1;
                    timeline1.setCycleCount(1);
                    keyValueX1 = new KeyValue(imageView.scaleXProperty(), 1.3);
                    keyValueY1 = new KeyValue(imageView.scaleYProperty(), 1.3);
                    duration1 = Duration.millis(150);
                    KeyFrame keyFrame = new KeyFrame(duration1, onFinished, keyValueX1, keyValueY1);
                    timeline1.getKeyFrames().add(keyFrame);
                    timeline1.play();

                    mouseEvent.consume();
                }
            });
            imageView.setOnMouseExited((MouseEvent mouseEvent) -> {
                KeyValue keyValueX1;
                KeyValue keyValueY1;
                Duration duration1;
                timeline2.setCycleCount(1);
                keyValueX1 = new KeyValue(imageView.scaleXProperty(), 0.9);
                keyValueY1 = new KeyValue(imageView.scaleYProperty(), 0.9);
                duration1 = Duration.millis(150);
                KeyFrame keyFrame = new KeyFrame(duration1, onFinished, keyValueX1, keyValueY1);
                timeline2.getKeyFrames().add(keyFrame);
                timeline2.play();
                mouseEvent.consume();
            });
            tilePane.getChildren().addAll(imageView);
        });
        sp.setContent(tilePane);
        tilePane.setEffect(new GaussianBlur(20));
        sp.setVisible(true);
        sp.setEffect(new DropShadow(6.0, 0, 7.0, Color.BLACK));
        tilePane.setEffect(null);
        tilePane.setStyle("-fx-background-color: " + colorToHEx());
        sp.setStyle("-fx-background-color: " + colorToHEx());
        imagePane.setEffect(new DropShadow(6.0, 0, 7.0, Color.BLACK));
        closeButton.setDisable(false);
        export.setDisable(false);
        circle.setVisible(false);
        toBeExported = true;
        newImage.setVisible(true);
    }

    private Image setImageViewPropeties(BufferedImage im) {
        im = imRes.normalView(im);
        Image image = SwingFXUtils.toFXImage(im, null);
        return image;
    }

    private void setCloseButton() {
        Image img = new Image(getClass().getResourceAsStream("Icons/Xbutton.png"));
        closeButton.setGraphic(new ImageView(img));
    }

    private void setExportButton() {
        Image img2 = new Image(getClass().getResourceAsStream("Icons/ExportButton.png"));
        export.setGraphic(new ImageView(img2));
    }

    private void setMinimizeButton() {
        Image img3 = new Image(getClass().getResourceAsStream("Icons/Minimize.png"));
        minimizeButton.setGraphic(new ImageView(img3));
    }

    private void setGenerateButton() {
        Image img = new Image(getClass().getResourceAsStream("Icons/GenerateButton.png"));
        export.setGraphic(new ImageView(img));
    }

    private void setNewImageButton() {
        Image img = new Image(getClass().getResourceAsStream("Icons/NewImageButton.png"));
        newImage.setGraphic(new ImageView(img));
        newImage.setVisible(false);
    }

    private void setScrollPaneHbarPrefferences() {
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setPickOnBounds(false);
    }

    private void setTilePanePropeties() {
        tilePane.setHgap(0);
        tilePane.setVgap(0);
        tilePane.setPrefColumns(3);
        tilePane.setPickOnBounds(false);
    }

    private void setAnimationPropeties() {
        myPane.getChildren().addAll(circle);
        circle.setCenterX(350);
        circle.setCenterY(250);
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        keyValueX = new KeyValue(circle.scaleXProperty(), 2);
        keyValueY = new KeyValue(circle.scaleYProperty(), 2);
        duration = Duration.millis(1500);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(7.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.BLACK);
        circle.setEffect(dropShadow);
        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);
        timeline.getKeyFrames().add(keyFrame);
    }
    /**
     * newImage.setOnAction(new EventHandler<ActionEvent>() { public void
     * handle(ActionEvent event) { Parent root; try { root =
     * FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"),
     * resources); Stage stage = new Stage(); stage.setTitle("My New Stage
     * Title"); stage.setScene(new Scene(root, 450, 450)); stage.show(); // Hide
     * this current window (if this is what you want)
     * ((Node)(event.getSource())).getScene().getWindow().hide(); } catch
     * (IOException e) { e.printStackTrace(); } } *
     */
}
