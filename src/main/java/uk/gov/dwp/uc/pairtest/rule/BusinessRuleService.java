package uk.gov.dwp.uc.pairtest.rule;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public interface BusinessRuleService {
    void validateBusinessRules(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
}
