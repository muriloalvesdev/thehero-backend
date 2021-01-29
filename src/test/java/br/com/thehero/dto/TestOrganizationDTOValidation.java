package br.com.thehero.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

class TestOrganizationDTOValidation {

    private Validator validator;
    private OrganizationDTO organizationDTO;

    @BeforeEach
    void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.organizationDTO = OrganizationDTO.newBuilder().build();
    }

    @Test
    void shouldBeValidValueForWhatsapp() {
        this.organizationDTO.setWhatsapp("+5511988887777");
        Set<ConstraintViolation<OrganizationDTO>> violations = validator
                .validateProperty(organizationDTO, "whatsapp", Default.class);

        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    void shouldBeInvalidValueForWhatsapp() {
        this.organizationDTO.setWhatsapp("11988887777");
        Set<ConstraintViolation<OrganizationDTO>> violations = validator
                .validateProperty(organizationDTO, "whatsapp", Default.class);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("whatsapp is not valid!", violations.iterator().next().getMessage());
    }
}
