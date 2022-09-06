package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ConfigBanksDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class ConfigBanksEntity extends EntityBase<ConfigBanksDTO> {

    private String _coreSelect;

    public ConfigBanksEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT BANK_ID,NAME FROM DA_CONFIG_BANK";
    }
    
    @Override
    protected final ArrayList<ConfigBanksDTO> fillList(ResultSet result) throws PersistenceException {
        ConfigBanksDTO dto;
        ArrayList<ConfigBanksDTO> lista = new ArrayList<ConfigBanksDTO>();
        try {
            while (result.next()) {
                dto = new ConfigBanksDTO();
                dto.setIdBank(result.getLong("BANK_ID"));
                dto.setBank(result.getString("NAME"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final List<ConfigBanksDTO> getConfigBanks() throws PersistenceException {
        return executeSelectStatement(_coreSelect);
    }
}
