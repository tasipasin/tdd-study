
package com.tasi.tdd.translator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TranslatorTest {

    private Translator translator;

    @Before
    public void before() {
        this.translator = new Translator();
    }

    @Test
    public void tc001_translateWordless() {
        Assert.assertTrue(this.translator.isEmpty());
    }

    @Test
    public void tc002_oneTranslation() {
        this.translator.addTranslation("cake", "bolo");
        Assert.assertFalse(this.translator.isEmpty());
        Assert.assertEquals("bolo", this.translator.translate("cake"));
    }

    @Test
    public void tc003_twoTranslations() {
        this.translator.addTranslation("corn", "milho");
        this.translator.addTranslation("food", "comida");
        Assert.assertFalse(this.translator.isEmpty());
        Assert.assertEquals("comida", this.translator.translate("food"));
        Assert.assertEquals("milho", this.translator.translate("corn"));
    }

    @Test
    public void tc004_twoTranslationsSameWord() {
        this.translator.addTranslation("play", "jogar");
        this.translator.addTranslation("play", "tocar");
        Assert.assertFalse(this.translator.isEmpty());
        Assert.assertEquals("jogar, tocar", this.translator.translate("play"));
    }

    @Test
    public void tc005_translatePhrase() {
        this.translator.addTranslation("i", "eu");
        this.translator.addTranslation("like", "gosto");
        this.translator.addTranslation("to", "de");
        this.translator.addTranslation("play", "jogar");
        Assert.assertEquals("eu gosto de jogar", this.translator.translatePhrase("i like to play"));
    }

    @Test
    public void tc006_translatePhraseSameWord() {
        this.translator.addTranslation("i", "eu");
        this.translator.addTranslation("like", "gosto");
        this.translator.addTranslation("to", "de");
        this.translator.addTranslation("play", "jogar");
        this.translator.addTranslation("play", "tocar");
        Assert.assertEquals("eu gosto de jogar", this.translator.translatePhrase("i like to play"));
    }
}
