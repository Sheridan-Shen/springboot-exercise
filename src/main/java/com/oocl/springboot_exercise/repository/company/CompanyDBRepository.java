package com.oocl.springboot_exercise.repository.company;

import com.oocl.springboot_exercise.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDBRepository implements CompanyRepository {
    @Autowired
    JpaCompanyRepository jpaCompanyRepository;

    @Override
    public List<Company> getCompanies() {
        return jpaCompanyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Integer companyId) {
        return jpaCompanyRepository.getCompanyById(companyId);
    }

    @Override
    public Company addCompany(Company company) {
        return jpaCompanyRepository.save(company);
    }

    @Override
    public void deleteCompany(Company company) {
        jpaCompanyRepository.delete(company);
    }
}
