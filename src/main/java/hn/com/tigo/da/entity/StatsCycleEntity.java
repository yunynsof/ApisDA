package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.StatsCycleDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class StatsCycleEntity extends EntityBase<StatsCycleDTO> {

    private String _coreSelect;

    public StatsCycleEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT COUNT_CLIENT+(SELECT COUNT(*) FROM DA_LOGS_DA WHERE DETAIL_ERROR LIKE '%NOT_INSERTED%' AND REFERENCE=DA_BANK_PROCESS.ID) AS COUNT_CLIENT,COUNT_CLIENT AS COUNT_DETAIL,\n" +
"(SELECT COUNT(*) FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS=DA_BANK_PROCESS.ID AND STATUS=1) AS PAY_APPLY,\n" +
"(SELECT COUNT(*) FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS=DA_BANK_PROCESS.ID AND STATUS=2) AS REVER,\n" +
"(SELECT COUNT(*) FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS=DA_BANK_PROCESS.ID AND STATUS=-1 AND ERROR_BANK IN('OK','00')) AS ERROR_APPLY,\n" +
"(SELECT LISTAGG('[\"'||ERROR_BANK||'\",'||COUNT(ERROR_BANK)||']',',')WITHIN GROUP (ORDER BY ID_BANK_PROCESS) \n" +
"FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS=DA_BANK_PROCESS.ID AND STATUS=-1 group by ERROR_BANK,ID_BANK_PROCESS) AS ERROR_JSON\n" +
"FROM DA_BANK_PROCESS WHERE ID=?";
    }
    
    @Override
    protected final ArrayList<StatsCycleDTO> fillList(ResultSet result) throws PersistenceException {
        StatsCycleDTO dto;
        ArrayList<StatsCycleDTO> lista = new ArrayList<StatsCycleDTO>();
        try {
            while (result.next()) {
                dto = new StatsCycleDTO();
                dto.setCountClient(result.getInt("COUNT_CLIENT"));
                dto.setCountDetail(result.getInt("COUNT_DETAIL"));
                dto.setPaymentApplied(result.getInt("PAY_APPLY"));
                dto.setErrorPayment(result.getInt("ERROR_APPLY"));
                dto.setReversePayments(result.getInt("REVER"));
                if(result.getString("ERROR_JSON")!=null){
                    dto.setJsonError(result.getString("ERROR_JSON").replace("\u0000", "").replace("\\u0000", ""));
                }else{
                    dto.setJsonError("");
                }
                
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final StatsCycleDTO getStatsCycle(final String id) throws PersistenceException {
        List<StatsCycleDTO> tempDTO = executeSelectStatement(_coreSelect, id);
        return tempDTO.isEmpty() ? null : tempDTO.iterator().next();
    }
}
