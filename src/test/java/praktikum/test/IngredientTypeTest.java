package praktikum.test;

import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {
    @Test
    public void valueOfSauce(){
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }
    @Test
    public void valueOfFilling(){
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIngredientTypeValueOfInvalid() {
        IngredientType.valueOf("INVALID");
    }
}
