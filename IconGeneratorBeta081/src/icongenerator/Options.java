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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flaviusstan
 */
public final class Options implements Serializable {

    private boolean iOSwatch,iMessage,automaticGeneration;

    private int maxHistorySaves = 10;

    private int preferResizing = 3;

    private boolean[] booleanSaves = new boolean[3];

    private int[] intSaves = new int[2];

    public Options() {
        if (loadData()) {
            System.out.println("Todo correcto");
            iOSwatch=booleanSaves[0];
            iMessage=booleanSaves[1];
            automaticGeneration=booleanSaves[2];
            intSaves[0] = maxHistorySaves;
            intSaves[1] = preferResizing;
        } else {
            preferedData();
        }
        toStringData();
    }

    public boolean isAutomaticGeneration() {
        return automaticGeneration;
    }

    public void setAutomaticGeneration(boolean automaticGeneration) {
        this.automaticGeneration = automaticGeneration;
        saveData();
    }

    public boolean isiOSwatch() {
        return iOSwatch;
    }

    public void setPreferResizing(int preferResizing) {
        this.preferResizing = preferResizing;
        saveData();
    }

    public void setiOSwatch(boolean iOSwatch) {
        this.iOSwatch = iOSwatch;
        saveData();
    }

    public int getpreferResizing() {
        return preferResizing;
    }

    public boolean isiMessage() {
        return iMessage;
    }

    public void setiMessage(boolean iMessage) {
        this.iMessage = iMessage;
        saveData();
    }

    public int getMaxHistorySaves() {
        return maxHistorySaves;
    }

    public void setMaxHistorySaves(int maxHistorySaves) {
        this.maxHistorySaves = maxHistorySaves;
        saveData();
    }

    public void preferedData() {
        iOSwatch = true;
        iMessage = true;
        automaticGeneration=true;
        maxHistorySaves = 10;
        preferResizing = 3;
        saveData();
    }

    public void toStringData() {
        System.out.println("---------------");
        System.out.println("Watch State" + iOSwatch);
        System.out.println("iMessage State" + iMessage);
        System.out.println("History State" + maxHistorySaves);
        System.out.println("Export History" + preferResizing);
        System.out.println("**************");
    }
    public void saveData() {
        try {
            toStringData();
            booleanSaves[0] = iOSwatch;
            booleanSaves[1] = iMessage;
            booleanSaves[2]= automaticGeneration;
            intSaves[0] = maxHistorySaves;
            intSaves[1] = preferResizing;
            FileOutputStream ostreamBool = new FileOutputStream("Bool.dat");
            FileOutputStream ostreamInt = new FileOutputStream("Int.dat");
            ObjectOutputStream oosBool = new ObjectOutputStream(ostreamBool);
            ObjectOutputStream oosInt = new ObjectOutputStream(ostreamInt);
            oosBool.writeObject(booleanSaves);
            oosInt.writeObject(intSaves);
            oosBool.close();
            oosInt.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean loadData() {
        try {
            FileInputStream istreamBool = new FileInputStream("Bool.dat");
            FileInputStream istreamInt = new FileInputStream("Int.dat");
            ObjectInputStream oisBool = new ObjectInputStream(istreamBool);
            ObjectInputStream oisInt = new ObjectInputStream(istreamInt);
            booleanSaves = (boolean[]) oisBool.readObject();
            intSaves = (int[]) oisInt.readObject();
            istreamBool.close();
            istreamInt.close();
            return true;
        } catch (IOException ioe) {
            System.out.println("IO: " + ioe.getMessage());
            preferedData();
            loadData();
            return false;
        } catch (ClassNotFoundException ex) {
            preferedData();
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
