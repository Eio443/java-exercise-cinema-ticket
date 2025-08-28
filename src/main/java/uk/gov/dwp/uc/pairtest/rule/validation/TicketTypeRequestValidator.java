package uk.gov.dwp.uc.pairtest.rule.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

@FunctionalInterface
public interface TicketTypeRequestValidator {
    void validate(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
}
