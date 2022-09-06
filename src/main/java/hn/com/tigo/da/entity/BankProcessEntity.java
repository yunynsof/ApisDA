package hn.com.tigo.da.entity;

import hn.com.tigo.da.dto.BankProcessDTO;
import hn.com.tigo.da.dto.BankProcessModel;
import hn.com.tigo.da.dto.BankReProcessDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.util.List;

public class BankProcessEntity extends EntityBase<BankProcessDTO> {

    private String _coreSelect;
    private String _coreSelectByCycle;
    private String _coreSelectByBankCycleAll;
    private String _coreSelectByBankCycleApply;
    private String _coreSelectByBankCycleRevers;
    private String _coreInsert;
    private String _coreInsertReprocess;
    private String _coreUpdate;
    private String _coreUpdateFile;
    private String _coreSelectReprocess;

    public BankProcessEntity(final SessionBase sessionBase) throws PersistenceException {
        super(sessionBase);
    
        _coreSelect = "SELECT ID,REPROCESS, BANK_ID, CYCLE, SEQUENCE, AMOUNT, COUNT_CLIENT, STATUS,(SELECT DESCRIPTION FROM DA_CONFIG_STATUS WHERE CODE=DA_BANK_PROCESS.STATUS) AS STATUS_DESC, CREATED, DATE_INIT, DATE_END, UUID_PENDING, FILE_NAME_DA, FILE_NAME_BANK, UUID_INCLU_EXCLU, USER_, ERROR FROM DA_BANK_PROCESS";
        _coreSelectByCycle = "SELECT ID,REPROCESS, BANK_ID, CYCLE, SEQUENCE, AMOUNT, COUNT_CLIENT, STATUS,(SELECT DESCRIPTION FROM DA_CONFIG_STATUS WHERE CODE=DA_BANK_PROCESS.STATUS) AS STATUS_DESC, CREATED, DATE_INIT, DATE_END, UUID_PENDING, FILE_NAME_DA, FILE_NAME_BANK, UUID_INCLU_EXCLU, USER_, ERROR\n" +
"FROM DA_BANK_PROCESS WHERE CYCLE=? AND BANK_ID=? AND REPROCESS!=1 AND ROWNUM=1";
        _coreSelectByBankCycleAll = "SELECT ID,REPROCESS, BANK_ID, CYCLE, SEQUENCE, AMOUNT, COUNT_CLIENT, STATUS,(SELECT DESCRIPTION FROM DA_CONFIG_STATUS WHERE CODE=DA_BANK_PROCESS.STATUS) AS STATUS_DESC, CREATED, DATE_INIT, DATE_END, UUID_PENDING, FILE_NAME_DA, FILE_NAME_BANK, UUID_INCLU_EXCLU, USER_, ERROR FROM DA_BANK_PROCESS WHERE BANK_ID=? AND CYCLE LIKE '%<LIKE>'";
        _coreSelectByBankCycleApply = "SELECT ID,REPROCESS, BANK_ID, CYCLE, SEQUENCE, AMOUNT, COUNT_CLIENT, STATUS,(SELECT DESCRIPTION FROM DA_CONFIG_STATUS WHERE CODE=DA_BANK_PROCESS.STATUS) AS STATUS_DESC, CREATED, DATE_INIT, DATE_END, UUID_PENDING, FILE_NAME_DA, FILE_NAME_BANK, UUID_INCLU_EXCLU, USER_, ERROR FROM DA_BANK_PROCESS WHERE BANK_ID=? AND STATUS=4 AND CYCLE LIKE '%<LIKE>'";
        _coreSelectByBankCycleRevers = "SELECT ID,REPROCESS, BANK_ID, CYCLE, SEQUENCE, AMOUNT, COUNT_CLIENT, STATUS,(SELECT DESCRIPTION FROM DA_CONFIG_STATUS WHERE CODE=DA_BANK_PROCESS.STATUS) AS STATUS_DESC, CREATED, DATE_INIT, DATE_END, UUID_PENDING, FILE_NAME_DA, FILE_NAME_BANK, UUID_INCLU_EXCLU, USER_, ERROR FROM DA_BANK_PROCESS WHERE BANK_ID=? AND STATUS=6 AND CYCLE LIKE '%<LIKE>'";
        _coreInsert = "INSERT INTO DA_BANK_PROCESS(ID,BANK_ID,CYCLE,USER_) VALUES (?,?,?,?)";
        _coreInsertReprocess = "INSERT INTO DA_BANK_PROCESS(ID,BANK_ID,CYCLE,USER_,REPROCESS) VALUES (?,?,?,?,1)";
        _coreUpdate = "UPDATE DA_BANK_PROCESS SET STATUS=? WHERE ID=?";
        _coreUpdateFile = "UPDATE DA_BANK_PROCESS SET STATUS=?,FILE_NAME_BANK=?,BASE64_BANK=? WHERE ID=?";
        _coreSelectReprocess="SELECT DA_BANK_PROCESS.ID,DA_BANK_PROCESS.REPROCESS, BANK_ID, DA_BANK_PROCESS.CYCLE, SEQUENCE, AMOUNT, COUNT_CLIENT, STATUS,(SELECT DESCRIPTION FROM DA_CONFIG_STATUS WHERE CODE=DA_BANK_PROCESS.STATUS) AS STATUS_DESC, DA_BANK_PROCESS.CREATED, DATE_INIT, DATE_END, UUID_PENDING, FILE_NAME AS FILE_NAME_DA, FILE_NAME_BANK, UUID_INCLU_EXCLU, DA_REPROCESS_FILE.USER_, ERROR\n" +
"FROM DA_REPROCESS_FILE JOIN DA_BANK_PROCESS ON(DA_REPROCESS_FILE.ID=DA_BANK_PROCESS.ID) ORDER BY DA_BANK_PROCESS.CREATED DESC";
    }
    
    @Override
    protected final ArrayList<BankProcessDTO> fillList(ResultSet result) throws PersistenceException {
        BankProcessDTO dto;
        ArrayList<BankProcessDTO> lista = new ArrayList<BankProcessDTO>();
        try {
            while (result.next()) {
                dto = new BankProcessDTO();
                dto.setId(result.getString("ID"));
                dto.setReProcess(result.getString("REPROCESS"));
                dto.setBankId(result.getLong("BANK_ID"));
                dto.setCycle(result.getString("CYCLE"));
                dto.setSequence(result.getString("SEQUENCE"));
                dto.setAmmount(result.getString("AMOUNT"));
                dto.setCountCLient(result.getLong("COUNT_CLIENT"));
                dto.setStatus(result.getLong("STATUS"));
                dto.setStatusDesc(result.getString("STATUS_DESC"));
                dto.setCreatedString(result.getString("CREATED"));
                dto.setDateInitString(result.getString("DATE_INIT"));
                dto.setDateEndString(result.getString("DATE_END"));
                dto.setUuidPending(result.getString("UUID_PENDING"));
                dto.setFileNameDA(result.getString("FILE_NAME_DA"));
                dto.setFileNameBank(result.getString("FILE_NAME_BANK"));
                dto.setUuidIncluExclu(result.getString("UUID_INCLU_EXCLU"));
                dto.setUser(result.getString("USER_"));
                dto.setError(result.getString("ERROR"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SQL, e);
        }
    }
    
    public final List<BankProcessDTO> getBankProcessCycles() throws PersistenceException {
        return executeSelectStatement(_coreSelect);
    }
    
    public void insertBankProcess(final BankProcessModel dto, final String uuid) throws PersistenceException{
        executeInsertStatement(_coreInsert,uuid,dto.getBankId(),dto.getCycle(),dto.getUser());
    }
    
    public void insertBankReProcess(final BankProcessModel dto, final String uuid) throws PersistenceException{
        executeInsertStatement(_coreInsertReprocess,uuid,dto.getBankId(),dto.getCycle(),dto.getUser());
    }
    
    public void updateBankProcessByStatus(final BankReProcessDTO dto) throws PersistenceException{
        if(dto.getStatus()==3){
            executeInsertStatement(_coreUpdateFile,dto.getStatus(),dto.getFileNameBank(),dto.getBase64Bank(),dto.getId());
        }else{
            executeInsertStatement(_coreUpdate,dto.getStatus(),dto.getId());
        }
    }
    
    public final List<BankProcessDTO> getReprocessBank() throws PersistenceException {
        return executeSelectStatement(_coreSelectReprocess);
    }
    
    public final List<BankProcessDTO> getReprocessBankByCycle(final String cycle, final String bankId) throws PersistenceException {
        return executeSelectStatement(_coreSelectByCycle,cycle,bankId);
    }
    
    public final List<BankProcessDTO> getReprocessBankByBankCycle(final String bankId,final String cycle, final String type) throws PersistenceException {
        String sql="";
        if("all".equalsIgnoreCase(type)){
            sql=_coreSelectByBankCycleAll;
        }else if("apply".equalsIgnoreCase(type)){
            sql=_coreSelectByBankCycleApply;
        }else if("revers".equalsIgnoreCase(type)){
            sql=_coreSelectByBankCycleRevers;
        }
        return executeSelectStatement(sql.replace("<LIKE>", cycle),bankId);
    }
    
    public final List<BankProcessDTO> getBankProcessById(final String id) throws PersistenceException {
    	String sql = _coreSelect + " WHERE ID = ? ";
		return executeSelectStatement(sql, id);
    }
    
    public final List<BankProcessDTO> getBankProcessByBankId(final long bankId, final String cycle) throws PersistenceException {
    	String sql = _coreSelect + " WHERE BANK_ID = ? AND CYCLE = ? AND CREATED = (SELECT MAX(CREATED) FROM DA_BANK_PROCESS WHERE BANK_ID = ?) ";
		return executeSelectStatement(sql, bankId, cycle, bankId);
    }
}
