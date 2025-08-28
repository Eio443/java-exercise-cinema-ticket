package uk.gov.dwp.uc.pairtest.rule.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.utils.constant.TicketTypeRequestConstant;

import java.util.Arrays;

public class TicketMaximumQuantityValidation implements  TicketTypeRequestValidator {
    @Override
    public void validate(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        int totalNoOfTickets = Arrays.stream(ticketTypeRequests).mapToInt(TicketTypeRequest::getNoOfTickets).sum();
        if(totalNoOfTickets > TicketTypeRequestConstant.MAX_TICKET_REQUEST) {
            throw new InvalidPurchaseException(String.format("Only a maximum of %d tickets can be purchased at a time", TicketTypeRequestConstant.MAX_TICKET_REQUEST));
        }
    }
}
