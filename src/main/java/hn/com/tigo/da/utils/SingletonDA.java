/*
 * SingletonDA class that configure the cache
 */
package hn.com.tigo.da.utils;

import hn.com.tigo.da.manager.DAManager;
import hn.com.tigo.josm.persistence.core.ServiceSessionEJB;
import hn.com.tigo.josm.persistence.core.ServiceSessionEJBLocal;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.HashMap;
import javax.ejb.Singleton;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.LoggerFactory;


@Singleton
public class SingletonDA implements SingletonDALocal {
private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SingletonDA.class);
HashMap<String, String> params = new HashMap<>();
DAManager manager = null;

public SingletonDA() {
   
    Timer timer;
    timer = new Timer();

    TimerTask task = new TimerTask() {
        @Override
        public void run(){
           setParamsDb();
        }
    };
    timer.schedule(task, 10, (86400*1000));
}

@Override
public void setParamsDb(){
    try {
        ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
        manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
        params = manager.listAllParam();
        LOGGER.info("PARAMETROS ESTABLECIDOS");
    } catch (PersistenceException ex) {
            LOGGER.error(ex.getMessage());
    } finally {
        if (manager != null) {
            try {
                manager.close();
            } catch (PersistenceException e) {
                LOGGER.error("Could not close the database");
            }
        }
    }
}

@Override
public HashMap<String, String> getParams() {
    if(params.isEmpty()){
      setParamsDb();
    }
    return params;
}
}