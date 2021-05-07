package carsharing.interfejs;

import carsharing.entity.Company;

import java.util.List;

public interface CompanyDao {

    public List<Company> getAllCompanies();
    public Company getCompanyById(int idCompany);
    public void deleteCompanyById(int id);

    public void insertCompany(Company company);
    public void updateCompany(Company company);
    public void deleteCompany(Company company);

}
