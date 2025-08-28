package uk.gov.dwp.uc.pairtest.rule.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountIdValidationTest {
    AccountIdValidation accountIdValidation;

    @BeforeEach
    void setUp() {
        accountIdValidation = new AccountIdValidation();
    }

     TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
            new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2)
     };

    @Test
    void accountIdShouldThrowExceptionWhenZero(){
        assertThrows(InvalidPurchaseException.class, () -> accountIdValidation.validate(0L, ticketTypeRequests));
    }

    @Test
    void accountIdShouldThrowExceptionWhenNegative(){
        assertThrows(InvalidPurchaseException.class, () -> accountIdValidation.validate(-5L, ticketTypeRequests));
    }

    @Test
    void accountIdGreaterThanZeroShouldPass(){
        assertDoesNotThrow(() -> accountIdValidation.validate(1L, ticketTypeRequests));
    }
}
