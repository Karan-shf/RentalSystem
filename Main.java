import java.util.ArrayList;
import java.util.Date;

abstract class Item {
    private int id;
    private String title;
    private String genre;
    private Date releaseDate;
    private boolean isAvailable;

    public Item(String title,String genre,int id) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = new Date();
        this.id = id;

        isAvailable = true;
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

class Movie extends Item {

    private String director;
    private String cast;
    
    public Movie(String title,String genre,int id,String director,String cast) {
        
        super(title,genre,id);

        this.director = director;
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }
    public String getCast() {
        return cast;
    }
    
    // public void rentMovie(Customer customer) {

    //     MovieRental newRental = new MovieRental(this, customer, this.getId()+customer.getId());

    //     customer.getRentals().add(newRental);

    //     this.setAvailable(false);
    // }
}

class Game extends Item {
    
    private String platform;
    private String publisher;

    public Game(String title,String genre,int id,String platform,String publisher) {
        
        super(title, genre, id);

        this.platform = platform;
        this.publisher = publisher;
    }

    public String getPlatform() {
        return platform;
    }
    public String getPublisher() {
        return publisher;
    }

}

class Book extends Item {

    private String author;
    private String publisher;
    
    public Book(String title,String genre,int id,String author,String publisher) {
        
        super(title, genre, id);

        this.author = author;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }

}

class Customer {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    ArrayList<ItemRental> rentals;

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
    public ArrayList<ItemRental> getRentals() {
        return rentals;
    }

}

class ItemRental {

    private Item item;
    private Customer customer;
    private Date rentalDate;
    private Date returnedDate;
    private int id;

    public ItemRental(Item item,Customer customer,int id) {
        this.item = item;
        this.customer = customer;
        this.rentalDate = new Date();
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public Item getItem() {
        return item;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Date getRentalDate() {
        return rentalDate;
    }
    public Date getReturnedDate() {
        return returnedDate;
    }
    public void setReturnedDate() {
        this.returnedDate = new Date();
    }
    

}

// class MovieRental extends ItemRental {

//     private Movie rentedMovie;

//     public MovieRental(Movie rentedMovie,Customer customer,int id) {
//         super(customer, id);
//         this.rentedMovie = rentedMovie;

//         rentedMovie.setAvailable(false);
//     }

//     public Movie getRentedMovie() {
//         return rentedMovie;
//     }

// }

// class GameRental extends ItemRental {

//     private Game rentedGame;

//     public GameRental(Game rentedGame,Customer customer,int id) {
//         super(customer, id);
//         this.rentedGame = rentedGame;

//         rentedGame.setAvailable(false);
//     }

//     public Game getRentedGame() {
//         return rentedGame;
//     }

// }

// class BookRental extends ItemRental {

//     private Book rentedBook;

//     public BookRental(Book rentedBook,Customer customer,int id) {
//         super(customer, id);
//         this.rentedBook = rentedBook;

//         rentedBook.setAvailable(false);
//     }

//     public Book getRentedBook() {
//         return rentedBook;
//     }

// }

class RentalStore {

    ArrayList<Item> Items;
    ArrayList<Customer> customers;

    public void register(Customer customer) {
        customers.add(customer);
    }

    public void addItem(Item item) {
        Items.add(item);
    }

    public void removeItem(Item item) {
        Items.remove(item);
    }

    public ArrayList<Item> getAvailableItems() {

        ArrayList<Item> AvailableItems = new ArrayList<>(Items);

        for (Item item : Items) {
            if (item.isAvailable()) {
                AvailableItems.add(item);
            }
        }

        return AvailableItems;
        
    }

    // public void rentMovie(Movie movie,Customer customer) {
        
    //     // ItemRental newRental = new ItemRental(movie, customer, movie.getId()+customer.getId());
    //     MovieRental newMovieRental = new MovieRental(movie, customer, movie.getId()+customer.getId());

    //     customer.getRentals().add(newMovieRental);

    // }

    // public void rentGame(Game game,Customer customer) {

    //     GameRental newGameRental = new GameRental(game, customer, game.getId()+customer.getId());

    //     customer.getRentals().add(newGameRental);
        
    // }

    // public void rentBook(Book book,Customer customer) {
        
    //     BookRental newBookRental = new BookRental(book, customer, book.getId()+customer.getId());

    //     customer.getRentals().add(newBookRental);

    // }

    public void rentItem(Item item, Customer customer) {

        ItemRental newItemRental = new ItemRental(item,customer,item.getId()+customer.getId());

        customer.getRentals().add(newItemRental);

        item.setAvailable(false);
            
    }

    public void returnItem(ItemRental rental) {

        rental.setReturnedDate();

        rental.getCustomer().getRentals().remove(rental);

        rental.getItem().setAvailable(true);
        
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

    public Item getItemById(int id) {

        for (Item item : Items) {
            if (item.getId()==id) {
                return item;
            }
        }

        System.out.println("id not found :(");
        return null;
        
    }

}

public class Main {
    public static void main(String[] args) {
        //try me;
    }
}