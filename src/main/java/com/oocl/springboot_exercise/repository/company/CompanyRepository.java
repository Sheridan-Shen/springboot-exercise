package com.oocl.springboot_exercise.repository.company;

import com.oocl.springboot_exercise.models.Company;

import java.util.List;

public interface CompanyRepository {
    List<Company> getCompanies();

    Company getCompanyById(Integer companyId);

    Company addCompany(Company company);

    void deleteCompany(Company company);
}
