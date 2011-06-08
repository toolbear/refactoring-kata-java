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
            amountOwed += rentalAmount(rental);
        }
        return amountOwed;
    }

    public int earnedFrequentRenterPoints() {
        int earned = 0;
        for (Rental rental : rentals) {
            earned += frequentRenterPoints(rental);
        }
        return earned;
    }

    public String statement() {
        String result = statementHeader();

        for (Rental rental : rentals) {
            result += statementLineItem(rental);
        }

        result += statementFooter();

        return result;
    }

    private String statementHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String statementLineItem(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rentalAmount(rental)) + "\n";
    }

    private String statementFooter() {
        return "You owed " + String.valueOf(amountOwed()) + "\n" + "You earned "
                  + String.valueOf(earnedFrequentRenterPoints()) + " frequent renter points\n";
    }

    private int frequentRenterPoints(Rental rental) {
        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) return 2;
        else return 1;
    }

    private double rentalAmount(Rental rental) {
        double thisAmount = 0;

        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2) {
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3) {
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return thisAmount;
    }
}
