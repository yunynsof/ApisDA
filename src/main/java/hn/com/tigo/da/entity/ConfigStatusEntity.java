package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ConfigStatusDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class ConfigStatusEntity extends EntityBase<ConfigStatusDTO> {

    private String _coreSelect;

    public ConfigStatusEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT CODE,DESCRIPTION FROM DA_CONFIG_STATUS";
    }
    
    @Override
    protected final ArrayList<ConfigStatusDTO> fillList(ResultSet result) throws PersistenceException {
        ConfigStatusDTO dto;
        ArrayList<ConfigStatusDTO> lista = new ArrayList<ConfigStatusDTO>();
        try {
            while (result.next()) {
                dto = new ConfigStatusDTO();
                dto.setCode(result.getLong("CODE"));
                dto.setDescription(result.getString("DESCRIPTION"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final List<ConfigStatusDTO> getConfigStatus() throws PersistenceException {
        return executeSelectStatement(_coreSelect);
    }
}
