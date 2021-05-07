package carsharing.interfejs;

import carsharing.entity.Car;

import java.util.List;

public interface CarDao {
    public List<Car> getAllCarsByCompanyId(int idCompany);
    public void insertCar(Car car);
    public Car getCarById( int idCar);
    public List<Car> getAllNotRentedCarsByCompanyId(int idCompany);

}
