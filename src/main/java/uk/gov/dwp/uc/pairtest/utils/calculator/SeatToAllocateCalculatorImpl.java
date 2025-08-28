package uk.gov.dwp.uc.pairtest.utils.calculator;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Arrays;

public class SeatToAllocateCalculatorImpl implements SeatToAllocateCalculator {
    @Override
    public int calculateTotalSeatsToAllocate(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        return Arrays.stream(ticketTypeRequests).filter(t -> t.getTicketType() != TicketTypeRequest.Type.INFANT).mapToInt(TicketTypeRequest::getNoOfTickets).sum();
    }
}
