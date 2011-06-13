public class RegularMovie extends Movie {

    public RegularMovie(String title) {
        super(title, Movie.REGULAR);
    }

    @Override
    int frequentRenterPoints(int daysRented) {
        return 1;
    }

    @Override
    protected double amount(int daysRented) {
        double thisAmount = 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }
}
