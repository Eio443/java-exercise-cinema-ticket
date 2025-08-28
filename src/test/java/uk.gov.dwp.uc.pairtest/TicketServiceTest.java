package uk.gov.dwp.uc.pairtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.rule.BusinessRuleService;
import uk.gov.dwp.uc.pairtest.utils.calculator.SeatToAllocateCalculator;
import uk.gov.dwp.uc.pairtest.utils.calculator.TicketTotalAmountCalculator;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private BusinessRuleService businessRuleService;

    @Mock
    private TicketPaymentService ticketPaymentService;

    @Mock
    private SeatReservationService seatReservationService;

    @Mock
    private TicketTotalAmountCalculator ticketTotalAmountCalculator;

    @Mock
    private SeatToAllocateCalculator seatToAllocateCalculator;

    @InjectMocks
    private TicketServiceImpl ticketService;


    @Test
    void shouldMakePaymentAndReserveSeat() {
        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
        };
        int expectedTotalPayment = 40;
        int expectedSeatsToAllocate = 2;
        long id = 1L;

        when(ticketTotalAmountCalculator.calculateTotalAmountToPay(ticketTypeRequests)).thenReturn(expectedTotalPayment);
        when(seatToAllocateCalculator.calculateTotalSeatsToAllocate(ticketTypeRequests)).thenReturn(expectedSeatsToAllocate);

        ticketService.purchaseTickets(1L, ticketTypeRequests);

        verify(businessRuleService).validateBusinessRules(id, ticketTypeRequests);
        verify(ticketTotalAmountCalculator).calculateTotalAmountToPay(ticketTypeRequests);
        verify(seatToAllocateCalculator).calculateTotalSeatsToAllocate(ticketTypeRequests);
        verify(ticketPaymentService).makePayment(id, expectedTotalPayment);
        verify(seatReservationService).reserveSeat(id, expectedSeatsToAllocate);
    }
}
