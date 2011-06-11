import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {
    private static final Movie NEW_RELEASE_1 = new Movie ("The Cell", Movie.NEW_RELEASE);
    private static final Movie NEW_RELEASE_2 = new Movie ("The Tigger Movie", Movie.NEW_RELEASE);
    private static final Movie REGULAR_MOVIE_1 = new Movie ("Plan 9 from Outer Space", Movie.REGULAR);
    private static final Movie REGULAR_MOVIE_2 = new Movie ("8 1/2", Movie.REGULAR);
    private static final Movie REGULAR_MOVIE_3 = new Movie ("Eraserhead", Movie.REGULAR);
    private static final Movie CHILDRENS_MOVIE = new Movie ("The Tigger Movie", Movie.CHILDRENS);

    private Customer customer;

    @Before
    public void setUp ()  {
        customer = new Customer ("Fred");
    }

    @Test
    public void singleNewReleaseAmountOwed() throws Exception {
        customer.addRental (new Rental (NEW_RELEASE_1, 3));
        customer.statement();
        assertThat(customer.amountOwed(), is(9.0));
    }

    @Test
    public void singleNewReleaseEarnedFrequentRenterPoints() throws Exception {
        customer.addRental (new Rental (NEW_RELEASE_1, 3));
        customer.statement();
        assertThat(customer.earnedFrequentRenterPoints(), is(2));
    }

    @Test
    public void dualNewReleaseAmountOwed() throws Exception {
        customer.addRental (new Rental (NEW_RELEASE_1, 3));
        customer.addRental (new Rental (NEW_RELEASE_2, 3));
        customer.statement();
        assertThat(customer.amountOwed(), is(18.0));
    }

    @Test
    public void dualNewReleaseEarnedFrequentRenterPoints() throws Exception {
        customer.addRental (new Rental (NEW_RELEASE_1, 3));
        customer.addRental (new Rental (NEW_RELEASE_2, 3));
        customer.statement();
        assertThat(customer.earnedFrequentRenterPoints(), is(4));
    }

    @Test
    public void singleChildrensAmountOwed() throws Exception {
        customer.addRental (new Rental (CHILDRENS_MOVIE, 3));
        customer.statement();
        assertThat(customer.amountOwed(), is(1.5));
    }

    @Test
    public void singleChildrensEarnedFrequentRenterPoints() throws Exception {
        customer.addRental (new Rental (CHILDRENS_MOVIE, 3));
        customer.statement();
        assertThat(customer.earnedFrequentRenterPoints(), is(1));
    }

    @Test
    public void multipleRegularAmountOwed() throws Exception {
        customer.addRental (new Rental (REGULAR_MOVIE_1, 1));
        customer.addRental (new Rental (REGULAR_MOVIE_2, 2));
        customer.addRental (new Rental (REGULAR_MOVIE_3, 3));
        customer.statement();
        assertThat(customer.amountOwed(), is(7.5));
    }

    @Test
    public void multipleRegularEarnedFrequentRenterPoints() throws Exception {
        customer.addRental (new Rental (REGULAR_MOVIE_1, 1));
        customer.addRental (new Rental (REGULAR_MOVIE_2, 2));
        customer.addRental (new Rental (REGULAR_MOVIE_3, 3));
        customer.statement();
        assertThat(customer.earnedFrequentRenterPoints(), is(3));
    }

    @Test
    public void multipleRegularStatement () {
        customer.addRental (new Rental (REGULAR_MOVIE_1, 1));
        customer.addRental (new Rental (REGULAR_MOVIE_2, 2));
        customer.addRental (new Rental (REGULAR_MOVIE_3, 3));

        assertEquals ("Rental Record for Fred\n" +
                "\tPlan 9 from Outer Space\t2.0\n" +
                "\t8 1/2\t2.0\n" +
                "\tEraserhead\t3.5\n" +
                "You owed 7.5\n" +
                "You earned 3 frequent renter points\n", customer.statement ());
    }
}
