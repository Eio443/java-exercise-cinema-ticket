package uk.gov.dwp.uc.pairtest.utils.calculator;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.utils.constant.TicketTypeRequestConstant;

import java.util.Arrays;

public class TicketTotalAmountCalculatorImpl implements TicketTotalAmountCalculator {
    @Override
    public int calculateTotalAmountToPay(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        return Arrays.stream(ticketTypeRequests).mapToInt(this::getTicketTypeRequestAmount).sum();
    }

    private int getTicketTypeRequestAmount(TicketTypeRequest ticketTypeRequest){
        return switch(ticketTypeRequest.getTicketType()){
            case ADULT -> ticketTypeRequest.getNoOfTickets() * TicketTypeRequestConstant.ADULT_TICKET_PRICE;
            case CHILD -> ticketTypeRequest.getNoOfTickets() * TicketTypeRequestConstant.CHILD_TICKET_PRICE;
            case INFANT -> 0;
        };
    }
}
