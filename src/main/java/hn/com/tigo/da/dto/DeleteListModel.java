
package hn.com.tigo.da.dto;

import java.util.List;

public class DeleteListModel {
    List<BankDeleteModel> deleteList;

	/**
	 * @return the deleteList
	 */
	public final List<BankDeleteModel> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public final void setDeleteList(List<BankDeleteModel> deleteList) {
		this.deleteList = deleteList;
	}
}
