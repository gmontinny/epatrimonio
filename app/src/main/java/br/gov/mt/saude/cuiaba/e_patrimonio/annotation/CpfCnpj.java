package br.gov.mt.saude.cuiaba.e_patrimonio.annotation;

import com.mobsandgeeks.saripaar.annotation.ValidateUsing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.gov.mt.saude.cuiaba.e_patrimonio.validation.CpfCnpjRule;


@ValidateUsing(CpfCnpjRule.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CpfCnpj {
    boolean isRequired() default false;

    String emptyText() default "";

    int emptyTextResId() default -1;

    int sequence() default -1;

    int messageResId() default -1;

    String message() default "Cpf/Cnpj inválido";
}
