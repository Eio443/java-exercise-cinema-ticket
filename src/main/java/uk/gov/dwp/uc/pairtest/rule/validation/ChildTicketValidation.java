package uk.gov.dwp.uc.pairtest.rule.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Arrays;

public class ChildTicketValidation implements TicketTypeRequestValidator {
    @Override
    public void validate(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        boolean hasChildTicket = Arrays.stream(ticketTypeRequests)
                                           .anyMatch(t -> t.getTicketType() == TicketTypeRequest.Type.CHILD && t.getNoOfTickets() > 0);

        if(hasChildTicket) {
            boolean hasNoAdultTicket = Arrays.stream(ticketTypeRequests)
                                                 .noneMatch(t -> t.getTicketType() == TicketTypeRequest.Type.ADULT && t.getNoOfTickets() > 0);

            if(hasNoAdultTicket) {
                throw new InvalidPurchaseException("Child ticket cannot be purchased without purchasing an Adult ticket");
            }
        }
    }
}
