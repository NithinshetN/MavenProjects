package com.monetize.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.monetize.Main;
import com.monetize.dto.EmployeeFormatedData;
import com.monetize.dto.XmlFormat;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import com.monetize.dto.XmlFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class FileConverter {
    public void getJsonFormatedData(List<EmployeeFormatedData> employeeDetails){

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            File outputFile = new File("src/main/resources/outputResources/data.json");
            mapper.writeValue(outputFile, employeeDetails);
            System.out.println("Data successfully written to " + outputFile.getAbsolutePath());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getExcelFormatedData(List<EmployeeFormatedData> employeeDetails){

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Object Data");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("first_name");
        headerRow.createCell(2).setCellValue("lastname");
        headerRow.createCell(3).setCellValue("email");
        headerRow.createCell(4).setCellValue("gender");
        headerRow.createCell(5).setCellValue("currency");
        headerRow.createCell(6).setCellValue("salary");
        headerRow.createCell(7).setCellValue("currencyName");

        int rowNum=1;
        for(EmployeeFormatedData emp:employeeDetails){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getFirst_name());
            row.createCell(2).setCellValue(emp.getLast_name());
            row.createCell(3).setCellValue(emp.getEmail());
            row.createCell(4).setCellValue(emp.getGender());
            row.createCell(5).setCellValue(emp.getCurrency());
            row.createCell(6).setCellValue(emp.getSalary());
            row.createCell(7).setCellValue(emp.getCurrencyName());
        }

        try (FileOutputStream outputStream = new FileOutputStream("src/main/resources/outputResources/output.xlsx")) {
            workbook.write(outputStream);
            System.out.println("Excel file has been created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void xmlFileConverter(List<EmployeeFormatedData> employeeDetails) throws JAXBException {
        XmlFormat xmlFor=new XmlFormat();
        xmlFor.setObjects(employeeDetails);
       JAXBContext jaxbContext=JAXBContext.newInstance(XmlFormat.class);
       Marshaller marshaller=jaxbContext.createMarshaller();
       marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
       File file=new File("src/main/resources/outputResources/EmployeeDetails.xml");
       marshaller.marshal(xmlFor,file);
        System.out.println("XML file has been created successfully!");
    }

    public void csvFileConverter(List<EmployeeFormatedData> employeeDetails)throws IOException{
        try (FileWriter writer = new FileWriter("src/main/resources/outputResources/CsvFormat.csv");
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id", "firstname","lastname","email","gender","currency","salary","currencyName");

            for (EmployeeFormatedData emp:employeeDetails) {
                csvPrinter.printRecord(emp.getId(),emp.getFirst_name(),emp.getLast_name(),emp.getEmail(),emp.getGender(),emp.getCurrency(),emp.getSalary(),emp.getCurrency());
            }
            System.out.println("CSV file has been created successfully!");
        }
    }
}
