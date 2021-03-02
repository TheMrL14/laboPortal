package be.ehb.finalwork.lennert.lapoportal.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ModificationAfterCreationValidator.class)
public @interface ModificationAfterCreation {
    String message() default "modification date must be >= creation date";
    Class<?>[] groups() default {}; // to control the order of validation
    Class<? extends Payload>[] payload() default {}; // to associate meta-data

}
