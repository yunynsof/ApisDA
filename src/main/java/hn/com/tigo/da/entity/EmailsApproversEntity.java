package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.EmailsApproversDTO;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CorreosAprobadoresEntity.
 * @author Kerim Fortin
 * @since 10/2/2022
 */

public class EmailsApproversEntity extends EntityBase<EmailsApproversDTO> {

    private final String _coreSelect;

    public EmailsApproversEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelect = "SELECT (SELECT LISTAGG(EMAIL,';')WITHIN GROUP (ORDER BY USER) FROM DA_APPROVERS_BANK WHERE STATUS=1) AS EMAILS  FROM DUAL";
    }

    @Override
    protected List<EmailsApproversDTO> fillList(ResultSet rs) throws PersistenceException {
        EmailsApproversDTO dto;
        ArrayList<EmailsApproversDTO> lista = new ArrayList<EmailsApproversDTO>();
        dto = new EmailsApproversDTO();
        try {
            while (rs.next()) {
                dto = new EmailsApproversDTO();
                dto.setCorreos(rs.getString("EMAILS"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }

    }
    public final EmailsApproversDTO getCorreosAprobadores() throws PersistenceException {
        List<EmailsApproversDTO> correos = executeSelectStatement(_coreSelect);
        return !correos.isEmpty() ? correos.get(0) : null;
    }
}
