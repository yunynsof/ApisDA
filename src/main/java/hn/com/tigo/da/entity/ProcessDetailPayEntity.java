
package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.ProcessDetailPayDTO;
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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ProcessDetailPayEntity extends EntityBase<ProcessDetailPayDTO> {

    private final String _coreSelectDetailByAcctCode;
    private final String _coreSelectDetailByPrimary;
    private final String _coreDeleteDetail;
	private final String _coreSelect;

    public ProcessDetailPayEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelectDetailByAcctCode = "SELECT CYCLE,DA_CONFIG_BANK.NAME AS NAME_BANK,ERROR_BANK,DA_CONFIG_BANK.BANK_ID,(SELECT DESCRIPTION FROM DA_CONFIG_ERROR WHERE BANK_ID=DA_CONFIG_BANK.BANK_ID AND BANK_CODE=ERROR_BANK) AS ERROR_BANK_DESC,DA_BANK_PROCESS_DETAIL_PAY.ID, DA_BANK_PROCESS_DETAIL_PAY.ID_BANK_PROCESS, DA_BANK_PROCESS_DETAIL_PAY.STATUS, DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE, DA_BANK_PROCESS_DETAIL_PAY.SUBSCRIBER_ID, DA_BANK_PROCESS_DETAIL_PAY.NO_CARD, DA_BANK_PROCESS_DETAIL_PAY.AMOUNT, DA_BANK_PROCESS_DETAIL_PAY.COMMENT_PAY, DA_BANK_PROCESS_DETAIL_PAY.PAYMENT_ID, DA_BANK_PROCESS_DETAIL_PAY.CREATED, DA_BANK_PROCESS_DETAIL_PAY.GROUP_PAYMENT, FILE_NAME_BANK\n" +
"FROM DA_BANK_PROCESS_DETAIL_PAY JOIN DA_BANK_PROCESS ON(ID_BANK_PROCESS=DA_BANK_PROCESS.ID) JOIN DA_CONFIG_BANK ON(DA_BANK_PROCESS.BANK_ID=DA_CONFIG_BANK.BANK_ID) WHERE ACCTCODE=?";
        _coreSelectDetailByPrimary="SELECT CYCLE,DA_CONFIG_BANK.NAME AS NAME_BANK,ERROR_BANK,DA_CONFIG_BANK.BANK_ID,(SELECT DESCRIPTION FROM DA_CONFIG_ERROR WHERE BANK_ID=DA_CONFIG_BANK.BANK_ID AND BANK_CODE=ERROR_BANK) AS ERROR_BANK_DESC,DA_BANK_PROCESS_DETAIL_PAY.ID, DA_BANK_PROCESS_DETAIL_PAY.ID_BANK_PROCESS, DA_BANK_PROCESS_DETAIL_PAY.STATUS, DA_BANK_PROCESS_DETAIL_PAY.ACCTCODE, DA_BANK_PROCESS_DETAIL_PAY.SUBSCRIBER_ID, DA_BANK_PROCESS_DETAIL_PAY.NO_CARD, DA_BANK_PROCESS_DETAIL_PAY.AMOUNT, DA_BANK_PROCESS_DETAIL_PAY.COMMENT_PAY, DA_BANK_PROCESS_DETAIL_PAY.PAYMENT_ID, DA_BANK_PROCESS_DETAIL_PAY.CREATED, DA_BANK_PROCESS_DETAIL_PAY.GROUP_PAYMENT, FILE_NAME_BANK\n" +
"FROM DA_BANK_PROCESS_DETAIL_PAY JOIN DA_BANK_PROCESS ON(ID_BANK_PROCESS=DA_BANK_PROCESS.ID) JOIN DA_CONFIG_BANK ON(DA_BANK_PROCESS.BANK_ID=DA_CONFIG_BANK.BANK_ID) WHERE SUBSCRIBER_ID=?";
        _coreDeleteDetail="DELETE FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS=?";
        _coreSelect = "SELECT ID, ID_BANK_PROCESS, STATUS, ACCTCODE, SUBSCRIBER_ID, NO_CARD, AMOUNT, COMMENT_PAY, PAYMENT_ID, CREATED, GROUP_PAYMENT, ERROR_BANK, INVOICE_ID, TAX_AMOUNT, EXPIRATION_DATE, PAYMENT_SEQ, RETRIES FROM DA_BANK_PROCESS_DETAIL_PAY ";
    }

    @Override
    protected List<ProcessDetailPayDTO> fillList(ResultSet rs) throws PersistenceException {
        ProcessDetailPayDTO dto;
        ArrayList<ProcessDetailPayDTO> lista = new ArrayList<ProcessDetailPayDTO>();
        dto = new ProcessDetailPayDTO();
        try {
            while (rs.next()) {
                dto = new ProcessDetailPayDTO();
                dto.setId(rs.getString("ID"));
                dto.setIdBankProcess(rs.getString("ID_BANK_PROCESS"));
                dto.setStatus(rs.getInt("STATUS"));
                dto.setAcctCode(rs.getString("ACCTCODE"));
                dto.setSubscriberId(rs.getString("SUBSCRIBER_ID"));
                try {
                    String noCard;  
                    noCard = EncryptDecrypt.decript(rs.getString("NO_CARD"));
                    dto.setNoCard(String.format("%" + noCard.length() + "s", new Object[] { noCard.substring(noCard.length() - 4) }).replace(' ', '*'));
                } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException |BadPaddingException | UnsupportedEncodingException e) {
                    dto.setNoCard("");
                }
                dto.setAmmount(rs.getString("AMOUNT"));
                dto.setCommentPay(rs.getString("COMMENT_PAY"));
                dto.setPaymentId(rs.getString("PAYMENT_ID"));
                dto.setCreatedString(rs.getString("CREATED"));
                dto.setGroupPayment(rs.getString("GROUP_PAYMENT"));
                dto.setFileName(rs.getString("FILE_NAME_BANK"));
                dto.setFileName(rs.getString("FILE_NAME_BANK"));
                dto.setCycle(rs.getString("CYCLE"));
                dto.setBank(rs.getString("NAME_BANK"));
                dto.setBankId(rs.getString("BANK_ID"));
                dto.setErrorBank(rs.getString("ERROR_BANK_DESC"));
                dto.setCodeErrorBank(rs.getString("ERROR_BANK"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }

    }
    
    public final List<ProcessDetailPayDTO> getProcessDetailPay(final String param,final boolean byAcctCode) throws PersistenceException {
        String sql=_coreSelectDetailByPrimary;
        if(byAcctCode){
            sql=_coreSelectDetailByAcctCode;
        }
        return executeSelectStatement(sql,param);
    }
    
    public final List<ProcessDetailPayDTO> getProcessDetailPayByProcess(final String param,final boolean byAcctCode,final String process) throws PersistenceException {
        String sql=_coreSelectDetailByPrimary;
        if(byAcctCode){
            sql=_coreSelectDetailByAcctCode;
        }
        return executeSelectStatement(sql+" AND ID_BANK_PROCESS=?",param,process);
    }
    
    public void deleteProcessDetail(final String idProcess) throws PersistenceException{
        executeDeleteStatement(_coreDeleteDetail, idProcess);
    }
    
    public final List<ProcessDetailPayDTO> selectApplyPayError(final String idBankProcess, final long status,
			final String errorBank) throws PersistenceException {
		String sql = _coreSelect + " WHERE ID_BANK_PROCESS = ? AND STATUS = ? AND ERROR_BANK = ? ";
		return executeSelectStatement(sql, idBankProcess, status, errorBank);
	}
}