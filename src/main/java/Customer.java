import java.util.*;

public class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public double amountOwed() {
        double amountOwed = 0;
        for (Rental rental : rentals) {
            amountOwed += rental.amount();
        }
        return amountOwed;
    }

    public int earnedFrequentRenterPoints() {
        int earned = 0;
        for (Rental rental : rentals) {
            earned += rental.frequentRenterPoints();
        }
        return earned;
    }

    public String statement() {
        StringBuilder statement = new StringBuilder(statementHeader());

        for (Rental rental : rentals) {
            statement.append(statementLineItem(rental));
        }

        statement.append(statementFooter());

        return statement.toString();
    }

    private String statementHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String statementLineItem(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.amount()) + "\n";
    }

    private String statementFooter() {
        return "You owed " + String.valueOf(amountOwed()) + "\n" + "You earned "
               + String.valueOf(earnedFrequentRenterPoints()) + " frequent renter points\n";
    }
}
