package carsharing;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;
import carsharing.interfejs.CarDaoImpl;
import carsharing.interfejs.CompanyDaoImpl;
import carsharing.interfejs.CustomerDaoImpl;
import carsharing.interfejs.Tworzymy;

import java.util.List;
import java.util.Scanner;

public class Main {




    public static void main(String[] args) {
        // write your code here
        //System.out.println("yyy");
        Tworzymy t=new Tworzymy();
        t.tworz();
       // System.out.println("xxx");
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        CustomerDaoImpl customerDao=new CustomerDaoImpl();


        Scanner scanner=new Scanner(System.in);
        int i=-1;

        while (i!=0){
         //   t.tworz();
            System.out.println("1. Log in as a manager");
         //   t.tworz();
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit");
            i=Integer.parseInt(scanner.nextLine());
           // i= scanner.nextInt();
            if(i==1){
                int j=-1;
                while (j!=0){
                    System.out.println("1. Company list");
                    System.out.println("2. Create a company");
                    System.out.println("0. Back");
                    j= Integer.parseInt(scanner.nextLine());
                   // j= scanner.nextInt();
                        switch (j){
                            case 1:
                                List<Company> allCompanies = companyDao.getAllCompanies();
                                if(allCompanies.isEmpty())
                                    System.out.println("The company list is empty!");
                                else {
                                   // System.out.println("Company list:");
                                    System.out.println("Choose the company:");
                                    for(int x=0;x< allCompanies.size();x++){
                                        Company company = allCompanies.get(x);
                                        System.out.println(x+1+". "+company.getName());
                                    }
                                    System.out.println("0. Back");
                                    //----------------
                                    int k=-1;
                                    while (k!=0) {
                                        k = Integer.parseInt(scanner.nextLine());
                                        if(k!=0){
                                            Company c= allCompanies.get(k-1);
                                            carManagement(c);
                                            k=0;
                                        }
                                    }
                                    //----------------
                                }
                                System.out.println();
                                break;
                            case 2:
                                System.out.println("Enter the company name:");
                               // scanner.close();
                               // scanner=new Scanner(System.in);
                                String s = scanner.nextLine();
                                Company c=new Company(s);

                                companyDao.insertCompany(c);
                                System.out.println("The company was created!");
                                System.out.println();
                                break;
                            default:
                                break;
                        }
                }
            }else if(i==2){
                //loginas a customer
                // sprawdz czy są kustomery
                List<Customer> customerList = customerDao.getAllCustomers();
                if(customerList.isEmpty()) {
                    System.out.println("The customer list is empty!");
                    System.out.println();
                }
                else {
                    System.out.println("Customer list:");
                    for(int x=0;x< customerList.size();x++){
                        Customer customer = customerList.get(x);
                        System.out.println(x+1+". "+customer.getName());
                    }
                    System.out.println("0. Back");

                    int l = Integer.parseInt(scanner.nextLine());
                    if(l>0){
                        //idz do menu wybierania carow i zwracania carow
                        carWynajem(customerList.get(l-1).getId());

                    }


                }

            }else if(i==3){
                //dodaj kustomera
                System.out.println("Enter the customer name:");
                String s = scanner.nextLine();
                Customer c=new Customer(s);
                customerDao.insertCustomer(c);
                System.out.println("The customer was added!");
                System.out.println();
            }
        }
    }
    //------------------------------------------------------------
    private static void carWynajem(int idCustomer) {
        CustomerDaoImpl customerDao=new CustomerDaoImpl();
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        CarDaoImpl carDao= new CarDaoImpl();
        Company company;
        Car car;

        int l=-1;
        while (l!=0) {
            System.out.println("1. Rent a car");
            System.out.println("2. Return a rented car");
            System.out.println("3. My rented car");
            System.out.println("0. Back");
            Scanner scanner=new Scanner(System.in);
            l = Integer.parseInt(scanner.nextLine());

            switch (l) {
                case 1:
                    if( customerDao.czyCustomerWynajal(idCustomer)){
                        System.out.println("You've already rented a car!");
                    }else {
                        //-------------------------------------------------------
                        List<Company> allCompanies = companyDao.getAllCompanies();
                        if(allCompanies.isEmpty())
                            System.out.println("The company list is empty!");
                        else {
                            // System.out.println("Company list:");
                            System.out.println("Choose the company:");
                            for(int x=0;x< allCompanies.size();x++){
                                 company = allCompanies.get(x);
                                System.out.println(x+1+". "+company.getName());
                            }
                            System.out.println("0. Back");
                            //----------------
                            int k=-1;
                            while (k!=0) {
                                k = Integer.parseInt(scanner.nextLine());
                                if(k!=0){
                                    Company c= allCompanies.get(k-1);
                                    //++++++++++++++++++++++++++++++++++++++++++++++++
                                    List<Car> carList=carDao.getAllNotRentedCarsByCompanyId(c.getId());
                                    if(carList.isEmpty())
                                        System.out.println("The car list is empty!");
                                    else {
                                        //------------------------------------------------------
                                        System.out.println("Car list:");
                                        for(int x=0;x< carList.size();x++){
                                             car= carList.get(x);
                                            System.out.println(x+1+". "+car.getName());
                                        }
                                        System.out.println("0. Back");
                                        int m=-1;
                                        while (m!=0) {
                                            m = Integer.parseInt(scanner.nextLine());
                                            if(m!=0) {
                                                car = carList.get(m - 1);
                                                customerDao.rentCar(idCustomer,car.getId());
                                                System.out.println("You rented '"+car.getName()+"'");
                                                //zapisz
                                                m=0;
                                            }


                                        }
                                        //------------------------------------------------------
                                    }




                                    //++++++++++++++++++++++++++++++++++++++++++++++++
                                    //carManagement(c);
                                    k=0;
                                }
                            }
                            //----------------
                        }
                        System.out.println();
                        //-------------------------------------------------------

                            //wyswietl liste komanii - done
                            //wyswietl liste aut
                        //wybierz autoi wynajmij

                       // System.out.println("You rented 'Zmien naZmienna'");
                       // System.out.println();

                    }



                    break;
                case 2:
                    if(! customerDao.czyCustomerWynajal(idCustomer)){
                        System.out.println("You didn't rent a car!");
                    }else {
                        System.out.println("You've returned a rented car!");
                        // zwroc auto
                        customerDao.returnCar(idCustomer,0);

                    }
                    System.out.println();
                    break;


                case 3:
                   if(! customerDao.czyCustomerWynajal(idCustomer)){
                       System.out.println("You didn't rent a car!");
                   }else {
                       System.out.println("Your rented car:");
                        //poniez nazwe cara
                        Customer cc=customerDao.getCustomerById(idCustomer);
                        int aa=cc.getRentedCarId();
                        Car ccc=carDao.getCarById(aa);
                       System.out.println(ccc.getName());

                       System.out.println("Company:");
                        //pobierz nazwe kompany

                       int companyId = ccc.getCompanyId();
                      // System.out.println(" company id"+companyId);
                       Company company1=companyDao.getCompanyById(companyId);
                       System.out.println(company1.getName());

                       System.out.println();

                   }
                    System.out.println();
                    break;
            }
        }
    }

    //------------------------------------------------------------
    private static void carManagement(Company c) {
        int l=-1;
        while (l!=0) {
            System.out.println("'" + c.getName() + "' company:");
            System.out.println("1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");
            Scanner scanner=new Scanner(System.in);
            l = Integer.parseInt(scanner.nextLine());
            CarDaoImpl carDao= new CarDaoImpl();
            switch (l){
                case 1:
                    List<Car> carList=carDao.getAllCarsByCompanyId(c.getId());
                    if(carList.isEmpty())
                    System.out.println("The car list is empty!");
                    else {
                        System.out.println("Car list:");
                        for(int x=0;x< carList.size();x++){
                            Car car= carList.get(x);
                            System.out.println(x+1+". "+car.getName());
                        }

                    }
                    System.out.println();

                    break;
                case 2:
                    System.out.println("Enter the car name:");
                    String s = scanner.nextLine();
                    Car car= new Car(s,c.getId());

                    carDao.insertCar(car);
                    System.out.println("The car was added!");
                    System.out.println();
                    break;

                default:
                    l=0;
                    break;

            }


        }

    }
    //------------------------------------------------------------
}