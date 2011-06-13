public class NewReleaseMovie extends Movie {

    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    int frequentRenterPoints(int daysRented) {
        if (daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    protected double amount(int daysRented) {
        return (double) (daysRented * 3);
    }
}
