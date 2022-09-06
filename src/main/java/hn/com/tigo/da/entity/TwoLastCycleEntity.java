package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.TwoLastCycleDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class TwoLastCycleEntity extends EntityBase<TwoLastCycleDTO> {

    private String _coreSelect;
    private String _coreSelectTwo;
    private String _coreSelectLastBanks;

    public TwoLastCycleEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT ID,CYCLE,(SELECT FILE_NAME FROM (SELECT FILE_NAME FROM DA_REPROCESS_FILE WHERE FILE_NAME LIKE '%<LIKE_REPLACE_CYCLE>%' AND FILE_NAME LIKE '%<LIKE_REPLACE_BANK>%' ORDER BY FILE_NAME DESC)\n" +
"WHERE ROWNUM = 1) AS FILE_NAME FROM DA_BANK_PROCESS ";
        _coreSelectTwo = "SELECT ID,CYCLE,''AS FILE_NAME FROM DA_BANK_PROCESS ";
        _coreSelectLastBanks = "SELECT BANK_ID AS ID, CYCLE,'' AS FILE_NAME FROM(SELECT BANK_ID, CYCLE FROM DA_BANK_PROCESS WHERE BANK_ID=1000 ORDER BY CREATED DESC) WHERE ROWNUM=1 UNION ALL SELECT BANK_ID,CYCLE,'' AS FILE_NAME FROM(SELECT BANK_ID, CYCLE FROM DA_BANK_PROCESS WHERE BANK_ID=1001 ORDER BY CREATED DESC) WHERE ROWNUM=1";
    }
    
    @Override
    protected final ArrayList<TwoLastCycleDTO> fillList(ResultSet result) throws PersistenceException {
        TwoLastCycleDTO dto;
        ArrayList<TwoLastCycleDTO> lista = new ArrayList<TwoLastCycleDTO>();
        try {
            while (result.next()) {
                dto = new TwoLastCycleDTO();
                dto.setIdCycle(result.getString("ID"));
                dto.setCycle(result.getString("CYCLE"));
                dto.setFileName(result.getString("FILE_NAME"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final List<TwoLastCycleDTO> getTwoLastCycle(final String cycle, final String bankId) throws PersistenceException {
        return executeSelectStatement(_coreSelectTwo+"WHERE CYCLE LIKE '%"+cycle+"' AND BANK_ID="+bankId+" AND STATUS=4 AND ROWNUM <= 2 ORDER BY CYCLE DESC");
    }
    
    public final List<TwoLastCycleDTO> getOneLastCycle(final String cycle, final String bankName, final String bankId) throws PersistenceException {
        return executeSelectStatement(_coreSelect.replace("<LIKE_REPLACE_CYCLE>", cycle).replace("<LIKE_REPLACE_BANK>", bankName)+"WHERE CYCLE LIKE '%"+cycle+"%' AND BANK_ID="+bankId+" AND STATUS=4 AND ROWNUM <= 1 ORDER BY CYCLE DESC");
    }
    
    public final List<TwoLastCycleDTO> getLastCycleBanks() throws PersistenceException {
        return executeSelectStatement(_coreSelectLastBanks);
    }
}
