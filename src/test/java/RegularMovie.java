
public class RegularMovie extends Movie {

    public RegularMovie(String title) {
        super(title, Movie.REGULAR);
    }

    @Override
    int frequentRenterPoints(int daysRented) {
        return 1;
    }
}
