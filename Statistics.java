package cinema;

public class Statistics {
    private int current_income = 0;
    private int number_of_available_seats;
    private int number_of_purchased_tickets = 0;

    public Statistics(Cinema cinema) {
        number_of_available_seats = cinema.getTOTAL_COLUMNS() * cinema.getTOTAL_ROWS();
    }

    public void removeTicket(Seat seat) {
        removeIncome(seat.getPrice());
        removePurchasedTicket();
        addAvailableSeat();
    }

    public void addTicket(Seat seat) {
        addPurchasedTicket();
        removeAvailableSeat();
        addIncome(seat.getPrice());
    }

    public void addIncome(int income) {
        current_income += income;
    }

    public void removeIncome(int income) {
        current_income -= income;
    }

    public void addPurchasedTicket() {
        number_of_purchased_tickets++;
    }

    public void removePurchasedTicket() {
        number_of_purchased_tickets--;
    }

    public void addAvailableSeat() {
        number_of_available_seats++;
    }

    public void removeAvailableSeat() {
        number_of_available_seats--;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

}
