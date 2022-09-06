package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.DeleteMpDTO;
import hn.com.tigo.da.utils.EncryptDecrypt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DeleteMpEntity extends EntityBase<DeleteMpDTO> {

    private String _coreSelect;
    private String _coreSelectDetail;
    private String _coreUpdate;
    private String cycles;

    public DeleteMpEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT * FROM (SELECT COUNT(*) AS COUNT_,DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE,DA_BANK_PROCESS_DETAIL_PAY.SUBSCRIBER_ID,DA_BANK_PROCESS_DETAIL_PAY.NO_CARD,DA_BANK_PROCESS_DETAIL_PAY.EXPIRATION_DATE,'' AS CYCLE,'' AS AMOUNT,'' AS CREATED,'' AS ERROR_BANK\n" +
"FROM DA_BANK_PROCESS_DETAIL_PAY JOIN DA_BANK_PROCESS ON(DA_BANK_PROCESS.ID=ID_BANK_PROCESS) JOIN DA_CARD_INFO ON(DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE=DA_CARD_INFO.ACCTCODE) \n" +
"WHERE DA_BANK_PROCESS_DETAIL_PAY.ID_BANK_PROCESS IN(<REPLACE>) \n" +
"AND DA_CARD_INFO.STATUS=1 AND DA_BANK_PROCESS_DETAIL_PAY.STATUS=-1 AND BANK_ID=? \n" +
"AND DA_CARD_INFO.CYCLE=? AND DA_BANK_PROCESS_DETAIL_PAY.ERROR_BANK=? \n" +
"GROUP BY DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE,DA_BANK_PROCESS_DETAIL_PAY.SUBSCRIBER_ID,DA_BANK_PROCESS_DETAIL_PAY.NO_CARD,DA_BANK_PROCESS_DETAIL_PAY.EXPIRATION_DATE) \n" +
"WHERE COUNT_>=2";
        
        _coreSelectDetail = "SELECT DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE,DA_BANK_PROCESS_DETAIL_PAY.SUBSCRIBER_ID,DA_BANK_PROCESS_DETAIL_PAY.NO_CARD,DA_BANK_PROCESS_DETAIL_PAY.EXPIRATION_DATE,DA_BANK_PROCESS.CYCLE,DA_BANK_PROCESS_DETAIL_PAY.AMOUNT,DA_BANK_PROCESS_DETAIL_PAY.CREATED,DA_BANK_PROCESS_DETAIL_PAY.ERROR_BANK FROM DA_BANK_PROCESS_DETAIL_PAY JOIN DA_BANK_PROCESS ON(DA_BANK_PROCESS.ID=ID_BANK_PROCESS) JOIN DA_CARD_INFO ON(DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE=DA_CARD_INFO.ACCTCODE) \n" +
"WHERE DA_BANK_PROCESS_DETAIL_PAY.ID_BANK_PROCESS IN(<REPLACE>) AND DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE=?\n" +
"AND DA_CARD_INFO.STATUS=1 AND DA_BANK_PROCESS_DETAIL_PAY.STATUS=-1 AND BANK_ID=? \n" +
"AND DA_CARD_INFO.CYCLE=? AND DA_BANK_PROCESS_DETAIL_PAY.ERROR_BANK=?";
        
        _coreUpdate = "UPDATE DA_CARD_INFO SET STATUS=0 WHERE ACCTCODE IN(SELECT ACCTCODE FROM(SELECT COUNT(*) AS COUNT_, DA_CARD_INFO.ACCTCODE\n" +
"FROM DA_BANK_PROCESS_DETAIL_PAY JOIN DA_BANK_PROCESS ON(DA_BANK_PROCESS.ID=ID_BANK_PROCESS) JOIN DA_CARD_INFO ON(DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE=DA_CARD_INFO.ACCTCODE)\n" +
"WHERE DA_CARD_INFO.STATUS=1 AND DA_BANK_PROCESS_DETAIL_PAY.STATUS=-1 AND BANK_ID=? AND \n" +
"DA_CARD_INFO.CYCLE=? AND SUBSTR(DA_BANK_PROCESS.CYCLE,7)=? AND \n" +
"DA_BANK_PROCESS_DETAIL_PAY.ERROR_BANK=? GROUP BY DA_CARD_INFO.ACCTCODE) WHERE COUNT_>=2)";
    }
    
    @Override
    protected final ArrayList<DeleteMpDTO> fillList(ResultSet result) throws PersistenceException {
        DeleteMpDTO dto;
        ArrayList<DeleteMpDTO> lista = new ArrayList<DeleteMpDTO>();
        try {
            while (result.next()) {
                dto = new DeleteMpDTO();
                dto.setAcctCode(result.getString("ACCTCODE"));
                dto.setSubscriber(result.getString("SUBSCRIBER_ID"));
                //dto.setNoCard(result.getString("NO_CARD"));
                try {
                    String noCard;  
                    noCard = EncryptDecrypt.decript(result.getString("NO_CARD"));
                    dto.setNoCard(String.format("%" + noCard.length() + "s", new Object[] { noCard.substring(noCard.length() - 4) }).replace(' ', '*'));
                } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException |BadPaddingException | UnsupportedEncodingException e) {
                    dto.setNoCard("");
                }
                dto.setExpDate(result.getString("EXPIRATION_DATE"));
                dto.setCycle(result.getString("CYCLE"));
                //dto.setMessage(result.getString("DESC_"));
                dto.setAmmount(result.getString("AMOUNT"));
                dto.setTransactionDate(result.getString("CREATED"));
                dto.setError(result.getString("ERROR_BANK"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final List<DeleteMpDTO> getSelectDeleteMp(final String bank, final String cycle, final String error, final String inIdCycle) throws PersistenceException {
        return executeSelectStatement(_coreSelect.replace("<REPLACE>", inIdCycle),bank,cycle,error);
    }
    
    public final List<DeleteMpDTO> getSelectDeleteMpDetail(final String bank, final String cycle, final String error, final String inIdCycle, final String acctcode) throws PersistenceException {
        return executeSelectStatement(_coreSelectDetail.replace("<REPLACE>", inIdCycle),acctcode,bank,cycle,error);
    }
    
    public void updateDeleteMp(final String bank, final String cycle, final String error) throws PersistenceException {
        executeUpdateStatement(_coreUpdate, bank,cycle,cycle,error);
    }
}
