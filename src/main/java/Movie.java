public abstract class Movie {
    private String title;

    public Movie (String title) {
        this.title      = title;
    }

    public String getTitle () {
        return title;
    }

    abstract int frequentRenterPoints(int daysRented);

    protected abstract double amount(int daysRented);
}
