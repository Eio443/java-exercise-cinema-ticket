package uk.gov.dwp.uc.pairtest.rule.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TicketTypeValidationTest {
    TicketTypeValidation ticketTypeValidation;

    @BeforeEach
    void setUp() {
        ticketTypeValidation = new TicketTypeValidation();
    }

    @Test
    @DisplayName("ADULT is valid type")
    void adultTicketTypeShouldPass() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1)
        };
        assertDoesNotThrow(() -> ticketTypeValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("CHILD is valid type")
    void childTicketTypeShouldPass() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1)
        };
        assertDoesNotThrow(() -> ticketTypeValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("INFANT is valid type")
    void infantTicketTypeShouldPass() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };
        assertDoesNotThrow(() -> ticketTypeValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("only ADULT, CHILD, INFANT ticket types valid ")
    void adultChildInfantTicketTypeShouldPass() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };
        assertDoesNotThrow(() -> ticketTypeValidation.validate(2L, ticketTypeRequests));
    }
}
