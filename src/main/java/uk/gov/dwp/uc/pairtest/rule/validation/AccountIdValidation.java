package uk.gov.dwp.uc.pairtest.rule.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class AccountIdValidation implements TicketTypeRequestValidator {
    @Override
    public void validate(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        if(!(accountId >= 1)) {
            throw new InvalidPurchaseException("invalid account Id");
        }
    }
}
