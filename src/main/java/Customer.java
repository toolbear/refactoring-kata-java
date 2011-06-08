import java.util.*;

public class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<Rental>();
    private int earnedFrequentRenterPoints;

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
        return earnedFrequentRenterPoints;
    }

    public String statement() {
        earnedFrequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

        for (Rental rental : rentals) {
            int frequentRenterPoints;

            if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
                frequentRenterPoints = 2;
            } else {
                frequentRenterPoints = 1;
            }
            earnedFrequentRenterPoints += frequentRenterPoints;

            result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rentalAmount(rental)) + "\n";
        }

        result += "You owed " + String.valueOf(amountOwed()) + "\n";
        result += "You earned " + String.valueOf(earnedFrequentRenterPoints) + " frequent renter points\n";

        return result;
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
