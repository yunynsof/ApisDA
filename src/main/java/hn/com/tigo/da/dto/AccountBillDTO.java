package hn.com.tigo.da.dto;


public class AccountBillDTO extends DTO {
    /** Attribute that determine id. */
	private long id; // Tipo DB: NUMBER
	
        /** Attribute that determine idaccount. */
        private String idaccount; // Tipo DB: VARCHAR2
	
        /** Attribute that determine integrationid. */
        private String integrationid; // Tipo DB: VARCHAR2
	
        /** Attribute that determine client. */
        private String client; // Tipo DB: VARCHAR2
	
        /** Attribute that determine name. */
        private String name; // Tipo DB: VARCHAR2
	
        /** Attribute that determine identify. */
        private String identify; // Tipo DB: VARCHAR2
	
        /** Attribute that determine rtn. */
        private String rtn; // Tipo DB: VARCHAR2
	
        /** Attribute that determine adress. */
        private String adress; // Tipo DB: VARCHAR2
	
        /** Attribute that determine cycle. */
        private String cycle; // Tipo DB: VARCHAR2
	
        /** Attribute that determine phone. */
        private String phone; // Tipo DB: VARCHAR2
	
        /** Attribute that determine type. */
        private String type; // Tipo DB: VARCHAR2
	
        /** Attribute that determine exonerated. */
        private String exonerated; // Tipo DB: VARCHAR2
	
        /** Attribute that determine diplomat. */
        private String diplomat; // Tipo DB: VARCHAR2
	
        /** Attribute that determine carnet. */
        private String carnet; // Tipo DB: VARCHAR2
	
        /** Attribute that determine facturadora. */
        private String facturadora; // Tipo DB: VARCHAR2
	
        /** Attribute that determine agrupadora. */
        private String agrupadora; // Tipo DB: VARCHAR2
	
        /** Attribute that determine unificada. */
        private String unificada; // Tipo DB: VARCHAR2
	
        /** Attribute that determine email. */
        private String email; // Tipo DB: VARCHAR2
	
        /** Attribute that determine billmedium. */
        private String billmedium; // Tipo DB: VARCHAR2
	
        /** Attribute that determine typeaccount. */
        private String typeaccount; // Tipo DB: VARCHAR2

        private long selected;
        
	public final long getId() {
		return id;
	}
	public final void setId(final long id) {
		this.id = id;
	}

	public final String getIdaccount() {
		return idaccount;
	}
	public final void setIdaccount(final String idaccount) {
		this.idaccount = idaccount;
	}

	public final String getIntegrationid() {
		return integrationid;
	}
	public final void setIntegrationid(final String integrationid) {
		this.integrationid = integrationid;
	}

	public final String getClient() {
		return client;
	}
	public final void setClient(final String client) {
		this.client = client;
	}

	public final String getName() {
		return name;
	}
	public final void setName(final String name) {
		this.name = name;
	}

	public final String getIdentify() {
		return identify;
	}
	public final void setIdentify(final String identify) {
		this.identify = identify;
	}

	public final String getRtn() {
		return rtn;
	}
	public final void setRtn(final String rtn) {
		this.rtn = rtn;
	}

	public final String getAdress() {
		return adress;
	}
	public final void setAdress(final String adress) {
		this.adress = adress;
	}

	public final String getCycle() {
		return cycle;
	}
	public final void setCycle(final String cycle) {
		this.cycle = cycle;
	}

	public final String getPhone() {
		return phone;
	}
	public final void setPhone(final String phone) {
		this.phone = phone;
	}

	public final String getType() {
		return type;
	}
	public final void setType(final String type) {
		this.type = type;
	}

	public final String getExonerated() {
		return exonerated;
	}
	public final void setExonerated(final String exonerated) {
		this.exonerated = exonerated;
	}

	public final String getDiplomat() {
		return diplomat;
	}
	public final void setDiplomat(final String diplomat) {
		this.diplomat = diplomat;
	}

	public final String getCarnet() {
		return carnet;
	}
	public final void setCarnet(final String carnet) {
		this.carnet = carnet;
	}

	public final String getFacturadora() {
		return facturadora;
	}
	public final void setFacturadora(final String facturadora) {
		this.facturadora = facturadora;
	}

	public final String getAgrupadora() {
		return agrupadora;
	}
	public final void setAgrupadora(final String agrupadora) {
		this.agrupadora = agrupadora;
	}

	public final String getUnificada() {
		return unificada;
	}
	public final void setUnificada(final String unificada) {
		this.unificada = unificada;
	}

	public final String getEmail() {
		return email;
	}
	public final void setEmail(final String email) {
		this.email = email;
	}

	public final String getBillmedium() {
		return billmedium;
	}
	public final void setBillmedium(final String billmedium) {
		this.billmedium = billmedium;
	}

	public final String getTypeaccount() {
		return typeaccount;
	}
	public final void setTypeaccount(final String typeaccount) {
		this.typeaccount = typeaccount;
	}
        
        public long getSelected() {
        return selected;
        }

         public void setSelected(long selected) {
            this.selected = selected;
         }
}
