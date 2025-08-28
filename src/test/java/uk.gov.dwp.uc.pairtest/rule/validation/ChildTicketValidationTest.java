package uk.gov.dwp.uc.pairtest.rule.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChildTicketValidationTest {
    ChildTicketValidation childTicketValidation = new ChildTicketValidation();

    @Test
    @DisplayName("child ticket without adult ticket should be rejected")
    void childTicketWithoutAdultTicketShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1)
        };
        assertThrows(InvalidPurchaseException.class, () -> childTicketValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("child tickets with adult ticket should pass")
    void childTicketsWithAdultTicketShouldNotThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3)
        };
        assertDoesNotThrow(() -> childTicketValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("zero child tickets should pass")
    void zeroChildTicketsShouldNotThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0)
        };
        assertDoesNotThrow(() -> childTicketValidation.validate(2L, ticketTypeRequests));
    }

}
