import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<Rental>();

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
        int points = 0;
        for (Rental rental : rentals) {
            points += rental.frequentRenterPoints();
        }
        return points;
    }

    public String statement() {
        StringBuilder statement = new StringBuilder();

        appendStatementHeader(statement);
        for (Rental rental : rentals) appendStatementLineItem(statement, rental);
        appendStatementFooter(statement);

        return statement.toString();
    }

    private void appendStatementHeader(StringBuilder statement) {
        statement.append("Rental Record for ");
        statement.append(getName());
        statement.append("\n");
    }

    private void appendStatementLineItem(StringBuilder statement, Rental rental) {
        statement.append("\t");
        statement.append(rental.getMovie().getTitle());
        statement.append("\t");
        statement.append(rental.amount());
        statement.append("\n");
    }

    private void appendStatementFooter(StringBuilder statement) {
        statement.append("You owed ");
        statement.append(amountOwed());
        statement.append("\n");
        statement.append("You earned ");
        statement.append(earnedFrequentRenterPoints());
        statement.append(" frequent renter points\n");
    }
}
