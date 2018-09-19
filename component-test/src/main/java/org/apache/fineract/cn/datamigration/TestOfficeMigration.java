package org.apache.fineract.cn.datamigration;

import org.apache.fineract.cn.datamigration.service.internal.service.helper.OrganizationService;
import org.apache.fineract.cn.datamigration.util.CreateOffice;
import org.apache.fineract.cn.datamigration.util.OfficeSheetGenerator;
import org.apache.fineract.cn.office.api.v1.EventConstants;
import org.apache.fineract.cn.office.api.v1.domain.Office;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


public class TestOfficeMigration extends AbstractDataMigrationTest {

  @MockBean
  private OrganizationService organizationService;

  public TestOfficeMigration(){super();}

  @Test
  public void shouldMigrateOfficce() throws Exception {

    logger.info("genarating office excel sheet test");
    OfficeSheetGenerator.CreateofficeSheet();
    logger.info("creating  office.....");
    CreateOffice createOffice = new CreateOffice();
    createOffice.shouldCreateOffice();

    this.eventRecorder.wait(EventConstants.OPERATION_POST_OFFICE, OfficeSheetGenerator.identifier);
    final Office savedOffice = this.organizationService.findOffice(OfficeSheetGenerator.identifier);
    Assert.assertNotNull(savedOffice);

    Assert.assertEquals(OfficeSheetGenerator.identifier,savedOffice.getIdentifier());
    Assert.assertEquals(OfficeSheetGenerator.name,savedOffice.getName());
    Assert.assertEquals(OfficeSheetGenerator .description,savedOffice.getDescription());
    Assert.assertEquals(OfficeSheetGenerator.street,savedOffice.getAddress().getStreet());
    Assert.assertEquals(OfficeSheetGenerator.city,savedOffice.getAddress().getCity());
    Assert.assertEquals(OfficeSheetGenerator.region,savedOffice.getAddress().getRegion());
    Assert.assertEquals(OfficeSheetGenerator.postalCode,savedOffice.getAddress().getPostalCode());
    Assert.assertEquals(OfficeSheetGenerator.countryCode,savedOffice.getAddress().getCountryCode());
    Assert.assertEquals(OfficeSheetGenerator.country,savedOffice.getAddress().getCountry());
  }

}
