package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.models.Company;
import com.oocl.springboot_exercise.models.Employee;
import com.oocl.springboot_exercise.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return new ArrayList<>(companyRepository.getCompanies().values());
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.getCompany(id);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        Company company = companyRepository.getCompany(companyId);
        if (company != null) {
            return company.getEmployees();
        }
        return Collections.emptyList();
    }

    public Company addCompany(Company company) {
        companyRepository.addCompany(company);
        return company;
    }

    public Company updateCompany(Integer id, Company companyDetails) {
        Company company = companyRepository.getCompany(id);
        if (company != null) {
            company.setName(companyDetails.getName());
            return company;
        }
        return null;
    }

    public boolean deleteCompany(Integer id) {
        return companyRepository.deleteCompany(id);
    }
}