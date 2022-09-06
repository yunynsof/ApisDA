
package hn.com.tigo.da.dto;

public class DeleteMpModel {
    private String cycle;
    private String bankId;
    private String errorBank;
    private String jsonDelete;

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getErrorBank() {
        return errorBank;
    }

    public void setErrorBank(String errorBank) {
        this.errorBank = errorBank;
    }

    public String getJsonDelete() {
        return jsonDelete;
    }

    public void setJsonDelete(String jsonDelete) {
        this.jsonDelete = jsonDelete;
    }
}
