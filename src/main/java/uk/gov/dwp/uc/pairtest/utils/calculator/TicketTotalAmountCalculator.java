package uk.gov.dwp.uc.pairtest.utils.calculator;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public interface TicketTotalAmountCalculator {
    int calculateTotalAmountToPay(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
}
