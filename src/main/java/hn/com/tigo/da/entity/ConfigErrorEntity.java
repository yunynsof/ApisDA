package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ConfigErrorDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class ConfigErrorEntity extends EntityBase<ConfigErrorDTO> {

    private String _coreSelect;

    public ConfigErrorEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT BANK_ID,BANK_CODE,DESCRIPTION FROM DA_CONFIG_ERROR WHERE BANK_CODE NOT IN('00','OK')";
    }
    
    @Override
    protected final ArrayList<ConfigErrorDTO> fillList(ResultSet result) throws PersistenceException {
        ConfigErrorDTO dto;
        ArrayList<ConfigErrorDTO> lista = new ArrayList<ConfigErrorDTO>();
        try {
            while (result.next()) {
                dto = new ConfigErrorDTO();
                dto.setBankCode(result.getString("BANK_ID"));
                dto.setBankId(result.getString("BANK_CODE"));
                dto.setDescription(result.getString("DESCRIPTION"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final List<ConfigErrorDTO> getConfigError() throws PersistenceException {
        return executeSelectStatement(_coreSelect);
    }
}
