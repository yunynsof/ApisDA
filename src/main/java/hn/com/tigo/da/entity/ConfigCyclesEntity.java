package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ConfigCyclesDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class ConfigCyclesEntity extends EntityBase<ConfigCyclesDTO> {

    private String _coreSelect;

    public ConfigCyclesEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT * FROM DA_CONFIG_CYCLES";
    }
    
    @Override
    protected final ArrayList<ConfigCyclesDTO> fillList(ResultSet result) throws PersistenceException {
        ConfigCyclesDTO dto;
        ArrayList<ConfigCyclesDTO> lista = new ArrayList<ConfigCyclesDTO>();
        try {
            while (result.next()) {
                dto = new ConfigCyclesDTO();
                dto.setCycleCbs(result.getLong("CYCLE_CBS"));
                dto.setBu(result.getString("BU"));
                dto.setCycleBac(result.getLong("CYCLE_BAC"));
                dto.setCycleAs(result.getString("CYCLE_AS"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final List<ConfigCyclesDTO> getConfigCycles() throws PersistenceException {
        return executeSelectStatement(_coreSelect);
    }
}
