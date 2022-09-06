package hn.com.tigo.da.manager;

import hn.com.tigo.da.dto.AccountBillDTO;
import hn.com.tigo.da.entity.AccountBillEntity;
import java.util.List;
import javax.sql.DataSource;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.Date;
import java.util.HashMap;

public class InvoiceManager extends SessionBase {

    public InvoiceManager(final DataSource dataSource) throws PersistenceException {
        super(dataSource);
    }
    
    // Get a emails by acctCode
    public AccountBillDTO getEmailsAccountBill(final String acctCode) throws PersistenceException {
        AccountBillEntity entity = null;
        try {
            entity = new AccountBillEntity(this);
            AccountBillDTO response = entity.getEmailsAccountBill(acctCode);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());

        }
    }
}
