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
package icongenerator;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flaviusstan
 */
public class Generator implements Runnable {

    //Attributes
    private Method met;
    private Image image = null;
    private String method, name;
    private final ImageRescaling res;
    private final Creator creator;
    private BufferedImage buf = null;
    private final Path path;

    /**
     * Contructor
     *
     * @param im
     * @param r
     * @param c
     * @param p
     */
    public Generator(Image im, ImageRescaling r, Creator c, Path p) {
        image = im;
        res = r;
        creator = c;
        path = p;
    }

    /**
     * The run method get a dimension to scale the image.
     */
    @Override
    public void run() {
        try {
            String n;
            name = path.getFileName().toString();
            method = creator.getMethod();//The scale with which the image is resized
            if (method != null) {
                if (!method.equals("end")) {
                    met = res.getClass().getMethod(method, Image.class);
                    buf = (BufferedImage) met.invoke(res, image);
                    n = name.substring(0, name.lastIndexOf("."));
                    name = n + method;
                    creator.saveImage(buf, name, method);
                    System.out.println("Image saved: "+name);
                } else {
                    creator.finished();
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
