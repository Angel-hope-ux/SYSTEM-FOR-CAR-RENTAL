// Import statement
import java.util.ArrayList;
import java.util.List;

// Car Class
class Car {
    private String make;
    private String model;
    private double rentalPricePerDay;
    private boolean isAvailable;

    public Car(String make, String model, double rentalPricePerDay) {
        this.make = make;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double calculateRentalCost(int days) {
        return rentalPricePerDay * days;
    }

    @Override
    public String toString() {
        return make + " " + model + " | Price per day: $" + rentalPricePerDay;
    }
}

// Customer Class
class Customer {
    private String name;
    private String customerId;
    private String phoneNumber;

    public Customer(String name, String customerId, String phoneNumber) {
        this.name = name;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
    }

    // Getter method for customerId
    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerDetails() {
        return "Customer: " + name + " | ID: " + customerId + " | Phone: " + phoneNumber;
    }
}

// RentalAgency Class
class RentalAgency {
    private List<Car> carsAvailable;
    private List<Customer> customers;

    public RentalAgency() {
        carsAvailable = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void addCar(Car car) {
        carsAvailable.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Car rentCar(String customerId, String make, String model, int days) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            for (Car car : carsAvailable) {
                if (car.isAvailable() && car.toString().contains(make) && car.toString().contains(model)) {
                    car.setAvailability(false); // Mark the car as rented
                    System.out.println(customer.getCustomerDetails() + " rented " + car.toString() + " for " + days + " days.");
                    return car;
                }
            }
        }
        System.out.println("Car not available for rent.");
        return null;
    }

    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            // Use the getter method to get the customerId
            if (customerId.equals(customer.getCustomerId())) {
                return customer;
            }
        }
        return null;
    }
}

// Main Class (contains the main method)
public class Main {
    public static void main(String[] args) {
        // Create Rental Agency
        RentalAgency agency = new RentalAgency();

        // Create Cars
        Car car1 = new Car("Toyota", "Camry", 50.0);
        Car car2 = new Car("Honda", "Civic", 45.0);
        agency.addCar(car1);
        agency.addCar(car2);

        // Create Customers
        Customer customer1 = new Customer("Ella Sifah", "C128", "123-456-7890");
        Customer customer2 = new Customer("Ryan Joel", "C129", "987-654-3210");
        agency.addCustomer(customer1);
        agency.addCustomer(customer2);

        // Rent Cars to Customers
        agency.rentCar("C128", "Toyota", "Camry", 8); // Rent car for customer1
        agency.rentCar("C129", "Honda", "Civic", 11); // Rent car for customer2
    }
}
