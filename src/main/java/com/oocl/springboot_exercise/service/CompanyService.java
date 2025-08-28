package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.models.Company;
import com.oocl.springboot_exercise.repository.company.CompanyDBRepository;
import com.oocl.springboot_exercise.repository.company.CompanyMemoryRepository;
import com.oocl.springboot_exercise.repository.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyDBRepository companyDBRepository){
        this.companyRepository = companyDBRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getCompanies();
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.getCompanyById(id);
    }

//    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
//        Company company = companyRepository.getCompany(companyId);
//        if (company != null) {
//            return company.getEmployees();
//        }
//        return Collections.emptyList();
//    }

    public Company addCompany(Company company) {
        return companyRepository.addCompany(company);
    }

    public Company updateCompany(Integer id, Company companyDetails) {
        Company company = companyRepository.getCompanyById(id);
        if (company != null) {
            company.setName(companyDetails.getName());
            return company;
        }
        return null;
    }

    public boolean deleteCompany(Integer id) {
        Company targetCompany = companyRepository.getCompanyById(id);
        companyRepository.deleteCompany(targetCompany);
        return true;
    }
}