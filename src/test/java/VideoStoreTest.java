import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.*;

public class VideoStoreTest {
    private static final Movie REGULAR_MOVIE_1 = new Movie("Plan 9 from Outer Space", Movie.REGULAR);
    private static final Movie REGULAR_MOVIE_2 = new Movie("8 1/2", Movie.REGULAR);
    private static final Movie REGULAR_MOVIE_3 = new Movie("Eraserhead", Movie.REGULAR);
    private static final Movie CHILDRENS_MOVIE = new Movie("The Tigger Movie", Movie.CHILDRENS);
    private static final Movie NEW_RELEASE_1 = new Movie("The Cell", Movie.NEW_RELEASE);
    private static final Movie NEW_RELEASE_2 = new Movie("The Tigger Movie", Movie.NEW_RELEASE);
    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("Fred");
    }

    @Test
    @Ignore
    public void singleNewReleaseAmountOwed() {
        customer.addRental(new Rental(NEW_RELEASE_1, 3));
        assertThat(customer.amountOwed(), is(9.0));
    }

    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(NEW_RELEASE_1, 3));
        assertEquals("Rental Record for Fred\n" + "\tThe Cell\t9.0\n" + "You owed 9.0\n"
                     + "You earned 2 frequent renter points\n", customer.statement());
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(NEW_RELEASE_1, 3));
        customer.addRental(new Rental(NEW_RELEASE_2, 3));
        assertEquals("Rental Record for Fred\n" + "\tThe Cell\t9.0\n" + "\tThe Tigger Movie\t9.0\n" + "You owed 18.0\n"
                     + "You earned 4 frequent renter points\n", customer.statement());
    }

    @Test
    public void testSingleChildrensStatement() {
        customer.addRental(new Rental(CHILDRENS_MOVIE, 3));
        assertEquals("Rental Record for Fred\n" + "\tThe Tigger Movie\t1.5" + "\nYou owed 1.5"
                     + "\nYou earned 1 frequent renter points\n", customer.statement());
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(REGULAR_MOVIE_1, 1));
        customer.addRental(new Rental(REGULAR_MOVIE_2, 2));
        customer.addRental(new Rental(REGULAR_MOVIE_3, 3));

        assertEquals("Rental Record for Fred\n" + "\tPlan 9 from Outer Space\t2.0\n" + "\t8 1/2\t2.0\n"
                             + "\tEraserhead\t3.5\n" + "You owed 7.5\n" + "You earned 3 frequent renter points\n",
                     customer.statement());
    }

}
