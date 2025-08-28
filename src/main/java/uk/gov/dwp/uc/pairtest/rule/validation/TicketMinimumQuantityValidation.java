package uk.gov.dwp.uc.pairtest.rule.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Arrays;

public class TicketMinimumQuantityValidation implements TicketTypeRequestValidator {
    @Override
    public void validate(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // Ticket minimum after passing through infant and child validations must be 1 ADULT
        int totalNoOfAdult = Arrays.stream(ticketTypeRequests).filter(t -> t.getTicketType() == TicketTypeRequest.Type.ADULT).mapToInt(TicketTypeRequest::getNoOfTickets).sum();
        if(totalNoOfAdult < 1) {
            throw new InvalidPurchaseException("ticket quantity must be at least one");
        }
    }
}
