package uk.gov.dwp.uc.pairtest.rule.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketMaximumQuantityValidationTest {
    TicketMaximumQuantityValidation ticketMaximumQuantityValidation;

    @BeforeEach
    void setUp() {
        ticketMaximumQuantityValidation = new TicketMaximumQuantityValidation();
    }

    @Test
    @DisplayName("Maximum of 25 tickets can be purchased at a time")
    void shouldThrowExceptionWhenTicketQuantityMoreThan25() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 20),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 6)
        };
        assertThrows(InvalidPurchaseException.class, () -> ticketMaximumQuantityValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("Total quantity equal to 25 tickets should pass")
    void shouldNotThrowExceptionWhenTicketQuantityEqual25() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 20),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5)
        };
        assertDoesNotThrow(() -> ticketMaximumQuantityValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("Total quantity less than 25 tickets should pass")
    void shouldNotThrowExceptionWhenTicketQuantityLessThan25() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 8),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2)
        };
        assertDoesNotThrow(() -> ticketMaximumQuantityValidation.validate(2L, ticketTypeRequests));
    }

}
