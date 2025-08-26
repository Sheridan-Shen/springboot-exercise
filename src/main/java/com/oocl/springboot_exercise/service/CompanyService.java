package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.dao.Company;
import org.springframework.stereotype.Service;

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

}