package org.apache.fineract.cn.datamigration.util;

import org.apache.fineract.cn.datamigration.service.internal.service.helper.OrganizationService;
import org.apache.fineract.cn.office.api.v1.client.OrganizationManager;
import org.apache.fineract.cn.office.api.v1.domain.Address;
import org.apache.fineract.cn.office.api.v1.domain.Office;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class CreateOffice {

  public CreateOffice() { super(); }

@MockBean
private  OrganizationService organizationService;

public  void shouldCreateOffice(){

  try {

    InputStream spreadsheet = new FileInputStream("/home/le/Desktop/office.xlsx");

    XSSFWorkbook workbook = new XSSFWorkbook(spreadsheet);
    Sheet firstSheet = workbook.getSheetAt(0);
    int rowCount = firstSheet.getLastRowNum() + 1;
    Row row;

    String identifier =null ;
    String name  =null;
    String description  =null ;
    String street   =null;
    String city   =null;
    String region   =null;
    String postalCode   =null;
    String countryCode   =null;
    String country   =null;

    for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
      row = firstSheet.getRow(rowIndex);
      if (row.getCell(0) == null) {
        identifier = null;
      } else {
        switch (row.getCell(0) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            identifier = row.getCell(0).getStringCellValue();
            break;

          case Cell.CELL_TYPE_NUMERIC:
            identifier =  String.valueOf(((Double)row.getCell(0).getNumericCellValue()).intValue());
            break;
        }
      }

      if (row.getCell(1) == null) {
        name = null;
      } else {
        switch (row.getCell(1) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            name = row.getCell(1).getStringCellValue();
            break;

          case Cell.CELL_TYPE_NUMERIC:
            name =  String.valueOf(((Double)row.getCell(1).getNumericCellValue()).intValue());
            break;
        }
      }

      if (row.getCell(2) == null) {
        description = null;
      } else {
        switch (row.getCell(2) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            description = row.getCell(2).getStringCellValue();
            break;

          case Cell.CELL_TYPE_NUMERIC:
            description =   String.valueOf(((Double)row.getCell(2).getNumericCellValue()).intValue());
            break;
        }
      }

      if (row.getCell(3) == null) {
        street = null;
      } else {
        switch (row.getCell(3) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            street = row.getCell(3).getStringCellValue();
            break;

          case Cell.CELL_TYPE_NUMERIC:
            street =   String.valueOf(((Double)row.getCell(3).getNumericCellValue()).intValue());
            break;
        }
      }

      if (row.getCell(4) == null) {
        city = null;
      } else {
        switch (row.getCell(4) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            city = row.getCell(4).getStringCellValue();
            break;

          case Cell.CELL_TYPE_NUMERIC:
            city =  String.valueOf(((Double)row.getCell(4).getNumericCellValue()).intValue());
            break;
        }
      }

      if (row.getCell(5) == null) {
        region = null;
      } else {
        switch (row.getCell(5) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            region = row.getCell(5).getStringCellValue();
            break;

          case Cell.CELL_TYPE_NUMERIC:
            region =   String.valueOf(((Double)row.getCell(5).getNumericCellValue()).intValue());
            break;
        }
      }

      if (row.getCell(6) == null) {
        postalCode = null;
      } else {
        switch (row.getCell(6) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            postalCode = String.valueOf(row.getCell(6).getStringCellValue());
            break;

          case Cell.CELL_TYPE_NUMERIC:
            postalCode =  String.valueOf(((Double)row.getCell(6).getNumericCellValue()).intValue());
            break;
        }
      }

      if (row.getCell(7) == null) {
        countryCode = null;
      } else {
        switch (row.getCell(7) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            countryCode = String.valueOf(row.getCell(7).getStringCellValue());
            break;

          case Cell.CELL_TYPE_NUMERIC:
            countryCode =  String.valueOf(((Double)row.getCell(7).getNumericCellValue()).intValue());
            break;
        }
      }

      if (row.getCell(8) == null) {
        country = null;
      } else {
        switch (row.getCell(8) .getCellType()) {

          case Cell.CELL_TYPE_STRING:
            country = row.getCell(8).getStringCellValue();
            break;

          case Cell.CELL_TYPE_NUMERIC:
            country =  String.valueOf(((Double)row.getCell(8).getNumericCellValue()).intValue());
            break;
        }
      }

      Address address = new Address();
      address.setStreet(String.valueOf(street));
      address.setCity(String.valueOf(city));
      address.setRegion(String.valueOf(region));
      address.setPostalCode(String.valueOf(postalCode));
      address.setCountryCode(String.valueOf(countryCode));
      address.setCountry(String.valueOf(country));
      Office newOffice= new Office();
      newOffice.setIdentifier(String.valueOf(identifier));
      newOffice.setName(String.valueOf(name));
      newOffice.setDescription(String.valueOf(description));
      newOffice.setDescription(String.valueOf(description));
      newOffice.setAddress(address);

      System.out.println("\n\n\n\n\n\n\n\n\n "+
                                 "Identifier :" + newOffice.getIdentifier()+" "
                                 +" Name :" +newOffice.getName()+" "
                                 +"Description :"+newOffice.getDescription()+" "
                                 +"Street :"+newOffice.getAddress().getStreet()+" "
                                 +"City :"+newOffice.getAddress().getCity()+ " "
                                 +"Region :"+newOffice.getAddress().getRegion()+" "
                                 +"Postal code :"+newOffice.getAddress().getPostalCode()+" "
                                 +"Country Code :" +newOffice.getAddress().getCountry()+" "
                                 +" Contry :"+newOffice.getAddress().getCountry()+" "
                                 +"\n\n\n\n\n\n\n\n\n\n");
      
      this.organizationService.createOffice(newOffice);

    }
  } catch (IOException e) {
    e.printStackTrace();
  }
}

}
