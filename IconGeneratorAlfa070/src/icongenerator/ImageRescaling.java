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

/**
 *
 * @author flaviusstan
 */
public class ImageRescaling {
    
    /**
     * This method resize an image to the specific dimensions 20x20x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res1x1x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(1, 1, type);
        Image scaledImage = image.getScaledInstance(1, 1, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }


        
    //iOS Icon sizes
    
    /**
     * This method resize an image to the specific dimensions 20x20x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res20x20x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(20, 20, type);
        Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 20x20x2
     * @param image The image that will be resized.
     * @return The resized image.
     */
    public BufferedImage res20x20x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(40, 40, type);
        Image scaledImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 20x20x3
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res20x20x3(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(60, 60, type);
        Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 29x29x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res29x29x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(29, 29, type);
        Image scaledImage = image.getScaledInstance(29, 29, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 29x29x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res29x29x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(58, 58, type);
        Image scaledImage = image.getScaledInstance(58, 58, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 29x29x3
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res29x29x3(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(87, 87, type);
        Image scaledImage = image.getScaledInstance(87, 87, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 40x40x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res40x40x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(40, 40, type);
        Image scaledImage = image.getScaledInstance(40 , 40, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 40x40x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res40x40x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(80, 80, type);
        Image scaledImage = image.getScaledInstance(80 , 80, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 40x40x3
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res40x40x3(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(120, 120, type);
        Image scaledImage = image.getScaledInstance(120 , 120, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 50x50x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res50x50x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(50, 50, type);
        Image scaledImage = image.getScaledInstance(50 , 50, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 50x50x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res50x50x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(100, 100, type);
        Image scaledImage = image.getScaledInstance(100 , 100, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 57x57x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res57x57x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(57, 57, type);
        Image scaledImage = image.getScaledInstance(57 , 57, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 57x57x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res57x57x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(114, 114, type);
        Image scaledImage = image.getScaledInstance(114, 114, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 60x60x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res60x60x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(60, 60, type);
        Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    //Correcto
    
    /**
     * This method resize an image to the specific dimensions 60x60x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res60x60x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(120, 120, type);
        Image scaledImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    
    /**
     * This method resize an image to the specific dimensions 60x60x3
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res60x60x3(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(180, 180, type);
        Image scaledImage = image.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 72x72x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res72x72x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(72, 72, type);
        Image scaledImage = image.getScaledInstance(72, 72, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 72x72x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res72x72x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(144, 144, type);
        Image scaledImage = image.getScaledInstance(144, 144, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 76x76x1
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res76x76x1(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(76, 76, type);
        Image scaledImage = image.getScaledInstance(76, 76, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 76x76x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res76x76x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(152, 152, type);
        Image scaledImage = image.getScaledInstance(152, 152, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 76x76x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res76x76x3(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(228, 228, type);
        Image scaledImage = image.getScaledInstance(228, 228, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * This method resize an image to the specific dimensions 83,5x83,5x2
     * @param image The image than you want to resize.
     * @return The resized image.
     */
    public BufferedImage res835x835x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(167, 167, type);
        Image scaledImage = image.getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res75x75x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(150, 150, type);
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    


    //Apple Wath Sizes
    
    
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res40x40x2W(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(80, 80, type);
        Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res44x44x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(88, 88, type);
        Image scaledImage = image.getScaledInstance(88, 88, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res86x86x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(172, 172, type);
        Image scaledImage = image.getScaledInstance(172, 172, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res98x98x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(196, 196, type);
        Image scaledImage = image.getScaledInstance(196, 196, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res24x24x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(48, 48, type);
        Image scaledImage = image.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res275x275x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(55, 55, type);
        Image scaledImage = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res29x29x2W(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(58, 58, type);
        Image scaledImage = image.getScaledInstance(58, 58, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res29x29x3W(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(87, 87, type);
        Image scaledImage = image.getScaledInstance(87, 87, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    
    
    //iMessage images
    
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res1024x768(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(1024, 768, type);
        Image scaledImage = image.getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res60x45x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(120, 90, type);
        Image scaledImage = image.getScaledInstance(120, 90, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res60x45x3(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(180, 135, type);
        Image scaledImage = image.getScaledInstance(180, 135, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res67x50x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(134, 100, type);
        Image scaledImage = image.getScaledInstance(134, 100, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res74x55x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(148, 110, type);
        Image scaledImage = image.getScaledInstance(148, 110, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res27x20x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(54, 40, type);
        Image scaledImage = image.getScaledInstance(54, 40, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res27x20x3(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(81, 60, type);
        Image scaledImage = image.getScaledInstance(81, 60, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res32x24x2(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(64, 48, type);
        Image scaledImage = image.getScaledInstance(64, 48, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage res32x24x3(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(96, 72, type);
        Image scaledImage = image.getScaledInstance(96, 72, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    
    
    //Android sizes
    
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage lDPI(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(36, 36,type);
        Image scaledImage = image.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage mDPI(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(48, 48,type);
        Image scaledImage = image.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage tvDPI(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(64, 64,type);
        Image scaledImage = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage hdDPI(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(72, 72,type);
        Image scaledImage = image.getScaledInstance(72, 72, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage xhdDPI(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(96, 96,type);
        Image scaledImage = image.getScaledInstance(96, 96, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage xxhdDPI(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(144, 144,type);
        Image scaledImage = image.getScaledInstance(144, 144, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    /**
     * 
     * @param image
     * @return 
     */
    public BufferedImage xxxhdDPI(Image image) {
        BufferedImage reImage = (BufferedImage) image;
        int type = reImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : reImage.getType();
        BufferedImage resizedIm = new BufferedImage(192, 192,type);
        Image scaledImage = image.getScaledInstance(192, 192, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    public BufferedImage normalView(BufferedImage image){
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        int width= normalWidth(image.getWidth());
        int heigth= normalHeigth(image.getHeight());
        BufferedImage resizedIm = new BufferedImage(width, heigth,type);
        Image scaledImage = image.getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
        resizedIm.getGraphics().drawImage(scaledImage, 0, 0, null);
        return resizedIm;
    }
    
    private int normalHeigth(int n){
        if(n>670){
            n=(int)(n*0.3);
        }
        return n;
    }
    private int normalWidth(int n){
        if(n>670){
            n=(int)(n*0.2);
        }
        return n;
    }
}
