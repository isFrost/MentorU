/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author isend_000
 */
public class Log4jListener implements ServletContextListener{
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        String homeDir = event.getServletContext().getRealPath("/");
        File propertiesFile=new File(homeDir,"WEB-INF/log4j.xml");
        PropertyConfigurator.configure(propertiesFile.toString());
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    
    } 
}
