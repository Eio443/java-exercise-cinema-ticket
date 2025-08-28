package uk.gov.dwp.uc.pairtest.rule.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Arrays;

public class NumberOfTicketsPerTypeValidation implements TicketTypeRequestValidator {
    @Override
    public void validate(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // A valid number of tickets per Type must be >= 0 [Assumption is that the system might send ADULT, 2; CHILD, 0; and INFANT, 0]

        Arrays.stream(ticketTypeRequests)
              .filter(t -> t.getNoOfTickets() < 0)
              .findAny()
              .ifPresent(t -> { throw new InvalidPurchaseException("Number of tickets cannot be less than 0");});
    }
}
