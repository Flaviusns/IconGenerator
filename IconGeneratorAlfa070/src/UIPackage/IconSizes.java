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

import java.awt.SplashScreen;
import java.awt.Toolkit;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author flaviusstan
 */
public class IconSizes extends Application {

    
    private double xOffset,yOffset;
    private SplashScreen mySplash;

    @Override
    public void start(Stage stage) throws Exception {
        mySplash = SplashScreen.getSplashScreen();
        Parent root = FXMLLoader.load(getClass().getResource("Preview.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("Icons/Icono.png")));
        if(mySplash!=null){
            mySplash.close();
        }
        stage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private  void splashInit()
    {
        mySplash = SplashScreen.getSplashScreen();
        if (mySplash != null)
        {   // if there are any problems displaying the splash this will be null
//            Dimension ssDim = mySplash.getSize();
//            int height = ssDim.height;
//            int width = ssDim.width;
//            // stake out some area for our status information
//            splashTextArea = new Rectangle2D.Double(15., height*0.88, width * .45, 32.);
//            splashProgressArea = new Rectangle2D.Double(width * .55, height*.92, width*.4, 12 );
//
//            // create the Graphics environment for drawing status info
//            splashGraphics = mySplash.createGraphics();
//            font = new Font("Dialog", Font.PLAIN, 14);
//            splashGraphics.setFont(font);
//            
//            // initialize the status info
//            splashText("Starting");
//            splashProgress(0);
        }
    }

}
