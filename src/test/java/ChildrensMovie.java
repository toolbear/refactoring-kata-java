public class ChildrensMovie extends Movie {

    public ChildrensMovie(String title) {
        super(title, Movie.CHILDRENS);
    }

    @Override
    int frequentRenterPoints(int daysRented) {
        return 1;
    }

    @Override
    protected double amount(int daysRented) {
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}
