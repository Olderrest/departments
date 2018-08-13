package com.example.departments.listeners;


import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class Log4jInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        String homeDir = event.getServletContext().getRealPath("/");
        File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
    }
}
