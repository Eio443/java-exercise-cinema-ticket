package uk.gov.dwp.uc.pairtest.utils.calculator;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public interface SeatToAllocateCalculator {
    int calculateTotalSeatsToAllocate(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
}
