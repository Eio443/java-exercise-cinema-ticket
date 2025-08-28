package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.rule.BusinessRuleService;
import uk.gov.dwp.uc.pairtest.utils.calculator.SeatToAllocateCalculator;
import uk.gov.dwp.uc.pairtest.utils.calculator.TicketTotalAmountCalculator;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    private final BusinessRuleService businessRuleService;
    private final TicketPaymentService ticketPaymentService;
    private final SeatReservationService seatReservationService;
    private final TicketTotalAmountCalculator ticketTotalAmountCalculator;
    private final SeatToAllocateCalculator seatToAllocateCalculator;

    private TicketServiceImpl(BusinessRuleService businessRuleService,
                             TicketPaymentService ticketPaymentService,
                             SeatReservationService seatReservationService,
                             TicketTotalAmountCalculator ticketTotalAmountCalculator,
                             SeatToAllocateCalculator seatToAllocateCalculator) {
        this.businessRuleService = businessRuleService;
        this.ticketPaymentService = ticketPaymentService;
        this.seatReservationService = seatReservationService;
        this.ticketTotalAmountCalculator = ticketTotalAmountCalculator;
        this.seatToAllocateCalculator = seatToAllocateCalculator;
    }

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        businessRuleService.validateBusinessRules(accountId, ticketTypeRequests);

        int totalAmountToPay = ticketTotalAmountCalculator.calculateTotalAmountToPay(ticketTypeRequests);
        int totalSeatsToAllocate = seatToAllocateCalculator.calculateTotalSeatsToAllocate(ticketTypeRequests);

        ticketPaymentService.makePayment(accountId, totalAmountToPay);
        seatReservationService.reserveSeat(accountId, totalSeatsToAllocate);
    }

}