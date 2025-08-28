package uk.gov.dwp.uc.pairtest.utils.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTotalAmountCalculatorTest {
    TicketTotalAmountCalculator ticketTotalAmountCalculator;

    @BeforeEach
    void setUp() {
        ticketTotalAmountCalculator = new TicketTotalAmountCalculatorImpl();
    }

    @Test
    void totalAmountToPayWhenAllTicketTypesPresent() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 8)
        };

        assertEquals(280, ticketTotalAmountCalculator.calculateTotalAmountToPay(ticketTypeRequests));
    }

    @Test
    void totalAmountToPayWhenOnlyAdultPresent() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 3),
        };

        assertEquals(75, ticketTotalAmountCalculator.calculateTotalAmountToPay(ticketTypeRequests));
    }

    @Test
    void totalAmountToPayWhenZeroTickets() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertEquals(0, ticketTotalAmountCalculator.calculateTotalAmountToPay(ticketTypeRequests));
    }
}
