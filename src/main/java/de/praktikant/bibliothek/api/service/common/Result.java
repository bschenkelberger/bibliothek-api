package de.praktikant.bibliothek.api.service.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private T value;
    private Integer status;
    private List<Message> errorMessages = new ArrayList<>();

    public Result() {
    }

    public Result(T value) {
        this.value = value;
    }

    public Result(T value, Integer status) {
        this.value = value;
        this.status = status;
    }

    public void addMessage(Severity severity, String content) {
        this.errorMessages.add(new Message(severity, content));
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Message> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<Message> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
