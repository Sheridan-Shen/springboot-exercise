package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.dao.Company;
import com.oocl.springboot_exercise.dao.Employee;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CompanyService {
    private Map<Integer, Company> companyDb = new HashMap<>();
    private AtomicInteger companyIdGenerator = new AtomicInteger(0);

    public List<Company> getAllCompanies() {
        return new ArrayList<>(companyDb.values());
    }

    public Company getCompanyById(Integer id) {
        return companyDb.get(id);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        Company company = companyDb.get(companyId);
        if (company != null) {
            return company.getEmployees();
        }
        return Collections.emptyList();
    }

    public Company addCompany(Company company) {
        Integer newId = companyIdGenerator.incrementAndGet();
        company.setId(newId);
        companyDb.put(newId, company);
        return company;
    }

    public Company updateCompany(Integer id, Company companyDetails) {
        Company company = companyDb.get(id);
        if (company != null) {
            company.setName(companyDetails.getName());
            return company;
        }
        return null;
    }

    public boolean deleteCompany(Integer id) {
        return companyDb.remove(id) != null;
    }
}