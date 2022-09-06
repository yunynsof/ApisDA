package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.DetailReprocessDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;
import java.util.UUID;

public class DetailReprocessEntity extends EntityBase<DetailReprocessDTO> {

    private String _coreInsert;
    private String _coreSelect;

    public DetailReprocessEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreInsert = "INSERT INTO DA_DETAIL_REPROCESS(UUID,ID_REPROCESS,CLIENT,ACCOUNT,SUBSCRIBER,CYCLE) VALUES (?,?,?,?,?,?)";
        _coreSelect = "SELECT CLIENT,ACCOUNT,SUBSCRIBER,CYCLE FROM DA_DETAIL_REPROCESS WHERE ID_REPROCESS=?";
    }
    
    @Override
    protected final ArrayList<DetailReprocessDTO> fillList(ResultSet result) throws PersistenceException {
        DetailReprocessDTO dto;
        ArrayList<DetailReprocessDTO> lista = new ArrayList<DetailReprocessDTO>();
        try {
            while (result.next()) {
                dto = new DetailReprocessDTO();
                dto.setClient(result.getString("CLIENT"));
                dto.setAccount(result.getString("ACCOUNT"));
                dto.setSubs(result.getString("SUBSCRIBER"));
                dto.setCycle(result.getString("CYCLE"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public void insertBankReProcessDetail(final DetailReprocessDTO dto, final String idReprocess) throws PersistenceException{
        executeInsertStatement(_coreInsert,UUID.randomUUID().toString(),idReprocess,dto.getClient(),dto.getAccount(),dto.getSubs(),dto.getCycle());
    }
    
    public List<DetailReprocessDTO> getBankReProcessDetail(final String idReprocess) throws PersistenceException{
        return executeSelectStatement(_coreSelect,idReprocess);
    }
}
