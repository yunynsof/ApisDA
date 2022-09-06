package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.LogsDaDTO;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;

public class LogsDaEntity extends EntityBase<LogsDaDTO> {

    private String _coreSelect;
    private ArrayList<String> globalLista=null;

    public LogsDaEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT ID,CREATED,DETAIL_ERROR,REFERENCE,SRT FROM DA_LOGS_DA WHERE TYPE_ERROR=? AND TRUNC(CREATED)>=TRUNC(TO_DATE('DATE_IN','yyyy-mm-dd')) AND TRUNC(CREATED) <=TRUNC(TO_DATE('DATE_END','yyyy-mm-dd'))";
    }
    
    @Override
    protected final ArrayList<LogsDaDTO> fillList(ResultSet rs) throws PersistenceException {
        globalLista = new ArrayList<>();
        try {
            while (rs.next()) {
                ArrayList<String> lista = new ArrayList<>();
                lista.add(rs.getString("ID"));
                lista.add(rs.getString("CREATED"));
                lista.add(rs.getString("DETAIL_ERROR"));
                lista.add(rs.getString("REFERENCE"));
                lista.add(rs.getString("SRT"));
                String json = new Gson().toJson(lista);
                globalLista.add(json);
            }
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
        ArrayList<LogsDaDTO> dto = null;
        return dto;
    }
    
    public final ArrayList<String> getLogErrorByType(final String typeError, final String dateIn, final String dateEnd) throws PersistenceException {
        executeSelectStatement(_coreSelect.replace("DATE_IN", dateIn).replace("DATE_END", dateEnd), typeError);
        return globalLista;
    }
}
