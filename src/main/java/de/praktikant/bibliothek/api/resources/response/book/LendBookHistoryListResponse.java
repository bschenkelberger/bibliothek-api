package de.praktikant.bibliothek.api.resources.response.book;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerLendBookEntity;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LendBookHistoryListResponse extends BaseResponse {
    private List<CustomerLendBookEntity> lendBookHistory;

    public List<CustomerLendBookEntity> getLendBookHistory() {
        return lendBookHistory;
    }

    public void setLendBookHistory(List<CustomerLendBookEntity> lendBookHistory) {
        this.lendBookHistory = lendBookHistory;
    }
}
