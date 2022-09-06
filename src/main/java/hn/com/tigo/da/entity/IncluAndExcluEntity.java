
package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.IncluAndExcluDTO;
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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class IncluAndExcluEntity extends EntityBase<IncluAndExcluDTO> {

    private final String _coreSelectIncluAndExcluByAcct;
    private final String _coreSelectIncluAndExcluByPrimary;
    private final String _coreUpdateIncluAndExclu;
    private final String _coreInsertIncluAndExclu;

    public IncluAndExcluEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelectIncluAndExcluByAcct = "SELECT * FROM DA_BANK_INCLU_AND_EXCLU WHERE ANEXO=?";
        _coreSelectIncluAndExcluByPrimary="SELECT DA_BANK_INCLU_AND_EXCLU.ID, NUMPLA, TYPE_TRAN, ANEXO, DA_BANK_INCLU_AND_EXCLU.NO_CARD, CARD_ISSUE_DATE, EXP_DATE, DA_BANK_INCLU_AND_EXCLU.STATUS, DA_BANK_INCLU_AND_EXCLU.CYCLE, DA_BANK_INCLU_AND_EXCLU.CREATED,DA_BANK_INCLU_AND_EXCLU.FILE_NAME FROM DA_BANK_INCLU_AND_EXCLU JOIN DA_CARD_INFO ON(ACCTCODE=ANEXO) WHERE SUBSCRIBER_ID=?";
        _coreUpdateIncluAndExclu = "UPDATE DA_BANK_INCLU_AND_EXCLU SET NUMPLA=?,TYPE_TRAN=?,NO_CARD=?,CARD_ISSUE_DATE=?,EXP_DATE=?,CYCLE=? WHERE ANEXO=?";
       _coreInsertIncluAndExclu = "INSERT INTO DA_BANK_INCLU_AND_EXCLU(ID,NUMPLA,TYPE_TRAN,ANEXO,NO_CARD,CARD_ISSUE_DATE,EXP_DATE,STATUS,CYCLE) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    @Override
    protected List<IncluAndExcluDTO> fillList(ResultSet rs) throws PersistenceException {
        IncluAndExcluDTO dto;
        ArrayList<IncluAndExcluDTO> lista = new ArrayList<IncluAndExcluDTO>();
        dto = new IncluAndExcluDTO();
        try {
            while (rs.next()) {
                dto = new IncluAndExcluDTO();
                dto.setId(rs.getString("ID"));
                dto.setNumpla(rs.getString("NUMPLA"));
                dto.setType_tran(rs.getString("TYPE_TRAN"));
                dto.setAnexo(rs.getString("ANEXO"));             
                try {
                    String noCard;  
                    noCard = EncryptDecrypt.decript(rs.getString("NO_CARD"));
                    dto.setNoCard(String.format("%" + noCard.length() + "s", new Object[] { noCard.substring(noCard.length() - 4) }).replace(' ', '*'));
                } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException |BadPaddingException | UnsupportedEncodingException e) {
                    dto.setNoCard("");
                }
                dto.setCardIsueDate(rs.getString("CARD_ISSUE_DATE"));
                dto.setExpDate(rs.getString("EXP_DATE"));
                dto.setStatus(rs.getLong("STATUS"));
                dto.setCycle(rs.getString("CYCLE"));
                dto.setCreatedString(rs.getString("CREATED"));
                dto.setFileName(rs.getString("FILE_NAME"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }

    }

    public void insertIncluAndExclu(final IncluAndExcluDTO dto) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        executeInsertStatement(_coreInsertIncluAndExclu,UUID.randomUUID().toString(),dto.getNumpla(),dto.getType_tran(),dto.getAnexo(),EncryptDecrypt.encript(dto.getNoCard()),dto.getCardIsueDate(),dto.getExpDate(),dto.getStatus(),dto.getCycle());
    }
    
    public void EditIncluAndExclu(final IncluAndExcluDTO dto) throws PersistenceException {
        executeUpdateStatement(_coreUpdateIncluAndExclu, dto.getNumpla(),dto.getType_tran(),dto.getNoCard(),dto.getCardIsueDate(),dto.getExpDate(),dto.getCycle(),dto.getAnexo());
    }
    
    public final List<IncluAndExcluDTO> getIncluAndExclu(final String param,final boolean byAcctCode) throws PersistenceException {
        String sql=_coreSelectIncluAndExcluByPrimary;
        if(byAcctCode){
            sql=_coreSelectIncluAndExcluByAcct;
        }
        return executeSelectStatement(sql,param);
    }
}