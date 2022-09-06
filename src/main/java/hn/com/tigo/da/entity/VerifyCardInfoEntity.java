
package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.CardInfoDTO;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerifyCardInfoEntity extends EntityBase<CardInfoDTO> {

    private final String _coreSelect;

    public VerifyCardInfoEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelect = "SELECT ID FROM DA_CARD_INFO WHERE ACCTCODE=? AND STATUS=1";
    }

    @Override
    protected List<CardInfoDTO> fillList(ResultSet rs) throws PersistenceException {
        CardInfoDTO dto;
        ArrayList<CardInfoDTO> lista = new ArrayList<CardInfoDTO>();
        dto = new CardInfoDTO();
        try {
            while (rs.next()) {
                dto = new CardInfoDTO();
                dto.setId(rs.getString("ID"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }

    }
    
    public final CardInfoDTO existPaymemtMethod(final String acctCode) throws PersistenceException {
        List<CardInfoDTO> tempDTO = executeSelectStatement(_coreSelect, acctCode);
        return tempDTO.isEmpty() ? null : tempDTO.iterator().next();
    }
}