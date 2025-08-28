package uk.gov.dwp.uc.pairtest.rule;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.rule.validation.*;

import java.util.Arrays;
import java.util.List;

public class BusinessRuleServiceImpl implements BusinessRuleService {
    private final List<TicketTypeRequestValidator> businessRules;

    public BusinessRuleServiceImpl() {
        this.businessRules = Arrays.asList(
                new AccountIdValidation(),
                new TicketTypeValidation(),
                new NumberOfTicketsPerTypeValidation(),
                new ChildTicketValidation(),
                new InfantTicketValidation(),
                new TicketMinimumQuantityValidation(),
                new TicketMaximumQuantityValidation()
        );
    }

    @Override
    public void validateBusinessRules(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        for(TicketTypeRequestValidator businessRule : businessRules) {
            businessRule.validate(accountId, ticketTypeRequests);
        }
    }
}
