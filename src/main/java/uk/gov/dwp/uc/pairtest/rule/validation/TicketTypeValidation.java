package uk.gov.dwp.uc.pairtest.rule.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Arrays;
import java.util.Objects;

public class TicketTypeValidation implements TicketTypeRequestValidator {
    @Override
    public void validate(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        boolean hasInvalidType = Arrays.stream(ticketTypeRequests).map(TicketTypeRequest::getTicketType).anyMatch(Objects::isNull);
        if(hasInvalidType) {
            throw new InvalidPurchaseException("Invalid ticket type");
        }
    }
}
