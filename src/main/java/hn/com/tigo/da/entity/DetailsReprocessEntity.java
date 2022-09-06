/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.CardInfoDTO;
import hn.com.tigo.da.dto.DetailsReprocessDTO;
import hn.com.tigo.da.utils.EncryptDecrypt;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Laurent G. Caceres
 */
public class DetailsReprocessEntity extends EntityBase<DetailsReprocessDTO> {

    private final String _coreSelectDetailsReprocessByIdReprocess;

    public DetailsReprocessEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelectDetailsReprocessByIdReprocess = "SELECT DA_DETAIL_REPROCESS.CLIENT, DA_DETAIL_REPROCESS.ACCOUNT, DA_DETAIL_REPROCESS.SUBSCRIBER, DA_DETAIL_REPROCESS.CYCLE, DA_DETAIL_REPROCESS.CREATED\n"
                + "FROM DA_DETAIL_REPROCESS JOIN DA_REPROCESS_FILE ON DA_REPROCESS_FILE.ID = DA_DETAIL_REPROCESS.ID_REPROCESS \n"
                + "WHERE ID_REPROCESS = ?";

    }

    @Override
    protected List<DetailsReprocessDTO> fillList(ResultSet rs) throws PersistenceException {
        DetailsReprocessDTO dto;
        ArrayList<DetailsReprocessDTO> lista = new ArrayList<DetailsReprocessDTO>();
        dto = new DetailsReprocessDTO();
        try {
            while (rs.next()) {
                dto = new DetailsReprocessDTO();
                dto.setClientName(rs.getString("CLIENT"));
                dto.setAccount(rs.getString("ACCOUNT"));
                dto.setSubscriber(rs.getString("SUBSCRIBER"));
                dto.setCycle(rs.getString("CYCLE"));
                dto.setCreated(rs.getString("CREATED"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }

    public final List<DetailsReprocessDTO> getDetailsReprocess(final String idReprocess) throws PersistenceException {
        String sql = _coreSelectDetailsReprocessByIdReprocess;
        return executeSelectStatement(sql, idReprocess);
    }

}
