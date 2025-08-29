//package com.oocl.springboot_exercise.repository.company;
//
//import com.oocl.springboot_exercise.models.Company;
//import com.oocl.springboot_exercise.models.Employee;
//import org.springframework.stereotype.Repository;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Repository
//public class CompanyMemoryRepository {
//    private final HashMap<Integer, Company> companyDb = new HashMap<>(Map.of(
//            1, new Company(1, "Acme Corporation", List.of(
//                    new Employee(1, "John Smith", 32, "MALE", 5000.0),
//                    new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0)
//            )),
//            2, new Company(2, "TechCom Solutions", List.of(
//                    new Employee(3, "David Williams", 35, "MALE", 5500.0),
//                    new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
//                    new Employee(5, "Michael Jones", 40, "MALE", 7000.0)
//            )),
//            3, new Company(3, "Global Innovators"),
//            4, new Company(4, "Stellar Enterprises"),
//            5, new Company(5, "Nexus Industries")
//    ));
//
//    private AtomicInteger companyIdGenerator = new AtomicInteger(6);
//
//
//    public HashMap<Integer, Company> getCompanies(){
//        return companyDb;
//    }
//
//    public Company getCompany(Integer companyId){
//        return companyDb.get(companyId);
//    }
//
//    public void addCompany(Company company){
//        Integer newId = companyIdGenerator.incrementAndGet();
//        company.setId(newId);
//        companyDb.put(newId, company);
//    }
//
//    public boolean deleteCompany(Integer id){
//        return companyDb.remove(id) != null;
//    }
//}
