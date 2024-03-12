/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author thinkpad
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying.........................");
        //1. get Context Scope
        ServletContext context = sce.getServletContext();
        //2. Read file properties
        String filePath = context.getInitParameter("SITEMAPS_FILE_PATH");
        Properties siteMaps = new Properties();
        InputStream is = null;
        try {
            is = context.getResourceAsStream(filePath);
            siteMaps.load(is);
            //3. Caching on Context Scope
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("MyContextListener _ IO: " + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex){
                    context.log("MyContextListener _ IO: " + ex.getMessage());
                }
            }
        }
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Say goodbye!");
    }
}
