package be.ehb.finalwork.lennert.lapoportal.validation;



import be.ehb.finalwork.lennert.lapoportal.entities.SOP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ModificationAfterCreationValidator implements ConstraintValidator<ModificationAfterCreation, SOP> {


    @Override
    public boolean isValid(SOP value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.getLastModifiedDate() == null || value.getCreationDate() == null) {
            return true;
        }
        return value.getLastModifiedDate().isAfter(value.getCreationDate())
                || value.getLastModifiedDate().isEqual(value.getCreationDate());

    }
}
