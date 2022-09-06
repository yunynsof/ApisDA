/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.da.apisda;

import io.swagger.jaxrs.config.BeanConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SwaggerConfiguration extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Apis DA");
        beanConfig.setVersion("1.0.0");
        beanConfig.setDescription("Apis DA platform");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("192.168.159.46:7004");
        beanConfig.setBasePath("/ApisDA");
        beanConfig.setResourcePackage("hn.com.tigo.da.apisda");
        beanConfig.setScan(true);
    }
}
