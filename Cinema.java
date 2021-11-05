package cinema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cinema {

    private final int TOTAL_ROWS = 9;
    private final int TOTAL_COLUMNS = 9;
    private final List<Seat> available_seats = new ArrayList<>();
    private final Map<Token, Seat> purchasedTickets = new HashMap<>();

    public Cinema() {
        initSeats();
    }

    private void initSeats() {
        for (int i = 1; i <= TOTAL_ROWS; i++) {
            for (int j = 1; j <= TOTAL_COLUMNS; j++) {
                available_seats.add(new Seat(i, j));
            }
        }
    }

    public int getTOTAL_ROWS() {
        return TOTAL_ROWS;
    }

    public int getTOTAL_COLUMNS() {
        return TOTAL_COLUMNS;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public boolean deleteSeat(Seat seat) {
        return available_seats.remove(seat);
    }

    public Seat returnTicket(Token token) {
        return purchasedTickets.remove(token);
    }

    public void addPurchasedTicket(Token token, Seat seat) {
        purchasedTickets.put(token, seat);
    }
}
