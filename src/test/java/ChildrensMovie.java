
public class ChildrensMovie extends Movie {

    public ChildrensMovie(String title) {
        super(title, Movie.CHILDRENS);
    }

    @Override
    int frequentRenterPoints(int daysRented) {
        return 1;
    }
}
