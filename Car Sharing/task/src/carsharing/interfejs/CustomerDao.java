package carsharing.interfejs;

import carsharing.entity.Customer;

import java.util.List;

public interface CustomerDao {

    public List<Customer> getAllCustomers();
    public Customer getCustomerById(int odCustomer);

    public void insertCustomer(Customer customer);

    public void rentCar(int idCustomer,int idCar);
    public void returnCar(int idCustomer,int idCar);
    public boolean czyCustomerWynajal(int idCustomer);


}
