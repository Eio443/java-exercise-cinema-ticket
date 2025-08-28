package uk.gov.dwp.uc.pairtest.rule.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketMinimumQuantityValidationTest {
    TicketMinimumQuantityValidation ticketMinimumQuantityValidation = new TicketMinimumQuantityValidation();

    @Test
    @DisplayName("zero total tickets should be rejected")
    void totalTicketOfZeroShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };
        assertThrows(InvalidPurchaseException.class, () -> ticketMinimumQuantityValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("total ticket of one should pass")
    void totalAdultTicketOfOneShouldNotThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };
        assertDoesNotThrow(() -> ticketMinimumQuantityValidation.validate(2L, ticketTypeRequests));
    }
}
