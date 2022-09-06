package hn.com.tigo.da.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hn.com.tigo.da.dto.DABankProcessDetailPayDTO;
import hn.com.tigo.josm.persistence.core.EntityBase;
import hn.com.tigo.josm.persistence.core.SessionBase;
import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;

public class DABankProcessDetailPayEntity extends EntityBase<DABankProcessDetailPayDTO> {

	private String _coreInsert;
	private String _coreUpdate;
	private String _coreSelect;
	private String _coreUpdRevert;
	private String _coreDelete;

	public DABankProcessDetailPayEntity(final SessionBase sessionBase) throws PersistenceException {
		super(sessionBase);
		_coreInsert = "INSERT INTO DA_BANK_PROCESS_DETAIL_PAY (ID, ID_BANK_PROCESS, STATUS, ACCTCODE, SUBSCRIBER_ID, NO_CARD, AMOUNT, COMMENT_PAY, INVOICE_ID, GROUP_PAYMENT, TAX_AMOUNT, EXPIRATION_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";

		_coreUpdate = "UPDATE DA_BANK_PROCESS_DETAIL_PAY SET ERROR_BANK = ?, STATUS = ?, PAYMENT_ID = ?, PAYMENT_SEQ = ?, RETRIES = ?, CREATED = SYSDATE  WHERE ACCTCODE = ? AND STATUS = ? AND GROUP_PAYMENT = ? ";

		_coreSelect = "SELECT ID, ID_BANK_PROCESS, STATUS, ACCTCODE, SUBSCRIBER_ID, NO_CARD, AMOUNT, COMMENT_PAY, PAYMENT_ID, CREATED, GROUP_PAYMENT, ERROR_BANK, INVOICE_ID, TAX_AMOUNT, EXPIRATION_DATE, PAYMENT_SEQ, RETRIES FROM DA_BANK_PROCESS_DETAIL_PAY ";

		_coreUpdRevert = "UPDATE DA_BANK_PROCESS_DETAIL_PAY SET STATUS = ?, PAYMENT_ID = ?  WHERE ACCTCODE = ? AND STATUS = ? AND GROUP_PAYMENT = ? AND INVOICE_ID = ? ";

		_coreDelete = "DELETE FROM DA_BANK_PROCESS_DETAIL_PAY WHERE ID_BANK_PROCESS = ? ";
	}

	@Override
	protected List<DABankProcessDetailPayDTO> fillList(ResultSet resultSet) throws PersistenceException {
		DABankProcessDetailPayDTO dto;
		ArrayList<DABankProcessDetailPayDTO> lista = new ArrayList<DABankProcessDetailPayDTO>();
		try {
			while (resultSet.next()) {
				dto = new DABankProcessDetailPayDTO();
				dto.setId(resultSet.getString("ID"));
				dto.setIdBankProcess(resultSet.getString("ID_BANK_PROCESS"));
				dto.setStatus(resultSet.getLong("STATUS"));
				dto.setAcctCode(resultSet.getString("ACCTCODE"));
				dto.setSubscriberId(resultSet.getString("SUBSCRIBER_ID"));
				dto.setNoCard(resultSet.getString("NO_CARD"));
				dto.setAmount(resultSet.getString("AMOUNT"));
				dto.setCommentPay(resultSet.getString("COMMENT_PAY"));
				dto.setPaymentId(resultSet.getString("PAYMENT_ID"));
				dto.setCreated(resultSet.getDate("CREATED"));
				dto.setGroupPayment(resultSet.getLong("GROUP_PAYMENT"));
				dto.setErrorBank(resultSet.getString("ERROR_BANK"));
				dto.setInvoiceId(resultSet.getString("INVOICE_ID"));
				dto.setAmountTax(resultSet.getString("TAX_AMOUNT"));
				dto.setExpirationDate(resultSet.getString("EXPIRATION_DATE"));
				dto.setPaymentSeq(resultSet.getString("PAYMENT_SEQ"));
				dto.setRetries(resultSet.getLong("RETRIES"));
				lista.add(dto);
			}
			return lista;
		} catch (SQLException e) {
			throw new PersistenceException(PersistenceError.SQL, e);
		}
	}

	public final void insertBankProcessDetailPay(final String id, final String idBankProcess, final long status,
			final String acctcode, final String subscriberId, final String noCard, final String amount,
			final String commentPay, final String invoiceId, final long groupPayment, final String amountTax,
			final String expirationDate) throws PersistenceException {
		executeUpdateStatement(_coreInsert, id, idBankProcess, status, acctcode, subscriberId, noCard, amount,
				commentPay, invoiceId, groupPayment, amountTax, expirationDate);
	}

	public final void updateBankProcDetailPay(final String errorBank, final long newStatus, final String txIdPay,
			final String acctCode, final long oldStatus, final long groupPayment, final long retries) throws PersistenceException {
		executeUpdateStatement(_coreUpdate, errorBank, newStatus, txIdPay, "", retries, acctCode, oldStatus, groupPayment);
	}

	public final void updateBPDPByOne(final long newStatus, final String txIdPay, final String acctCode,
			final long oldStatus, final long groupPayment, final String invoiceId) throws PersistenceException {
		executeUpdateStatement(_coreUpdRevert, newStatus, txIdPay, acctCode, oldStatus, groupPayment, invoiceId);
	}

	public final void updateBankProcDetailPayByOne(final String errorBank, final long newStatus, final String txIdPay,
			final String acctCode, final long oldStatus, final long groupPayment, final String invoiceId,
			final String paymentSeq, final long retries, final String bankProcessId) throws PersistenceException {
		String sql = _coreUpdate + " AND INVOICE_ID = ? AND ID_BANK_PROCESS = ? ";
		executeUpdateStatement(sql, errorBank, newStatus, txIdPay, paymentSeq, retries, acctCode, oldStatus, groupPayment,
				invoiceId, bankProcessId);
	}

	public final List<DABankProcessDetailPayDTO> selectBankProcessDetailPay(final String acctCode, final long status,
			final String idBankProcess) throws PersistenceException {
		String sql = _coreSelect + " WHERE ACCTCODE = ? AND STATUS = ? AND ID_BANK_PROCESS = ? ";
		return executeSelectStatement(sql, acctCode, status, idBankProcess);
	}

	public final List<DABankProcessDetailPayDTO> selectBankProcessRevert(final String idBankProcess, final long status)
			throws PersistenceException {
		String sql = _coreSelect + " WHERE ID_BANK_PROCESS = ? AND STATUS = ? ";
		return executeSelectStatement(sql, idBankProcess, status);
	}

	public final void deleteBPPayDetail(final String reference) throws PersistenceException {
		executeUpdateStatement(_coreDelete, reference);
	}

	public final List<DABankProcessDetailPayDTO> selectApplyPayError(final String idBankProcess, final long status,
			final String errorBank) throws PersistenceException {
		String sql = _coreSelect + " WHERE ID_BANK_PROCESS = ? AND STATUS = ? AND ERROR_BANK = ? ";
		return executeSelectStatement(sql, idBankProcess, status, errorBank);
	}
}
