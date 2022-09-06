
package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.DeleteAccountDTO;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteAccountEntity extends EntityBase<DeleteAccountDTO> {

    private String _coreSelect;
    private String _coreInsert;
    private String _coreUpdate;

    public DeleteAccountEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelect = "SELECT id,created,status,json FROM DA_BANK_DELETE_ACCOUNT ";
        _coreInsert = "INSERT INTO DA_BANK_DELETE_ACCOUNT (id,json) VALUES (?,?) ";
        _coreUpdate = "UPDATE DA_BANK_DELETE_ACCOUNT SET status = 'F' WHERE id = ?";
    }

    @Override
    protected final ArrayList<DeleteAccountDTO> fillList(ResultSet result) throws PersistenceException {
        DeleteAccountDTO dto;
        ArrayList<DeleteAccountDTO> lista = new ArrayList<DeleteAccountDTO>();
        try {
            while (result.next()) {
                dto = new DeleteAccountDTO();
                dto.setId(result.getString("id"));
                dto.setCreated(result.getDate("created"));
                dto.setStatus(result.getString("status"));
                dto.setJson(result.getString("json"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }

    public final DeleteAccountDTO selectByUUID(final String id) throws PersistenceException {
        String sql = _coreSelect + " where id=?";
        List<DeleteAccountDTO> tempDTO = executeSelectStatement(sql, id);
        return tempDTO.isEmpty() ? null : tempDTO.iterator().next();
    }

    public final List<DeleteAccountDTO> getDeleteAccount() throws PersistenceException {
        return executeSelectStatement(_coreSelect + "  where status = 'P'");
    }

    public final void insertBankDeleteStatus(final String id, final String json) throws PersistenceException {
        executeInsertStatement(_coreInsert, id, json);
    }

    public final void updateBankDeleteStatus(final String id) throws PersistenceException {
        executeUpdateStatement(_coreUpdate, id);
    }
}
