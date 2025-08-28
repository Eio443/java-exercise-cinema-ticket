package uk.gov.dwp.uc.pairtest.rule.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberOfTicketsPerTypeValidationTest {
    NumberOfTicketsPerTypeValidation numberOfTicketsPerTypeValidation = new NumberOfTicketsPerTypeValidation();

    @Test
    @DisplayName("negative adult ticket quantity should be rejected")
    void negativeNumberOfAdultTicketShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, -2),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };
        assertThrows(InvalidPurchaseException.class, () -> numberOfTicketsPerTypeValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("negative child ticket quantity should be rejected")
    void negativeNumberOfChildTicketShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, -1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };
        assertThrows(InvalidPurchaseException.class, () -> numberOfTicketsPerTypeValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("negative infant ticket quantity should be rejected")
    void negativeNumberOfInfantTicketShouldThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, -1)
        };
        assertThrows(InvalidPurchaseException.class, () -> numberOfTicketsPerTypeValidation.validate(2L, ticketTypeRequests));
    }

    @Test
    @DisplayName("zero ticket quantity should pass")
    void zeroTicketShouldNotThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };
        assertDoesNotThrow(() -> numberOfTicketsPerTypeValidation.validate(2L, ticketTypeRequests));
    }
    @Test
    @DisplayName("positive number of ticket quantity should pass")
    void positiveTicketShouldNotThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };
        assertDoesNotThrow(() -> numberOfTicketsPerTypeValidation.validate(2L, ticketTypeRequests));
    }
    @Test
    @DisplayName("zero or more number of ticket quantity should pass")
    void zeroOrMoreTicketShouldNotThrowException() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };
        assertDoesNotThrow(() -> numberOfTicketsPerTypeValidation.validate(2L, ticketTypeRequests));
    }
}
