package store.validator;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CategoriesConstraintValidator implements ConstraintValidator<CategoriesConstraint, List<String>> {
    @Value("${categories.list}")
    private String[] allCategories;

    @Override
    public void initialize(CategoriesConstraint categoriesConstraint) {
    }

    public boolean isValid(List<String> categories, ConstraintValidatorContext constraintValidatorContext) {
        if ((categories == null) || Arrays.asList(allCategories).containsAll(categories))
            return true;
        return false;
    }

}
