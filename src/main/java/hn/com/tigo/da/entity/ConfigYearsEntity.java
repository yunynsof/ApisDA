package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ConfigYearsDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class ConfigYearsEntity extends EntityBase<ConfigYearsDTO> {

    private String _coreSelect;

    public ConfigYearsEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT ID,YEAR FROM DA_CONFIG_YEARS";
    }
    
    @Override
    protected final ArrayList<ConfigYearsDTO> fillList(ResultSet result) throws PersistenceException {
        ConfigYearsDTO dto;
        ArrayList<ConfigYearsDTO> lista = new ArrayList<ConfigYearsDTO>();
        try {
            while (result.next()) {
                dto = new ConfigYearsDTO();
                dto.setId(result.getLong("ID"));
                dto.setYear(result.getString("YEAR"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final List<ConfigYearsDTO> getConfigYears() throws PersistenceException {
        return executeSelectStatement(_coreSelect);
    }
}
