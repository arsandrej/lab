package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/bookReservation")
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping("/bookReservation")
    public String placeReservation(
            @RequestParam String bookTitle,
            @RequestParam String readerName,
            @RequestParam String readerAddress,
            @RequestParam int numCopies,
            HttpServletRequest request,
            Model model
    ) {

        BookReservation reservation = this.bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);

        String ipAddress = request.getRemoteAddr();
        model.addAttribute("reservation", reservation);
        model.addAttribute("ipAddress", ipAddress);


        return "reservationConfirmation";
    }


}