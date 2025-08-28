package uk.gov.dwp.uc.pairtest.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BusinessRuleServiceTest {
    BusinessRuleService businessRuleService;

    @BeforeEach
    void setUp() {
        businessRuleService = new BusinessRuleServiceImpl();
    }

    @Test
    void shouldThrowExceptionWhenAccountIdIsZero() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(0L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenAccountIdIsNegative() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(-3L, ticketTypeRequests));
    }

    @Test
    void shouldNotThrowExceptionWhenAccountIdIsGreaterThanZero() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };

        assertDoesNotThrow(() -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenChildTicketWithNoAdultTicket() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldNotThrowExceptionWhenChildTicketWithAdultTicket() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertDoesNotThrow(() -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenInfantTicketWithNoAdultTicket() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldNotThrowExceptionWhenInfantTicketWithAdultTicket() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };

        assertDoesNotThrow(() -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenInfantTicketsMoreThanAdultTickets() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 12)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void minimumQuantityShouldBeOneAdultTicket() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertDoesNotThrow(() -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenZeroTickets() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void maximumQuantityShouldBe25() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 26),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldNotThrowExceptionWhenMaximumIs25() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 25),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertDoesNotThrow(() -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldNotThrowExceptionWhenTotalIs25() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
        };

        assertDoesNotThrow(() -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenTotalIsAbove25() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 15),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenInfantTicketQuantityIsNegative() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 0),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, -1)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenChildTicketQuantityIsNegative() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, -1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }

    @Test
    void shouldThrowExceptionWhenAdultTicketQuantityIsNegative() {

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, -1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 0)
        };

        assertThrows(InvalidPurchaseException.class, () -> businessRuleService.validateBusinessRules(1L, ticketTypeRequests));
    }
}
