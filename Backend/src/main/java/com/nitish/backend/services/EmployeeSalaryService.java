package com.nitish.backend.services;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.nitish.backend.dto.SalaryResponse;
import com.nitish.backend.entity.EmployeeSalary;
import com.nitish.backend.entity.Employees;
import com.nitish.backend.mapper.SalaryMapper;
import com.nitish.backend.repo.EmployeeRepo;
import com.nitish.backend.repo.SalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeSalaryService {
    @Autowired
    SalaryRepo salaryRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    SalaryMapper mapper;


    public List<SalaryResponse> getLastSalary(String emailAddress) {
//        List<EmployeeSalary> lastSalary = salaryRepo.findByEmployeeId(employeeRepo.findByEmail(emailAddress).getEmployeeId());
        Employees employee = employeeRepo.findByEmail(emailAddress);

        List<EmployeeSalary> lastSalary = salaryRepo.findByEmployee(employee);
        List<SalaryResponse> salaries = new ArrayList<>();

        for (EmployeeSalary employeeSalary : lastSalary) {
            salaries.add(mapper.toSalaryResponse(employeeSalary));
        }
        return salaries;
    }

    public List<SalaryResponse> getSalaryHistory(String emailAddress) {
//        List<EmployeeSalary> latestSalary = salaryRepo.findHistoryByEmployeeId(employeeRepo.findByEmail(emailAddress).getEmployeeId());
        Employees employee = employeeRepo.findByEmail(emailAddress);
        List<EmployeeSalary> latestSalary = salaryRepo.findHistoryByEmployeeId(employee);
        List<SalaryResponse> salaryList = new ArrayList<>();


        for(EmployeeSalary employeeSalary : latestSalary) {
            salaryList.add(mapper.toSalaryResponse(employeeSalary));
        }
        return salaryList;
    }


    public byte[] generatePdf(String email, int month){
        Employees employee = employeeRepo.findByEmail(email);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found for the given email.");
        }

        List<EmployeeSalary> monthlySalary = salaryRepo.findByMonth(employee, month);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

        document.add(new Paragraph("Salary Details for the Month").setBold().setFontSize(18));
        document.add(new Paragraph("Employee: " + employee.getFirstName()+" "+employee.getLastName()).setFontSize(12));
        document.add(new Paragraph("Email: " + email).setFontSize(12));
        document.add(new Paragraph("Month: " + Month.of(month).name()).setFontSize(12));
        document.add(new Paragraph("\n"));

        Table table = new Table(new float[]{3, 5, 3});
        table.addCell(new Cell().add("Payment Date").setBold());
        table.addCell(new Cell().add("Salary Description").setBold());
        table.addCell(new Cell().add("Salary Amount").setBold());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (EmployeeSalary salary : monthlySalary) {
            table.addCell(salary.getPaymentDate().format(formatter));
            table.addCell(salary.getDescription());
            table.addCell(String.valueOf(salary.getAmount()));
        }

        document.add(table);

        double totalSalary = monthlySalary.stream()
                .mapToDouble(EmployeeSalary::getAmount)
                .sum();
        document.add(new Paragraph("\nTotal Salary: " + totalSalary).setBold().setFontSize(12));

        document.close();

        return outputStream.toByteArray();
    }
}
