package uk.gov.dwp.uc.pairtest.rule.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InfantTicketValidationTest {
    InfantTicketValidation infantTicketValidation;

    @BeforeEach
    void setUp() {
        infantTicketValidation = new InfantTicketValidation();
    }

    @Test
    @DisplayName("infant ticket without adult ticket should be rejected")
    void infantTicketWithoutAdultTicketShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };
        assertThrows(InvalidPurchaseException.class, () -> infantTicketValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("total infant tickets more than adult tickets should be rejected")
    void infantTicketsMoreThanAdultTicketsShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2)
        };
        assertThrows(InvalidPurchaseException.class, () -> infantTicketValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("total infant tickets equal to adult tickets should pass")
    void infantTicketsEqualAdultTicketsShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2)
        };
        assertDoesNotThrow(() -> infantTicketValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("total infant tickets less than adult tickets should pass")
    void infantTicketsLessThanAdultTicketsShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 12),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 11)
        };
        assertDoesNotThrow(() -> infantTicketValidation.validate(2L, ticketTypeRequests));
    }
}
