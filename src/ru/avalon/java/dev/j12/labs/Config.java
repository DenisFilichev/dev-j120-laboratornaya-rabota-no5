/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author denis
 */
public class Config {
    String DBhost = "jdbc:derby:D:\\MyJavaDB\\shop";
    String DBlogin = "root";
    String DBpassword = "root";
    int workingData = 0;
    
    FileInputStream fis;
    Properties properties = new Properties();
    
    public Config () {
        try {
            fis = new FileInputStream("Settings.properties");
            properties.load(fis);
            
            DBhost = properties.getProperty("DBhost");
            DBlogin = properties.getProperty("DBlogin");
            DBpassword = properties.getProperty("DBpassword");
            workingData = Integer.parseInt(properties.getProperty("workingData"));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDBhost() {
        return DBhost;
    }

    public String getDBlogin() {
        return DBlogin;
    }

    public String getDBpassword() {
        return DBpassword;
    }

    public int getWorkingData() {
        return workingData;
    }
    
    
}
