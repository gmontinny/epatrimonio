package br.gov.mt.saude.cuiaba.e_patrimonio.validation;

import android.util.Log;

import com.mobsandgeeks.saripaar.ContextualAnnotationRule;
import com.mobsandgeeks.saripaar.ValidationContext;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

import br.gov.mt.saude.cuiaba.e_patrimonio.annotation.CpfCnpj;


public class CpfCnpjRule extends ContextualAnnotationRule<CpfCnpj, String> {
    protected CpfCnpjRule(final ValidationContext validationContext, final CpfCnpj cpfCnpj) {
        super(validationContext, cpfCnpj);
    }

    @Override
    public boolean isValid(String data) {
        boolean stringIsEmpty = data == null || data.isEmpty();
        if (mRuleAnnotation.isRequired() && stringIsEmpty)
            return false;
        else if (!mRuleAnnotation.isRequired() && stringIsEmpty)
            return true;
        return IsValidCNPJ(data) || IsValidCPF(data);
    }

    private static boolean IsValidCNPJ(String cnpj) {
        return IsValid(cnpj, new Integer[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, 14);
    }

    private static boolean IsValidCPF(String cpf) {
        return IsValid(cpf, new Integer[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2}, 11);
    }

    private static boolean IsValid(String cpfCnpj, Integer[] mult, int length) {
        cpfCnpj = cpfCnpj.replace(".","");
        cpfCnpj = cpfCnpj.replace("-","");
        cpfCnpj = cpfCnpj.replace("/","");

        if (cpfCnpj.length() != length || cpfCnpj.equals("11111111111"))
            return false;

        String cpnjWithoutCheckDigit = cpfCnpj.substring(0, length - 2);
        List<Integer> ints = Arrays.asList(mult);
        int sum = Sum(cpnjWithoutCheckDigit, ints.subList(1, ints.size()));

        Integer rest = sum % 11;
        rest = rest < 2 ? 0 : 11 - rest;

        String digits = rest.toString();
        cpnjWithoutCheckDigit += digits;
        sum = Sum(cpnjWithoutCheckDigit, ints);

        rest = sum % 11;
        rest = rest < 2 ? 0 : 11 - rest;
        digits += rest.toString();
        return cpfCnpj.endsWith(digits);
    }

    private static int Sum(String cpnjWithoutCheckDigit, List<Integer> mult) {
        int sum = 0;
        List<Integer> ints = Lists.transform(Chars.asList(cpnjWithoutCheckDigit.toCharArray()), new Function<Character, Integer>() {
            @Override
            public Integer apply(Character input) {
                return Integer.parseInt(input.toString());
            }
        });
        for (int i = 0; i < ints.size(); i++) sum += ints.get(i) * mult.get(i);
        return sum;
    }
}

