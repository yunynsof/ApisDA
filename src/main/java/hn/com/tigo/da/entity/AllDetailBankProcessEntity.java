package hn.com.tigo.da.entity;

import com.google.gson.Gson;
import hn.com.tigo.da.dto.AccountBillDTO;
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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AllDetailBankProcessEntity extends EntityBase<AccountBillDTO>{

    private String _coreSelect;
    private String _coreSelectApply;
    private String _coreSelectRevers;
    private ArrayList<String> globalLista=null;

    public AllDetailBankProcessEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        _coreSelect = "SELECT SUBSCRIBER_ID,CREATED,ACCTCODE,NO_CARD,AMOUNT,PAYMENT_SEQ,INVOICE_ID,STATUS,ERROR_BANK FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS=?";
        _coreSelectApply = "SELECT SUBSCRIBER_ID,CREATED,ACCTCODE,NO_CARD,AMOUNT,PAYMENT_SEQ,INVOICE_ID,STATUS,ERROR_BANK FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS=? AND STATUS IN(1,2,-1,-2)";
        _coreSelectRevers = "SELECT SUBSCRIBER_ID,CREATED,ACCTCODE,NO_CARD,AMOUNT,PAYMENT_SEQ,INVOICE_ID,STATUS,ERROR_BANK FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS=? AND STATUS=2";
    }
    
    @Override
    protected final ArrayList<AccountBillDTO> fillList(ResultSet rs) throws PersistenceException {
        globalLista = new ArrayList<>();
        try {
            while (rs.next()) {
                ArrayList<String> lista = new ArrayList<>();
                lista.add(rs.getString("SUBSCRIBER_ID"));
                lista.add(rs.getString("ACCTCODE"));
                try {
                    String noCard;  
                    noCard = EncryptDecrypt.decript(rs.getString("NO_CARD"));
                    lista.add(noCard.substring(noCard.length() - 4));
                    //lista.add(String.format("%" + noCard.length() + "s", new Object[] { noCard.substring(noCard.length() - 4) }).replace(' ', '*'));
                } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException |BadPaddingException | UnsupportedEncodingException e) {
                    lista.add("");
                }
                lista.add(rs.getString("AMOUNT"));
                lista.add(rs.getString("INVOICE_ID"));
                lista.add(rs.getString("PAYMENT_SEQ"));
                lista.add(rs.getString("CREATED"));
                lista.add(rs.getString("STATUS"));
                lista.add(rs.getString("ERROR_BANK"));
                String json = new Gson().toJson(lista);
                globalLista.add(json);
            }
            
        }catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
        ArrayList<AccountBillDTO> dto = null;
        return dto;
    }
    
    public final ArrayList<String> getDetailBankProcess(final String idProcess, final String type) throws PersistenceException {
        if("all".equalsIgnoreCase(type)){
            executeSelectStatement(_coreSelect,idProcess);
        }else if("apply".equalsIgnoreCase(type)){
            executeSelectStatement(_coreSelectApply,idProcess);
        }else if("revers".equalsIgnoreCase(type)){
            executeSelectStatement(_coreSelectRevers,idProcess);
        }
        executeSelectStatement(_coreSelect,idProcess);
        return globalLista;
    }
}
