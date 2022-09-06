package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.AccountBillDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class AccountBillEntity extends EntityBase<AccountBillDTO> {

    private String _coreSelect;

    public AccountBillEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT email FROM Account_bill WHERE integrationid=?";
    }
    
    @Override
    protected final ArrayList<AccountBillDTO> fillList(ResultSet result) throws PersistenceException {
        AccountBillDTO dto;
        ArrayList<AccountBillDTO> lista = new ArrayList<AccountBillDTO>();
        try {
            while (result.next()) {
                dto = new AccountBillDTO();
                dto.setEmail(result.getString("email"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final AccountBillDTO getEmailsAccountBill(final String acctCode) throws PersistenceException {
        List<AccountBillDTO> tempDTO = executeSelectStatement(_coreSelect, acctCode);
        return tempDTO.isEmpty() ? null : tempDTO.iterator().next();
    }
}
