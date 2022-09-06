package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ParametersDTO;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * this class is linked to the table CONFIG_PARAMETERS db BANK
 * Class ParametersEntity.
 * 
 * @author Kerim Fortin
 * @since 03/06/2022
 */
public class ParametersEntity extends EntityBase<ParametersDTO> {
	private String _coreSelect;

	public ParametersEntity(final SessionBase sessionBase)throws PersistenceException {
		super(sessionBase);
		_coreSelect = "SELECT * FROM DA_CONFIG_PARAMETERS WHERE APPLICATION_ID = 101";
	}

	@Override
	protected final ArrayList<ParametersDTO> fillList(ResultSet result) throws PersistenceException {
		ParametersDTO dto;
		ArrayList<ParametersDTO> lista = new ArrayList<ParametersDTO>();
		try {
			while(result.next()){
				dto = new ParametersDTO();
				dto.setIdApplication(result.getLong("APPLICATION_ID"));
				dto.setName(result.getString("NAME"));
				dto.setValue(result.getString("VALUE"));
				dto.setDescription(result.getString("DESCRIPTION"));
				dto.setCreatedDate(result.getDate("CREATED_DATE"));
				lista.add(dto);
			}
			return lista;
		} catch (SQLException e) {
			throw new PersistenceException(PersistenceError.SQL,e);
		}
	}
	
	/*listAllParam method: Used for obtain all params from CONFIG_PARAMETERS table */
	public final HashMap<String,String> listAllParam() throws PersistenceException {
		final HashMap<String,String> param = new HashMap<>();
		final List<ParametersDTO> list = executeSelectStatement(_coreSelect);
		for (ParametersDTO parametersModel : list) {
			param.put(parametersModel.getName(), parametersModel.getValue());
		}
		return param;
	}

}