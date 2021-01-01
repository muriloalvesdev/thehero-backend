package br.com.thehero.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

/**
 * Annotation to validate whether the phone in Brazil is valid or not.
 *
 * @author Murilo Alves
 */
@Pattern(
    regexp = "^\\((?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")
@ReportAsSingleViolation
@Documented
@Constraint(validatedBy = {})
@Target(FIELD)
@Retention(RUNTIME)
public @interface PhoneBrazil {
  String message() default "";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
