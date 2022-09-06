package hn.com.tigo.da.utils;

import com.newrelic.api.agent.NewRelic;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewRelicImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewRelicImpl.class);

    /**
     * The method register errors in metric the new relic
     * @param message
     */
    public static void addNewRelicError(Throwable message) {
        try {
            NewRelic.noticeError(message);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
    
    /**
     * The method register errors in metric the new relic
     * @param message
     */
    public static void addNewRelicErrorMessage(String message) {
        try {
            NewRelic.noticeError(message);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }

}
