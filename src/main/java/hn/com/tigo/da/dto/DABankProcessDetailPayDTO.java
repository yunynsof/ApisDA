package hn.com.tigo.da.dto;

import java.util.Date;


public class DABankProcessDetailPayDTO extends DTO {

	private String id;
	private String idBankProcess;
	private long status;
	private String acctCode;
	private String subscriberId;
	private String noCard;
	private String amount;
	private String commentPay;
	private String paymentId;
	private Date created;
	private long groupPayment;
	private String errorBank;
	private String invoiceId;
	private String amountTax;
	private String expirationDate;
	private String paymentSeq;
	private long retries;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdBankProcess() {
		return idBankProcess;
	}

	public void setIdBankProcess(String idBankProcess) {
		this.idBankProcess = idBankProcess;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getAcctCode() {
		return acctCode;
	}

	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getNoCard() {
		return noCard;
	}

	public void setNoCard(String noCard) {
		this.noCard = noCard;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCommentPay() {
		return commentPay;
	}

	public void setCommentPay(String commentPay) {
		this.commentPay = commentPay;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getGroupPayment() {
		return groupPayment;
	}

	public void setGroupPayment(long groupPayment) {
		this.groupPayment = groupPayment;
	}

	public String getErrorBank() {
		return errorBank;
	}

	public void setErrorBank(String errorBank) {
		this.errorBank = errorBank;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getAmountTax() {
		return amountTax;
	}

	public void setAmountTax(String amountTax) {
		this.amountTax = amountTax;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPaymentSeq() {
		return paymentSeq;
	}

	public void setPaymentSeq(String paymentSeq) {
		this.paymentSeq = paymentSeq;
	}

	public long getRetries() {
		return retries;
	}

	public void setRetries(long retries) {
		this.retries = retries;
	}
	

}
