
package com.tasi.tdd;

import com.tasi.tdd.exception.EmptyInputException;
import com.tasi.tdd.exception.SpecialCharacterException;
import com.tasi.tdd.exception.WrongFormatException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CamelCaseTest {

    private static void runsBasicTest(String original, String... expectedOutputs) {
        List<String> result = CamelCase.convertCamelCase(original);
        Assert.assertTrue(!result.isEmpty());
        Assert.assertEquals(expectedOutputs.length, result.size());
        for (int index = 0; index < expectedOutputs.length; index++) {
            Assert.assertEquals(expectedOutputs[index], result.get(index));
        }
    }

    @Test
    public void tc01_simpleStringDoNothing() {
        CamelCaseTest.runsBasicTest("nome", "nome");
    }

    @Test
    public void tc02_simpleStringBeginsUpperCase() {
        CamelCaseTest.runsBasicTest("Nome", "nome");
    }

    @Test
    public void tc03_simpleCamelCase() {
        CamelCaseTest.runsBasicTest("nomeComposto", "nome", "composto");
    }

    @Test
    public void tc04_simpleCamelCaseBeginsUpperCase() {
        CamelCaseTest.runsBasicTest("NomeComposto", "nome", "composto");
    }

    @Test
    public void tc05_simpleStringAcronym() {
        CamelCaseTest.runsBasicTest("CPF", "CPF");
    }

    @Test
    public void tc06_camelCaseWithAcronym() {
        CamelCaseTest.runsBasicTest("numeroCPF", "numero", "CPF");
    }

    @Test
    public void tc07_camelCaseWithAcronymAndAfter() {
        CamelCaseTest.runsBasicTest("numeroCPFContribuinte", "numero", "CPF", "contribuinte");
    }

    @Test
    public void tc08_camelCaseWithNumberInTheMiddle() {
        CamelCaseTest.runsBasicTest("recupera10Primeiros", "recupera", "10", "primeiros");
    }

    @Test(expected = EmptyInputException.class)
    public void tc09_nullInput() {
        CamelCase.convertCamelCase(null);
    }

    @Test(expected = EmptyInputException.class)
    public void tc10_emptyInput() {
        CamelCase.convertCamelCase("");
    }

    @Test(expected = WrongFormatException.class)
    public void tc11_beginsWithNumber() {
        CamelCase.convertCamelCase("10Primeiros");
    }

    @Test(expected = SpecialCharacterException.class)
    public void tc12_specialCharacters() {
        CamelCase.convertCamelCase("nome#Composto");
    }
}
