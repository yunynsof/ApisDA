/*
 * SingletonInvoiceLocal interface that configure the cache
 */
package hn.com.tigo.da.utils;

import java.util.HashMap;
import javax.ejb.Local;

@Local
public interface SingletonDALocal {
    public void setParamsDb();
    public HashMap<String, String> getParams();
}
