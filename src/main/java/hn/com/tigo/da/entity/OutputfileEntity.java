package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.OutputfileDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class OutputfileEntity extends EntityBase<OutputfileDTO> {

    private String _coreSelect;

    public OutputfileEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT BASE64,FILE_NAME_DA,(SELECT FILEBASE64 FROM DA_BANK_FILE_INCLU_AND_EXCLU WHERE ID=UUID_INCLU_EXCLU) AS BASE64INCLUEXCLU,(SELECT FILE_NAME_DA FROM DA_BANK_FILE_INCLU_AND_EXCLU WHERE ID=UUID_INCLU_EXCLU) AS FILE_NAME_EXCLU FROM DA_BANK_PROCESS WHERE ID='<REPLACE>' AND STATUS!=0";
    }
    
    @Override
    protected final ArrayList<OutputfileDTO> fillList(ResultSet result) throws PersistenceException {
        OutputfileDTO dto;
        ArrayList<OutputfileDTO> lista = new ArrayList<OutputfileDTO>();
        try {
            while (result.next()) {
                dto = new OutputfileDTO();
                dto.setFileBase64(result.getString("BASE64"));
                dto.setFileName(result.getString("FILE_NAME_DA"));
                dto.setFileNameExclu(result.getString("FILE_NAME_EXCLU"));
                dto.setFileBase64Exclu(result.getString("BASE64INCLUEXCLU"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final OutputfileDTO getOutputsFile(final String id) throws PersistenceException {
        List<OutputfileDTO> tempDTO = executeSelectStatement(_coreSelect.replace("<REPLACE>", id));
        return tempDTO.isEmpty() ? null : tempDTO.iterator().next();
    }
}
