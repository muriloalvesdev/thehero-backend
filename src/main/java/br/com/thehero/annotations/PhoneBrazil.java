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
    regexp = "^(?:\\+|00)?(55)\\s?\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)?\\s?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$")
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
