package uk.gov.dwp.uc.pairtest.utils.calculator;

import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeatToAllocateCalculatorTest {
    SeatToAllocateCalculator seatToAllocateCalculator = new SeatToAllocateCalculatorImpl();

    @Test
    void totalSeatsWhenAllTicketTypesPresent() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };

        assertEquals(5, seatToAllocateCalculator.calculateTotalSeatsToAllocate(ticketTypeRequests));
    }

    @Test
    void totalSeatsWhenOnlyAdult() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 8)
        };

        assertEquals(8, seatToAllocateCalculator.calculateTotalSeatsToAllocate(ticketTypeRequests));
    }

    @Test
    void totalSeatsWhenZeroTickets() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertEquals(0, seatToAllocateCalculator.calculateTotalSeatsToAllocate(ticketTypeRequests));
    }
}
