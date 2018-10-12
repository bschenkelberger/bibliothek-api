package de.praktikant.bibliothek.api.resources.response.book;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerLendBookEntity;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LendBookHistoryResponse extends BaseResponse {
    private CustomerLendBookEntity lendBookHistory;

    public CustomerLendBookEntity getLendBookHistory() {
        return lendBookHistory;
    }

    public void setLendBookHistory(CustomerLendBookEntity lendBookHistory) {
        this.lendBookHistory = lendBookHistory;
    }
}
