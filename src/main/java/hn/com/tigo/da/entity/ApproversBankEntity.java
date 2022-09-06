package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ApproversBankDTO;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApproversBankEntity extends EntityBase<ApproversBankDTO> {
    private final String _coreSelect; 
    
    public ApproversBankEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelect = "SELECT * FROM DA_APPROVERS_BANK WHERE USER_=? AND STATUS=1 AND PROCESS=?";
    }
    
    @Override
    protected List<ApproversBankDTO> fillList(ResultSet rs) throws PersistenceException {
        ApproversBankDTO dto;
        ArrayList<ApproversBankDTO> lista = new ArrayList<ApproversBankDTO>();
        dto = new ApproversBankDTO();
        try {
            while (rs.next()) {
                dto = new ApproversBankDTO();
                dto.setId(rs.getLong("ID"));                
                dto.setUsuario(rs.getString("USER_"));
                dto.setEmail(rs.getString("EMAIL"));
                dto.setStatus(rs.getInt("STATUS"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }

    }
    
    public final ApproversBankDTO getAprobador(final String user,final String process) throws PersistenceException {
        List<ApproversBankDTO> approvers = executeSelectStatement(_coreSelect, user,process);
        return !approvers.isEmpty() ? approvers.get(0) : null;
    }
    
}
