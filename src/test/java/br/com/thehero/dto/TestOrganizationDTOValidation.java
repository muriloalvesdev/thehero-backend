package br.com.thehero.dto;

import br.com.thehero.providers.OrganizationDTOProviderTest;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

class TestOrganizationDTOValidation {

    private Validator validator;

    @BeforeEach
    void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @ParameterizedTest
    @ArgumentsSource(OrganizationDTOProviderTest.class)
    void shouldBeValidValue(OrganizationDTO organizationDTO) {
        Set<ConstraintViolation<OrganizationDTO>> violations = validator.validate(organizationDTO);

        Assert.assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(OrganizationDTOProviderTest.class)
    void shouldBeInvalidValueForWhatsapp(OrganizationDTO organizationDTO) {
        organizationDTO.setWhatsapp("11988887777");
        Set<ConstraintViolation<OrganizationDTO>> violations = validator.validate(organizationDTO);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("whatsapp is not valid!", violations.iterator().next().getMessage());
    }
}
