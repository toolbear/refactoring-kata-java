
public class NewReleaseMovie extends Movie {

    public NewReleaseMovie(String title) {
        super(title, Movie.NEW_RELEASE);
    }

    @Override
    int frequentRenterPoints(int daysRented) {
        if (daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
