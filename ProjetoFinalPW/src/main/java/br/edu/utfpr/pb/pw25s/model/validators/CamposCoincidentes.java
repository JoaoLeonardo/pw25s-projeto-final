package br.edu.utfpr.pb.pw25s.model.validators;

import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = CamposCoincidentesValidator.class)
@Retention(RUNTIME)
@Documented
public @interface CamposCoincidentes {

    public String message() default "Os valores não coincidem";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

    String campo();

    String confirmacao();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List { CamposCoincidentes[] value(); }

}