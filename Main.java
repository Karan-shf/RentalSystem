import java.util.ArrayList;
import java.util.Date;
//comment
class Movie {

    private int id;
    private String title;
    private String genre;
    private String director;
    private String cast;
    private Date releaseDate;
    private boolean isAvailable;
    
    public Movie(String title,String genre,String director,String cast,Date releaseDate,int id) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.releaseDate = releaseDate;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public String getDirector() {
        return director;
    }
    public String getCast() {
        return cast;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}

class Customer {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    ArrayList<Rental> rentals;

    public Customer(String name,String email,String phone,String address,int id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public ArrayList<Rental> getRentals() {
        return rentals;
    }

}

class Rental {

    private Movie rentedMovie;
    private Customer customer;
    private Date rentalDate;
    private Date returndDate;
    private int id;

    public Rental(Movie rentedMovie,Customer customer,Date rentalDate,int id) {
        this.rentedMovie = rentedMovie;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.id = id;

        rentedMovie.setAvailable(false);
    }

    public int getId() {
        return id;
    }
    public Movie getRentedMovie() {
        return rentedMovie;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Date getRentalDate() {
        return rentalDate;
    }
    public Date getReturndDate() {
        return returndDate;
    }
    public void setReturndDate(Date returndDate) {
        this.returndDate = returndDate;
    }

}

class RentalStore {

    ArrayList<Movie> movies;
    ArrayList<Customer> customers;

    public void register(Customer customer) {
        customers.add(customer);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public ArrayList<Movie> getAvailableMovies() {

        ArrayList<Movie> AvailableMovies = new ArrayList<>(movies);

        for (Movie movie : movies) {
            if (movie.isAvailable()) {
                AvailableMovies.add(movie);
            }
        }

        return AvailableMovies;
        
    }

    public void rentMovie(Movie movie,Customer customer) {

        Date date = new Date();
        
        Rental newRental = new Rental(movie, customer, date, movie.getId()+customer.getId());

        customer.getRentals().add(newRental);

    }

    public void returnMovie(Rental rental) {

        Date date = new Date();

        rental.setReturndDate(date);

        rental.getCustomer().getRentals().remove(rental);

        rental.getRentedMovie().setAvailable(true);
        
    }

    public Customer getCustomerById(int id) {

        for (Customer customer : customers) {
            if (customer.getId()==id) {
                return customer;
            }
        }

        System.out.println("id not found :(");
        return null;
        
    }

    public Movie getMovieById(int id) {

        for (Movie movie : movies) {
            if (movie.getId()==id) {
                return movie;
            }
        }

        System.out.println("id not found :(");
        return null;
        
    }

}


public class Main {
    public static void main(String[] args) {
        
    }
}
