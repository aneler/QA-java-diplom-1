package praktikum.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.Database;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class IngredientTest {
    public IngredientType type;
    public String name;
    public float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();
        Object[][] data = new Object[ingredients.size()][3];

        for (int i = 0; i < ingredients.size(); i++) {
            data[i][0] = ingredients.get(i).getType();
            data[i][1] = ingredients.get(i).getName();
            data[i][2] = ingredients.get(i).getPrice();
        }
        return Arrays.asList(data);
    }
    @Test
    public void ingredientTypeTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(type, ingredient.getType());
    }
    @Test
    public void ingredientNameTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(name, ingredient.getName());
    }
    @Test
    public void ingredientPriceTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(price, ingredient.getPrice(), 0.001);
    }


}
