package esportsclash.pratique.auth.application.exceptions;

import esportsclash.pratique.core.domain.exception.BadRequestException;

public class EmailAddressUnavailableException extends BadRequestException {
    public EmailAddressUnavailableException() {
        super("Email address is already in use");
    }
}
