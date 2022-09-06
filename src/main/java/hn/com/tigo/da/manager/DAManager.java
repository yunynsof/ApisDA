package hn.com.tigo.da.manager;

import hn.com.tigo.da.dto.ApproveCyclesDTO;
import hn.com.tigo.da.dto.ApproversBankDTO;
import hn.com.tigo.da.dto.BankProcessDTO;
import hn.com.tigo.da.dto.BankProcessModel;
import hn.com.tigo.da.dto.BankReProcessDTO;
import hn.com.tigo.da.dto.CardInfoDTO;
import hn.com.tigo.da.dto.ConfigBanksDTO;
import hn.com.tigo.da.dto.ConfigCyclesDTO;
import hn.com.tigo.da.dto.ConfigErrorDTO;
import hn.com.tigo.da.dto.ConfigStatusDTO;
import hn.com.tigo.da.dto.ConfigYearsDTO;
import hn.com.tigo.da.dto.DABankProcessDetailPayDTO;
import hn.com.tigo.da.dto.DeleteMpDTO;
import hn.com.tigo.da.dto.DetailReprocessDTO;
import hn.com.tigo.da.dto.DetailsReprocessDTO;
import hn.com.tigo.da.dto.EmailsApproversDTO;
import hn.com.tigo.da.dto.IncluAndExcluDTO;
import hn.com.tigo.da.dto.OutputfileDTO;
import hn.com.tigo.da.entity.CardInfoEntity;
import hn.com.tigo.da.entity.DeleteAccountEntity;
import hn.com.tigo.da.entity.IncluAndExcluEntity;
import hn.com.tigo.da.entity.ParametersEntity;
import hn.com.tigo.da.dto.ProcessDetailPayDTO;
import hn.com.tigo.da.dto.ReprocessFileDTO;
import hn.com.tigo.da.dto.StatsCycleDTO;
import hn.com.tigo.da.dto.TwoLastCycleDTO;
import hn.com.tigo.da.entity.AllDetailBankProcessEntity;
import hn.com.tigo.da.entity.ApproveCyclesEntity;
import hn.com.tigo.da.entity.ApproversBankEntity;
import hn.com.tigo.da.entity.BankProcessEntity;
import hn.com.tigo.da.entity.ConfigBanksEntity;
import hn.com.tigo.da.entity.ConfigCyclesEntity;
import hn.com.tigo.da.entity.ConfigErrorEntity;
import hn.com.tigo.da.entity.ConfigStatusEntity;
import hn.com.tigo.da.entity.ConfigYearsEntity;
import hn.com.tigo.da.entity.DABankProcessDetailPayEntity;
import hn.com.tigo.da.entity.DALogsDAEntity;
import hn.com.tigo.da.entity.DeleteMpEntity;
import hn.com.tigo.da.entity.DetailReprocessEntity;
import hn.com.tigo.da.entity.DetailsReprocessEntity;
import hn.com.tigo.da.entity.EmailsApproversEntity;
import hn.com.tigo.da.entity.LogsDaEntity;
import hn.com.tigo.da.entity.OutputfileEntity;
import hn.com.tigo.da.entity.ProcessDetailPayEntity;
import hn.com.tigo.da.entity.ReprocessFileEntity;
import hn.com.tigo.da.entity.StatsCycleEntity;
import hn.com.tigo.da.entity.TwoLastCycleEntity;
import hn.com.tigo.da.entity.VerifyCardInfoEntity;
import hn.com.tigo.da.swagger.model.CardInfoSwaggerModel;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.DataSource;

public class DAManager extends SessionBase {

    public DAManager(final DataSource dataSource) throws PersistenceException {
        super(dataSource);
    }

    // get all config params from database
    public HashMap<String, String> listAllParam() throws PersistenceException {
        ParametersEntity entity = null;
        try {
            entity = new ParametersEntity(this);
            HashMap<String, String> response = entity.listAllParam();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<CardInfoDTO> getCarInfo(final String type, final String param) throws PersistenceException {
        CardInfoEntity entity = null;
        try {
            entity = new CardInfoEntity(this);
            boolean isAcct = false;
            if ("acct".equalsIgnoreCase(type)) {
                isAcct = true;
            } else if ("primaryIdentity".equalsIgnoreCase(type)) {
                isAcct = false;
            }
            List<CardInfoDTO> response = entity.getCarInfo(param, isAcct);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public CardInfoDTO getCarInfoById(final String id) throws PersistenceException {
        CardInfoEntity entity = null;
        try {
            entity = new CardInfoEntity(this);
            CardInfoDTO response = entity.getCarById(id);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<CardInfoDTO> getCarInfoDetailReport(final String bank, final String cycle) throws PersistenceException {
        CardInfoEntity entity = null;
        try {
            entity = new CardInfoEntity(this);
            List<CardInfoDTO> response = entity.getCarInfoDetailReport(bank, cycle);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void insertBankIncluAndExclu(final IncluAndExcluDTO dto) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        IncluAndExcluEntity entity = null;
        try {
            entity = new IncluAndExcluEntity(this);
            entity.insertIncluAndExclu(dto);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void updateBankIncluAndExclu(final IncluAndExcluDTO dto) throws PersistenceException {
        IncluAndExcluEntity entity = null;
        try {
            entity = new IncluAndExcluEntity(this);
            entity.EditIncluAndExclu(dto);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void insertCardInfo(final CardInfoSwaggerModel dto) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        CardInfoEntity entity = null;
        try {
            entity = new CardInfoEntity(this);
            entity.insertMP(dto);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void updateCardInfo(final CardInfoSwaggerModel dto) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        CardInfoEntity entity = null;
        try {
            entity = new CardInfoEntity(this);
            entity.EditMP(dto);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void deleteCardInfo(final CardInfoDTO dto) throws PersistenceException {
        CardInfoEntity entity = null;
        try {
            entity = new CardInfoEntity(this);
            entity.deleteMP(dto);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void UpdatePrimaryMP(final String primary, final String acctCode) throws PersistenceException {
        CardInfoEntity entity = null;
        try {
            entity = new CardInfoEntity(this);
            entity.UpdatePrimaryMP(primary, acctCode);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void insertBankDeleteStatus(final String id, final String json) throws PersistenceException {
        DeleteAccountEntity entity = null;
        try {
            entity = new DeleteAccountEntity(this);
            entity.insertBankDeleteStatus(id, json);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<IncluAndExcluDTO> getIncluAndExclu(final String type, final String param) throws PersistenceException {
        IncluAndExcluEntity entity = null;
        try {
            entity = new IncluAndExcluEntity(this);
            boolean isAcct = false;
            if ("acct".equalsIgnoreCase(type)) {
                isAcct = true;
            } else if ("primaryIdentity".equalsIgnoreCase(type)) {
                isAcct = false;
            }
            List<IncluAndExcluDTO> response = entity.getIncluAndExclu(param, isAcct);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<ProcessDetailPayDTO> getProcessDetailPay(final String type, final String param) throws PersistenceException {
        ProcessDetailPayEntity entity = null;
        try {
            entity = new ProcessDetailPayEntity(this);
            boolean isAcct = false;
            if ("acct".equalsIgnoreCase(type)) {
                isAcct = true;
            } else if ("primaryIdentity".equalsIgnoreCase(type)) {
                isAcct = false;
            }
            List<ProcessDetailPayDTO> response = entity.getProcessDetailPay(param, isAcct);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<ProcessDetailPayDTO> getProcessDetailPayByProcess(final String type, final String param, final String process) throws PersistenceException {
        ProcessDetailPayEntity entity = null;
        try {
            entity = new ProcessDetailPayEntity(this);
            boolean isAcct = false;
            if ("acct".equalsIgnoreCase(type)) {
                isAcct = true;
            } else if ("primaryIdentity".equalsIgnoreCase(type)) {
                isAcct = false;
            }
            List<ProcessDetailPayDTO> response = entity.getProcessDetailPayByProcess(param, isAcct, process);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<ConfigCyclesDTO> getConfigCycles() throws PersistenceException {
        ConfigCyclesEntity entity = null;
        try {
            entity = new ConfigCyclesEntity(this);
            List<ConfigCyclesDTO> response = entity.getConfigCycles();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<BankProcessDTO> getBankProcessCycles() throws PersistenceException {
        BankProcessEntity entity = null;
        try {
            entity = new BankProcessEntity(this);
            List<BankProcessDTO> response = entity.getBankProcessCycles();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<BankProcessDTO> getBankProcessByBankCycle(final String bank, final String cycle, final String type) throws PersistenceException {
        BankProcessEntity entity = null;
        try {
            entity = new BankProcessEntity(this);
            List<BankProcessDTO> response = entity.getReprocessBankByBankCycle(bank, cycle, type);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<BankProcessDTO> getReprocessBank() throws PersistenceException {
        BankProcessEntity entity = null;
        try {
            entity = new BankProcessEntity(this);
            List<BankProcessDTO> response = entity.getReprocessBank();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<BankProcessDTO> getReprocessBankByCycle(final String cycle, final String idBank) throws PersistenceException {
        BankProcessEntity entity = null;
        try {
            entity = new BankProcessEntity(this);
            List<BankProcessDTO> response = entity.getReprocessBankByCycle(cycle, idBank);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void insertBankProcess(final BankProcessModel dto, final String uuid) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        BankProcessEntity entity = null;
        try {
            entity = new BankProcessEntity(this);
            entity.insertBankProcess(dto, uuid);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void insertBankReProcess(final BankProcessModel dto, final String uuid) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        BankProcessEntity entity = null;
        try {
            entity = new BankProcessEntity(this);
            entity.insertBankReProcess(dto, uuid);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void updateBankProcessByStatus(final BankReProcessDTO dto) throws PersistenceException {
        BankProcessEntity entity = null;
        try {
            entity = new BankProcessEntity(this);
            entity.updateBankProcessByStatus(dto);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void deleteProcessDetail(final String idProcess) throws PersistenceException {
        ProcessDetailPayEntity entity = null;
        try {
            entity = new ProcessDetailPayEntity(this);
            entity.deleteProcessDetail(idProcess);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public OutputfileDTO getOutputsFile(final String id) throws PersistenceException {
        OutputfileEntity entity = null;
        try {
            entity = new OutputfileEntity(this);
            OutputfileDTO response = entity.getOutputsFile(id);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<ConfigBanksDTO> getConfigBanks() throws PersistenceException {
        ConfigBanksEntity entity = null;
        try {
            entity = new ConfigBanksEntity(this);
            List<ConfigBanksDTO> response = entity.getConfigBanks();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<ConfigYearsDTO> getConfigYears() throws PersistenceException {
        ConfigYearsEntity entity = null;
        try {
            entity = new ConfigYearsEntity(this);
            List<ConfigYearsDTO> response = entity.getConfigYears();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<ConfigStatusDTO> getConfigStatus() throws PersistenceException {
        ConfigStatusEntity entity = null;
        try {
            entity = new ConfigStatusEntity(this);
            List<ConfigStatusDTO> response = entity.getConfigStatus();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<ConfigErrorDTO> getConfigError() throws PersistenceException {
        ConfigErrorEntity entity = null;
        try {
            entity = new ConfigErrorEntity(this);
            List<ConfigErrorDTO> response = entity.getConfigError();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public ApproveCyclesDTO getSolicitudAprobacion(String idProcess, int type) throws PersistenceException {
        ApproveCyclesEntity entity = null;
        try {
            entity = new ApproveCyclesEntity(this);
            ApproveCyclesDTO response = entity.getSolicitudAprobacion(idProcess, type);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public ApproversBankDTO existAprobadorCiclo(final String user, final String type) throws PersistenceException {
        ApproversBankEntity entity = null;
        try {
            entity = new ApproversBankEntity(this);
            ApproversBankDTO response = entity.getAprobador(user, type);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void insertAprobacionCiclo(final String cycle, final Long type, final String user, final String comment) throws PersistenceException {
        ApproveCyclesEntity entity = null;
        try {
            entity = new ApproveCyclesEntity(this);
            entity.insertAprobacion(cycle, type, user, comment);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());

        }
    }

    public void updateAprobacionCiclo(final String cycle, final String type, final String user, final String comment) throws PersistenceException {
        ApproveCyclesEntity entity = null;
        try {
            entity = new ApproveCyclesEntity(this);
            entity.upadteAprobacion(cycle, type, user, comment);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public EmailsApproversDTO getCorreosAprobadores() throws PersistenceException {
        EmailsApproversEntity entity = null;
        try {
            entity = new EmailsApproversEntity(this);
            EmailsApproversDTO response = entity.getCorreosAprobadores();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public StatsCycleDTO getStatsCycle(final String id) throws PersistenceException {
        StatsCycleEntity entity = null;
        try {
            entity = new StatsCycleEntity(this);
            StatsCycleDTO response = entity.getStatsCycle(id);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<DeleteMpDTO> getSelectDeleteMp(final String bank, final String cycle, final String error, final String inCycles) throws PersistenceException {
        DeleteMpEntity entity = null;
        try {
            entity = new DeleteMpEntity(this);
            List<DeleteMpDTO> response = entity.getSelectDeleteMp(bank, cycle, error, inCycles);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<DeleteMpDTO> getSelectDeleteDetailMp(final String bank, final String cycle, final String error, final String inCycles, final String account) throws PersistenceException {
        DeleteMpEntity entity = null;
        try {
            entity = new DeleteMpEntity(this);
            List<DeleteMpDTO> response = entity.getSelectDeleteMpDetail(bank, cycle, error, inCycles, account);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void updateDeleteMp(final String bank, final String cycle, final String error) throws PersistenceException {
        DeleteMpEntity entity = null;
        try {
            entity = new DeleteMpEntity(this);
            entity.updateDeleteMp(bank, cycle, error);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<TwoLastCycleDTO> getTwoLastCycle(final String cycle, final String bankId) throws PersistenceException {
        TwoLastCycleEntity entity = null;
        try {
            entity = new TwoLastCycleEntity(this);
            List<TwoLastCycleDTO> response = entity.getTwoLastCycle(cycle, bankId);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<TwoLastCycleDTO> getOneLastCycle(final String cycle, final String bankName, final String bankId) throws PersistenceException {
        TwoLastCycleEntity entity = null;
        try {
            entity = new TwoLastCycleEntity(this);
            List<TwoLastCycleDTO> response = entity.getOneLastCycle(cycle, bankName, bankId);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<TwoLastCycleDTO> getLastCycleBanks() throws PersistenceException {
        TwoLastCycleEntity entity = null;
        try {
            entity = new TwoLastCycleEntity(this);
            List<TwoLastCycleDTO> response = entity.getLastCycleBanks();
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void insertReprocess(final ReprocessFileDTO dto, final String uuid) throws PersistenceException {
        ReprocessFileEntity entity = null;
        try {
            entity = new ReprocessFileEntity(this);
            entity.insertReprocess(dto, uuid);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public CardInfoDTO existPaymemtMethod(final String acctCode) throws PersistenceException {
        VerifyCardInfoEntity entity = null;
        try {
            entity = new VerifyCardInfoEntity(this);
            CardInfoDTO response = entity.existPaymemtMethod(acctCode);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void insertBankReProcessDetail(final DetailReprocessDTO dto, final String uuid) throws PersistenceException {
        DetailReprocessEntity entity = null;
        try {
            entity = new DetailReprocessEntity(this);
            entity.insertBankReProcessDetail(dto, uuid);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void getBankReProcessDetail(final String uuid) throws PersistenceException {
        DetailReprocessEntity entity = null;
        try {
            entity = new DetailReprocessEntity(this);
            entity.getBankReProcessDetail(uuid);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public ArrayList<String> AllDetailBankProcessEntity(final String idProcess, final String type) throws PersistenceException {
        AllDetailBankProcessEntity entity = null;
        try {
            entity = new AllDetailBankProcessEntity(this);
            ArrayList<String> response = entity.getDetailBankProcess(idProcess, type);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public ArrayList<String> getLogErrorByType(final String typeError, final String dateIn, final String dateEnd) throws PersistenceException {
        LogsDaEntity entity = null;
        try {
            entity = new LogsDaEntity(this);
            ArrayList<String> response = entity.getLogErrorByType(typeError, dateIn, dateEnd);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public List<DetailsReprocessDTO> getDetailsReprocessByIdProcess(final String idReprocess) throws PersistenceException {
        DetailsReprocessEntity entity = null;
        try {
            entity = new DetailsReprocessEntity(this);
            List<DetailsReprocessDTO> response = entity.getDetailsReprocess(idReprocess);
            return response;
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    public List<DABankProcessDetailPayDTO> selectApplyPayError(final String idBankProcess, final long status,
			final String errorBank) throws PersistenceException {
		DABankProcessDetailPayEntity entity = null;
		try {
			entity = new DABankProcessDetailPayEntity(this);
			return entity.selectApplyPayError(idBankProcess, status, errorBank);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
    
    public void insertLogs(final String id, final int typeError, final String detailError, final String reference,
			final String srt) throws PersistenceException {
		DALogsDAEntity entity = null;
		try {
			entity = new DALogsDAEntity(this);
			entity.insertLogs(id, typeError, detailError, reference, srt);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());

		}
	}
    
    public void updateBankProcDetailPayByOne(final String errorBank, final long newStatus, final String txIdPay,
			final String acctCode, final long oldStatus, final long groupPayment, final String invoiceId,
			final String paymentSeq, final long retries, final String bankProcessId) throws PersistenceException {
		DABankProcessDetailPayEntity entity = null;
		try {
			entity = new DABankProcessDetailPayEntity(this);
			entity.updateBankProcDetailPayByOne(errorBank, newStatus, txIdPay, acctCode, oldStatus, groupPayment,
					invoiceId, paymentSeq, retries, bankProcessId);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());

		}
	}
    
    public List<BankProcessDTO> getBankProcessById(final String id) throws PersistenceException {
    	BankProcessEntity entity = null;
		try {
			entity = new BankProcessEntity(this);
			return entity.getBankProcessById(id);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
    
    public List<BankProcessDTO> getBankProcessByBankId(final long bankId, final String cycle) throws PersistenceException {
    	BankProcessEntity entity = null;
		try {
			entity = new BankProcessEntity(this);
			return entity.getBankProcessByBankId(bankId, cycle);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
}
