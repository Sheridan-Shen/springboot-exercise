package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.models.Company;
import com.oocl.springboot_exercise.repository.CompanyMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CompanyService {
    private CompanyMemoryRepository companyMemoryRepository;

    public CompanyService(CompanyMemoryRepository companyMemoryRepository){
        this.companyMemoryRepository = companyMemoryRepository;
    }

    public List<Company> getAllCompanies() {
        return new ArrayList<>(companyMemoryRepository.getCompanies().values());
    }

    public Company getCompanyById(Integer id) {
        return companyMemoryRepository.getCompany(id);
    }

//    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
//        Company company = companyRepository.getCompany(companyId);
//        if (company != null) {
//            return company.getEmployees();
//        }
//        return Collections.emptyList();
//    }

    public Company addCompany(Company company) {
        companyMemoryRepository.addCompany(company);
        return company;
    }

    public Company updateCompany(Integer id, Company companyDetails) {
        Company company = companyMemoryRepository.getCompany(id);
        if (company != null) {
            company.setName(companyDetails.getName());
            return company;
        }
        return null;
    }

    public boolean deleteCompany(Integer id) {
        return companyMemoryRepository.deleteCompany(id);
    }
}