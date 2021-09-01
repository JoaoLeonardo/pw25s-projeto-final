package br.edu.utfpr.pb.pw25s.model.validators;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CamposCoincidentesValidator implements ConstraintValidator<CamposCoincidentes, Object> {

    private String primeiroCampo;
    private String segundoCampo;
    private String mensagem;

    @Override
    public void initialize(final CamposCoincidentes anotacao) {
        primeiroCampo = anotacao.campo();
        segundoCampo = anotacao.confirmacao();
        mensagem = anotacao.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            if (!BeanUtils.getProperty(value, primeiroCampo).equals(BeanUtils.getProperty(value, segundoCampo))){
                context.buildConstraintViolationWithTemplate(mensagem)
                        .addPropertyNode(primeiroCampo)
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(mensagem)
                        .addPropertyNode(segundoCampo)
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                return false;
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

}