package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.models.Company;
import com.oocl.springboot_exercise.models.Employee;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CompanyService {
//    private Map<Integer, Company> companyDb = new HashMap<>();
    private final HashMap<Integer, Company> companyDb = new HashMap<>(Map.of(
            1, new Company(1, "Acme Corporation", List.of(
                    new Employee(1, "John Smith", 32, "MALE", 5000.0),
                    new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0)
            )),
            2, new Company(2, "TechCom Solutions", List.of(
                    new Employee(3, "David Williams", 35, "MALE", 5500.0),
                    new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
                    new Employee(5, "Michael Jones", 40, "MALE", 7000.0)
            )),
            3, new Company(3, "Global Innovators"),
            4, new Company(4, "Stellar Enterprises"),
            5, new Company(5, "Nexus Industries")
    ));
    private AtomicInteger companyIdGenerator = new AtomicInteger(6);

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