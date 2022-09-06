package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ApproveCyclesDTO;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApproveCyclesEntity extends EntityBase<ApproveCyclesDTO> {
    private final String _coreSelect;
    private final String _coreInsert;
    private final String _coreUpdate;
    
    public ApproveCyclesEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelect = "SELECT UUID, ID_PROCESS, USER_REQUEST, TO_CHAR(DATE_REQUEST, 'DD/MM/YYYY FMHH:MI:SS AM') AS DATE_REQUEST_STRING, COMMENT_REQUEST, USER_APPROVE,TO_CHAR(DATE_APPROVE, 'DD/MM/YYYY FMHH:MI:SS AM') AS DATE_APPROVE_STRING, COMMENT_APPROVE, STATUS FROM DA_APPROVE_CYCLES WHERE ID_PROCESS=? AND STATUS=0 AND TYPE=?";
        _coreInsert="INSERT INTO DA_APPROVE_CYCLES(UUID,ID_PROCESS,TYPE,USER_REQUEST,COMMENT_REQUEST) VALUES (?,?,?,?,?)";
        _coreUpdate="UPDATE DA_APPROVE_CYCLES SET USER_APPROVE=?,COMMENT_APPROVE=?,STATUS=1,DATE_APPROVE=SYSDATE WHERE ID_PROCESS=? AND TYPE=?";
    }
    
    @Override
    protected List<ApproveCyclesDTO> fillList(ResultSet rs) throws PersistenceException {
        ApproveCyclesDTO dto;
        ArrayList<ApproveCyclesDTO> lista = new ArrayList<ApproveCyclesDTO>();
        dto = new ApproveCyclesDTO();
        try {
            while (rs.next()) {
                dto = new ApproveCyclesDTO();
                dto.setUuid(rs.getString("UUID"));                
                dto.setIdProcess(rs.getString("ID_PROCESS"));
                dto.setUserRequest(rs.getString("USER_REQUEST"));
                dto.setDateRequestString(rs.getString("DATE_REQUEST_STRING"));
                dto.setCommentRequest(rs.getString("COMMENT_REQUEST"));
                dto.setUserApprove(rs.getString("USER_APPROVE"));
                dto.setDateApproveString(rs.getString("DATE_APPROVE_STRING"));
                dto.setCommentApprove(rs.getString("COMMENT_APPROVE"));
                dto.setStatus(rs.getInt("STATUS"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }

    }

    public final ApproveCyclesDTO getSolicitudAprobacion(final String idProcess,final int type) throws PersistenceException {
            List<ApproveCyclesDTO> aprobaciones = executeSelectStatement(_coreSelect, idProcess,type);
        return !aprobaciones.isEmpty() ? aprobaciones.get(0) : null;
    }
    
    public final void insertAprobacion(final String cycle,final long type, final String user, final String comment) throws PersistenceException{
        executeInsertStatement(_coreInsert, UUID.randomUUID().toString(),cycle,type,user,comment);
    }
    
    public final void upadteAprobacion(final String cycle,final String type, final String user, final String comment) throws PersistenceException{
        executeInsertStatement(_coreUpdate,user, comment,cycle,type);
    }
    
}
