package com.monetize.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.monetize.domain.Currency;
import com.monetize.domain.Employee;
import com.monetize.dto.EmployeeFormatedData;
import com.monetize.util.JsonReaderUtil;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceImpliment implements ServiceMain{
    private Map<String, Currency> currencies;
    private List<Employee> emp;

    public ServiceImpliment(){
        currencies = JsonReaderUtil.loadFromDataFromJsonFile("/currency.json", new TypeReference<Map<String, Currency>>() {});
        emp=JsonReaderUtil.loadFromDataFromJsonFile("/employee.json", new TypeReference<List<Employee>>() {});
    }

    public List<EmployeeFormatedData> RetreiveFormatedData() {
        String amount = "";
        List<EmployeeFormatedData> empFormat = new ArrayList<>();
        for (Map.Entry<String, Currency> entry : currencies.entrySet()) {
            for (Employee e : emp) {
                if (entry.getKey().equals(e.getCurrency())) {
                    double salary = e.getSalary();
                    int decimalDigits = entry.getValue().getDecimal_digits();

                    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                    symbols.setDecimalSeparator('.');

                    DecimalFormat df = new DecimalFormat("#." + new String(new char[decimalDigits]).replace('\0', '0'));
                    df.setDecimalFormatSymbols(symbols);
                    df.setRoundingMode(decimalDigits > 0 ? RoundingMode.HALF_UP : RoundingMode.DOWN);

                    String formattedSalary = df.format(salary);

                    empFormat.add(EmployeeFormatedData.builder()
                            .id(e.getId())
                            .first_name(e.getFirst_name())
                            .last_name(e.getLast_name())
                            .email(e.getEmail())
                            .gender(e.getGender())
                            .currency(e.getCurrency())
                            .salary(entry.getValue().getSymbol_native() + " " + formattedSalary)
                            .currencyName(entry.getValue().getName())
                            .build());
                }
            }
        }
        return empFormat;
    }
}
