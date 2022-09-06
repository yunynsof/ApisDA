package hn.com.tigo.da.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hn.com.tigo.da.dto.DALogsDADTO;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;

public class DALogsDAEntity extends EntityBase<DALogsDADTO> {

	private String _coreInsert;
	private String _coreDelete;

	public DALogsDAEntity(final SessionBase sessionBase) throws PersistenceException {
		super(sessionBase);
		_coreInsert = "INSERT INTO DA_LOGS_DA (ID, TYPE_ERROR, DETAIL_ERROR, REFERENCE, SRT) VALUES (?,?,?,?,?) ";
		_coreDelete = "DELETE FROM DA_LOGS_DA WHERE REFERENCE = ? ";
	}

	@Override
	protected List<DALogsDADTO> fillList(ResultSet resultSet) throws PersistenceException {
		DALogsDADTO dto;
		ArrayList<DALogsDADTO> lista = new ArrayList<DALogsDADTO>();
		try {
			while (resultSet.next()) {
				dto = new DALogsDADTO();
				dto.setId(resultSet.getString("ID"));
				dto.setTypeError(resultSet.getInt("TYPE_ERROR"));
				dto.setCreated(resultSet.getDate("CREATED"));
				dto.setDetailError(resultSet.getString("DETAIL_ERROR"));
				dto.setReference(resultSet.getString("REFERENCE"));
				dto.setSrt(resultSet.getString("SRT"));
				lista.add(dto);
			}
			return lista;
		} catch (SQLException e) {
			throw new PersistenceException(PersistenceError.SQL, e);
		}
	}

	public final void insertLogs(final String id, final int typeError, final String detailError, final String reference,
			final String srt) throws PersistenceException {
		executeUpdateStatement(_coreInsert, id, typeError, detailError, reference, srt);
	}

	public final void deleteLogs(final String reference) throws PersistenceException {
		executeUpdateStatement(_coreDelete, reference);
	}
}
