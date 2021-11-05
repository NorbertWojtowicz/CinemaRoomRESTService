package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();
    Statistics statistics = new Statistics(cinema);

    @GetMapping("/seats")
    public Map<String, Object> getSeats() {
        return Map.of("total_rows", cinema.getTOTAL_ROWS(),
                        "total_columns", cinema.getTOTAL_COLUMNS(),
                        "available_seats", cinema.getAvailable_seats());
    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseSeat(@RequestBody Seat seat) {
        if (seat.getColumn() <= 0 || seat.getRow() <= 0 || seat.getRow() > cinema.getTOTAL_ROWS() || seat.getColumn() > cinema.getTOTAL_COLUMNS()) {
            return ResponseEntity.badRequest().body(new ConcurrentHashMap<>(Map.of("error", "The number of a row or a column is out of bounds!")));
        } else {
            if (!cinema.deleteSeat(seat)) {
                return ResponseEntity.badRequest().body(new ConcurrentHashMap<>(Map.of("error", "The ticket has been already purchased!")));
            } else {
                Token token = new Token();
                seat.setPrice();
                cinema.addPurchasedTicket(token, seat);
                statistics.addTicket(seat);
                return ResponseEntity.ok(new ConcurrentHashMap<>(Map.of("token", token.getToken(), "ticket", seat)));
            }
        }
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody Token token) {
        Seat returned_ticket = cinema.returnTicket(token);
        if (returned_ticket == null) {
            return ResponseEntity.badRequest().body(new ConcurrentHashMap<>(Map.of("error", "Wrong token!")));
        } else {
            statistics.removeTicket(returned_ticket);
            return ResponseEntity.ok(new ConcurrentHashMap<>(Map.of("returned_ticket", returned_ticket)));
        }
    }

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam(required = false) String password) {
        if ("super_secret".equals(password)) {
            return ResponseEntity.ok(statistics);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ConcurrentHashMap<>(Map.of("error", "The password is wrong!")));
        }
    }
}