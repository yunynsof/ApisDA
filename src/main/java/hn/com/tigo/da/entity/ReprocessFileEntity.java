package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ReprocessFileDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;
import java.util.UUID;

public class ReprocessFileEntity extends EntityBase<ReprocessFileDTO> {

    private String _coreSelect;
    private String _coreInsert;

    public ReprocessFileEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT * FROM DA_REPROCESS_FILE";
        _coreInsert = "INSERT INTO DA_REPROCESS_FILE(ID,FILE_NAME,FILE_B64,USER_) VALUES(?,?,?,?)";
    }
    
    @Override
    protected final ArrayList<ReprocessFileDTO> fillList(ResultSet result) throws PersistenceException {
        ReprocessFileDTO dto;
        ArrayList<ReprocessFileDTO> lista = new ArrayList<ReprocessFileDTO>();
        try {
            while (result.next()) {
                dto = new ReprocessFileDTO();
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public void insertReprocess(final ReprocessFileDTO dto, final String uuid) throws PersistenceException {
        executeInsertStatement(_coreInsert,uuid,dto.getFileName(),dto.getFileB64(),dto.getUser());
    }
}
