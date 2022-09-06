
package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.CardInfoDTO;
import hn.com.tigo.da.swagger.model.CardInfoSwaggerModel;
import hn.com.tigo.da.utils.EncryptDecrypt;
import hn.com.tigo.da.utils.HashMapObjects;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CardInfoEntity extends EntityBase<CardInfoDTO> {
    private boolean getOriginalCard=false;
    private final String _coreSelectCardInfoByAcct;
    private final String _coreSelectCardInfoByCycleBank;
    private final String _coreSelectCardInfoBySubs;
    private final String _coreSelectCardInfoById;
    private final String _coreInsertCardInfoById;
    private final String _coreDeleteCardInfoById;
    private final String _coreUpdateDinCardInfoById;
    private final String _coreUpdatePrimaryCardInfoByAcct;

    public CardInfoEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
        getOriginalCard=false;
        _coreSelectCardInfoByAcct = "SELECT * FROM DA_CARD_INFO WHERE ACCTCODE=?";
        _coreSelectCardInfoByCycleBank = "SELECT * FROM DA_CARD_INFO WHERE PROCESSOR_BANK=? AND CYCLE=?";
        _coreSelectCardInfoBySubs = "SELECT * FROM DA_CARD_INFO WHERE SUBSCRIBER_ID=?";
        _coreSelectCardInfoById = "SELECT * FROM DA_CARD_INFO WHERE ID=?";
        _coreInsertCardInfoById="INSERT INTO DA_CARD_INFO(ID,NAME,NO_CARD,TYPE_CARD,MODALITY_CARD,YEAR,MONTH,ISSUING_BANK,PROCESSOR_BANK,GROUP_PAYMENT,CYCLE,STATUS,ACCTCODE,SUBSCRIBER_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        _coreDeleteCardInfoById="DELETE FROM DA_CARD_INFO WHERE ID=?";
        _coreUpdateDinCardInfoById="UPDATE DA_CARD_INFO SET ";
        _coreUpdatePrimaryCardInfoByAcct="UPDATE DA_CARD_INFO SET SUBSCRIBER_ID=? WHERE ACCTCODE=?";
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
                dto.setName(rs.getString("NAME"));
                if(getOriginalCard){
                    try {
                        String noCard;  
                        noCard = EncryptDecrypt.decript(rs.getString("NO_CARD"));
                        dto.setNoCard(noCard.substring(noCard.length() - 4));
                    } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException |BadPaddingException | UnsupportedEncodingException e) {
                        dto.setNoCard("");
                    }
                }else{
                    dto.setNoCard(rs.getString("NO_CARD"));
                }
                
                dto.setTypeCard(rs.getString("TYPE_CARD"));
                dto.setModalityCard(rs.getString("MODALITY_CARD"));
                dto.setYear(rs.getString("YEAR"));
                dto.setMonth(rs.getString("MONTH"));
                dto.setIssuingBank(rs.getString("ISSUING_BANK"));
                dto.setProcessorBank(rs.getString("PROCESSOR_BANK"));
                dto.setGroupPayment(rs.getString("GROUP_PAYMENT"));
                dto.setCycle(rs.getString("CYCLE"));
                dto.setCreated(rs.getDate("CREATED"));
                dto.setStatus(rs.getString("STATUS"));
                dto.setAcctCode(rs.getString("ACCTCODE"));
                dto.setSubscriberId(rs.getString("SUBSCRIBER_ID"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }

    }

    public final List<CardInfoDTO> getCarInfo(final String param,final boolean byAcctCode) throws PersistenceException {
        this.getOriginalCard=false;
        String sql=_coreSelectCardInfoBySubs;
        if(byAcctCode){
            sql=_coreSelectCardInfoByAcct;
        }
        return executeSelectStatement(sql,param);
    }
    
    public final CardInfoDTO getCarById(final String id) throws PersistenceException {
        this.getOriginalCard=false;
        List<CardInfoDTO> tempDTO = executeSelectStatement(_coreSelectCardInfoById, id);
        return tempDTO.isEmpty() ? null : tempDTO.iterator().next();
    }
    
    public final List<CardInfoDTO> getCarInfoDetailReport(final String bank, final String cycle) throws PersistenceException {
        this.getOriginalCard=true;
        return executeSelectStatement(_coreSelectCardInfoByCycleBank, bank,cycle);
    }
    
    public void insertMP(final CardInfoSwaggerModel dto) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        executeInsertStatement(_coreInsertCardInfoById,UUID.randomUUID().toString(),dto.getName().toUpperCase(),EncryptDecrypt.encript(dto.getNoCard()),dto.getTypeCard(),dto.getModalityCard(),dto.getYear(),dto.getMonth(),dto.getIssuingBank(),dto.getProcessorBank(),dto.getGroupPayment(),dto.getCycle(),dto.getStatus(),dto.getAcctCode(),dto.getSubscriberId());
    }
    
    public void EditMP(final CardInfoSwaggerModel dto) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String set="";
        if(dto.getName()!= null) {
        	 dto.setName(dto.getName().toUpperCase());
        }       
        final HashMap<String, String> map = HashMapObjects.getCardInfoMap();
        Class _class = Class.forName("hn.com.tigo.da.swagger.model.CardInfoSwaggerModel");
        Field properties[] = _class.getFields();
        for (int i = 0; i < properties.length; i++) {
            Field field = properties[i];
            Field variableString = null;
            String text = null;
            try {
                variableString = _class.getField(field.getName());
                Type tipo = field.getGenericType();
                if("long".equalsIgnoreCase(tipo.getTypeName())){
                    long longValue = (long) variableString.get(dto);
                    text=String.valueOf(longValue);
                }else{
                    text = (String) variableString.get(dto);
                }
                if(text!=null){
                    if(map.get(field.getName())!=null){
                        if("noCard".equalsIgnoreCase(field.getName())){
                           set+=map.get(field.getName())+"='"+EncryptDecrypt.encript(text)+"', ";
                        }else{
                            set+=map.get(field.getName())+"='"+text+"', ";
                        }
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                    e.printStackTrace();
            } 
        }
        String sql=_coreUpdateDinCardInfoById+set.substring(0, set.length()-2)+" WHERE id=?";
        executeUpdateStatement(sql,dto.getId());
    }
    
    public void deleteMP(final CardInfoDTO dto) throws PersistenceException {
        executeUpdateStatement(_coreDeleteCardInfoById,dto.getId());
    }
    
    public void UpdatePrimaryMP(final String primary,final String acctCode) throws PersistenceException {
        executeUpdateStatement(_coreUpdatePrimaryCardInfoByAcct,primary,acctCode);
    }
}