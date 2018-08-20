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

import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author flaviusstan
 */
public class Creator {

    //Attributes
    private static final String[] methods = {"res20x20x1", "res20x20x2", "res20x20x3", "res29x29x1", "res29x29x2", "res29x29x3", "res40x40x1", "res40x40x2", "res40x40x3", "res50x50x1",
        "res50x50x2", "res57x57x1", "res57x57x2", "res60x60x1", "res60x60x2", "res60x60x3", "res72x72x1", "res72x72x2", "res76x76x1", "res76x76x2",
        "res76x76x3", "res835x835x2"};
    private static final String[] methodsWatch = {"res40x40x2W", "res44x44x2", "res86x86x2", "res98x98x2", "res24x24x2", "res275x275x2",
        "res29x29x2W", "res29x29x3W"};
    private static final String[] methodsMessage = {"res1024x768", "res60x45x2", "res60x45x3", "res67x50x2", "res74x55x2", "res27x20x2", "res27x20x3",
        "res32x24x2", "res32x24x3"};
    private static final String[] methodsAndroid = {"lDPI", "mDPI", "tvDPI", "hdDPI", "xhdDPI", "xxhdDPI", "xxxhdDPI"};
    private int position = 0;
    private int saved = 0;
    private String path2;
    private ExecutorService pool = Executors.newFixedThreadPool(4);
    private Image im;
    private final ImageRescaling r = new ImageRescaling();
    private Path path, path1, tempPath, pathiOs, pathWatch, pathMessage, pathAndroid;
    private String dirPath = System.getProperty("user.dir");
    private boolean finished = false;
    private boolean onePath = false;
    private File file, dir;
    private Semaphore sem = new Semaphore(1, true);
    private ArrayList<ImageContainer> ims = new ArrayList<>();
    private ArrayList<ImageContainer> imsiOs = new ArrayList<>();
    private ArrayList<ImageContainer> imsWatch = new ArrayList<>();
    private ArrayList<ImageContainer> imsMessage = new ArrayList<>();
    private ArrayList<ImageContainer> imsAndroid = new ArrayList<>();
    private ArrayList<String> listForGenerate = new ArrayList<>();
    private Options opt;

    /**
     * Constructor
     *
     * @param file
     */
    public Creator(File file) {
        try {
            opt = new Options();
            im = ImageIO.read(file);
            this.path = file.toPath();
            this.file = file;
            dir = file.getParentFile();
            listForGenerate.addAll(Arrays.asList(methods));
            if (opt.isiMessage()) {
                listForGenerate.addAll(Arrays.asList(methodsMessage));
            }
            if (opt.isiOSwatch()) {
                listForGenerate.addAll(Arrays.asList(methodsWatch));
            }
            listForGenerate.addAll(Arrays.asList(methodsAndroid));
            listForGenerate.add("end");
        } catch (IOException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method create the threads to resize the image.
     */
    public void create() {
        listForGenerate.forEach((_item) -> {
            pool.execute(new Generator(im, r, this, path));
        });
    }

    /**
     * This method returns the string that the thread will execute.
     *
     * @return The method in a string object that will be execute in the thread.
     */
    public synchronized String getMethod() {
        String method = null;
        try {
            sem.acquire();
            if (position < listForGenerate.size()) {
                method = listForGenerate.get(position);
                position++;
                sem.release();
                return method;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sem.release();
            return method;
        }
    }

    /**
     * This method save the image resized with the name parametre, in the
     * choosen path
     *
     * @param image The resized image.
     * @param name The name ob the resized image.
     */
    public synchronized void saveImage(BufferedImage image, String name, String method) {
        try {
            sem.acquire();
            ims.add(new ImageContainer(name, image));
            if (!nameFound(name, method, image)) {
                System.out.println("Problem finding name");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            saved++;
            notifyAll();
            sem.release();
            notify();
        }

    }

    public void export() {
        createFolder();
        createAndroidFolder();
        switch (opt.getpreferResizing()) {
            case 1:
                createAndExportiOs();
                break;
            case 2:
                createAndExportAndroid();
                break;
            case 3:
                createAndExportiOs();
                createAndExportAndroid();

        }
    }

    private void createAndExportiOs() {
        createIosFolder();
        createWatchFolder();
        createMessFolder();
        exportiOsImages();
        exportMessageImages();
        exportWatchImages();
        File file1= path1.toFile();
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file1);
        } catch (IOException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void createAndExportAndroid() {
         createAndroidFolder();
         exportAndroidImages();
    }

    private void exportiOsImages() {
        imsiOs.forEach((imageContainer) -> {
            String myPath = pathiOs.toString() + "//" + imageContainer.getName() + ".png";
            try {
                File f = new File(pathiOs.toString() + "//" + imageContainer.getName() + ".png");
                if (!f.exists()) {
                    ImageIO.write(imageContainer.getImage(), "png", new File(myPath));
                }
            } catch (IOException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void exportWatchImages() {
        imsWatch.forEach((imageContainer) -> {
            String myPath = pathWatch.toString() + "//" + imageContainer.getName() + ".png";
            try {
                File f = new File(pathWatch.toString() + "//" + imageContainer.getName() + ".png");
                if (!f.exists()) {
                    ImageIO.write(imageContainer.getImage(), "png", new File(myPath));
                }
            } catch (IOException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void exportMessageImages() {
        imsMessage.forEach((imageContainer) -> {
            String myPath = pathMessage.toString() + "//" + imageContainer.getName() + ".png";
            try {
                File f = new File(pathMessage.toString() + "//" + imageContainer.getName() + ".png");
                if (!f.exists()) {
                    ImageIO.write(imageContainer.getImage(), "png", new File(myPath));
                }
            } catch (IOException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void exportAndroidImages() {
        imsAndroid.forEach((imageContainer) -> {
            String myPath = pathAndroid.toString() + "//" + imageContainer.getName() + ".png";
            try {
                File f = new File(pathAndroid.toString() + "//" + imageContainer.getName() + ".png");
                if (!f.exists()) {
                    ImageIO.write(imageContainer.getImage(), "png", new File(myPath));
                }
            } catch (IOException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public ArrayList<ImageContainer> getList() {
        return ims;
    }

    private void createFolder() {
        try {
            String name, n;
            name = file.getName();
            n = name.substring(0, name.lastIndexOf("."));
            File f = new File(dir.getAbsolutePath() + "//" + n);
            if (!f.exists()) {
                path1 = Files.createDirectory(Paths.get(dir.getAbsolutePath() + "//" + n));
            } else {
                path1 = f.toPath();
            }
        } catch (IOException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createIosFolder() {
        try {
            String name, n;
            name = file.getName() + "iOS";
            n = name.substring(0, name.lastIndexOf("."));
            n = n + "iOS";
            File f = new File(path1 + "//" + n);
            System.out.println(path1.toString());
            if (!f.exists()) {
                pathiOs = Files.createDirectory(Paths.get(path1.toString() + "//" + n));
            } else {
                pathiOs = f.toPath();
            }
        } catch (IOException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createAndroidFolder() {
        try {
            String name, n;
            name = file.getName();
            n = name.substring(0, name.lastIndexOf("."));
            n = n + "Android";
            File f = new File(path1 + "//" + n);
            if (!f.exists()) {
                pathAndroid = Files.createDirectory(Paths.get(path1.toString() + "//" + n));
            } else {
                pathAndroid = f.toPath();
            }
        } catch (IOException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createWatchFolder() {
        if (opt.isiOSwatch()) {
            try {
                String name, n;
                name = "WatchImages";
                n = name;
                File f = new File(pathiOs + "//" + n);
                if (!f.exists()) {
                    pathWatch = Files.createDirectory(Paths.get(pathiOs.toString() + "//" + n));
                } else {
                    pathWatch = f.toPath();
                }
            } catch (IOException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createMessFolder() {
        if (opt.isiMessage()) {
            try {
                String name, n;
                name = "iMessageImages";
                n = name;
                File f = new File(pathiOs + "//" + n);
                if (!f.exists()) {
                    pathMessage = Files.createDirectory(Paths.get(pathiOs.toString() + "//" + n));
                } else {
                    pathMessage = f.toPath();
                }
            } catch (IOException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Path getPath() {
        return path1;
    }

    public String getPath2() {
        return path2;
    }

    public Path getTempPath() {
        return tempPath;
    }

    public synchronized void isFinished() {
        while (!finished) {
            try {
                wait();
            } catch (InterruptedException ie) {
            }
        }
    }

    public synchronized void finished() {
        while (ims.size() != listForGenerate.size() - 1) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finished = true;
        pool.shutdown();
        notify();
    }

    private boolean nameFound(String name, String method, BufferedImage image) {
        if (cheakNameiOs(method)) {
            imsiOs.add(new ImageContainer(name, image));
            return true;
        }
        if (cheakNameWatch(method)) {
            imsWatch.add(new ImageContainer(name, image));
            return true;
        }
        if (cheakNameMessage(method)) {
            imsMessage.add(new ImageContainer(name, image));
            return true;
        }
        if (cheakNameAndroid(method)) {
            imsAndroid.add(new ImageContainer(name, image));
            return true;
        }
        return false;
    }

    private boolean cheakNameiOs(String name) {
        for (int i = 0; i < methods.length; i++) {
            if (name.equals(methods[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean cheakNameWatch(String name) {
        for (int i = 0; i < methodsWatch.length; i++) {
            if (name.equals(methodsWatch[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean cheakNameMessage(String name) {
        for (int i = 0; i < methodsMessage.length; i++) {
            if (name.equals(methodsMessage[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean cheakNameAndroid(String name) {
        for (int i = 0; i < methodsAndroid.length; i++) {
            if (name.equals(methodsAndroid[i])) {
                return true;
            }
        }
        return false;
    }
}
