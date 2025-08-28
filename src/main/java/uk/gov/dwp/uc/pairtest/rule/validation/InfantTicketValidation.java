package uk.gov.dwp.uc.pairtest.rule.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Arrays;

public class InfantTicketValidation implements TicketTypeRequestValidator {
    @Override
    public void validate(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        int numberOfInfantTicket = Arrays.stream(ticketTypeRequests).filter(t -> t.getTicketType() == TicketTypeRequest.Type.INFANT).mapToInt(TicketTypeRequest::getNoOfTickets).sum();

        if(numberOfInfantTicket > 0) {
            int numberOfAdultTicketType = Arrays.stream(ticketTypeRequests).filter(t -> t.getTicketType() == TicketTypeRequest.Type.ADULT).mapToInt(TicketTypeRequest::getNoOfTickets).sum();
            if(numberOfAdultTicketType == 0) {
                throw new InvalidPurchaseException("Infant ticket cannot be purchased without purchasing an Adult ticket");
            }
            else if(numberOfInfantTicket > numberOfAdultTicketType) {
                throw new InvalidPurchaseException("Infant tickets must be less than or equal to the number of Adult tickets");
            }
        }
    }
}
