package de.praktikant.bibliothek.api.utils;

import java.util.List;

import de.praktikant.bibliothek.api.resources.response.BaseResponse;
import de.praktikant.bibliothek.api.service.common.Message;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.service.common.Severity;

public class BaseResponseHelper {
    public static void mapResultToResponse(BaseResponse baseResponse, Result<?> result) {
        baseResponse.getMessages().addAll(result.getErrorMessages());

        if (hasErrors(result.getErrorMessages())) {
            baseResponse.setSuccessful(Boolean.FALSE);
        } else {
            baseResponse.setSuccessful(Boolean.TRUE);
        }
    }

    private static boolean hasErrors(List<Message> messages) {
        boolean result = Boolean.FALSE;

        for (Message message : messages) {
            if (Severity.ERROR.equals(message.getSeverity())) {
                result = Boolean.TRUE;
                break;
            }
        }

        return result;
    }
}
