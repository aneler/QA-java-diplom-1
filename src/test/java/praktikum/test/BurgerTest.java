package praktikum.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.Database;
import praktikum.IngredientType;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Database data = new Database();

    @Test
    public void setBunsTest() {
        Bun bun = data.getRandomBun();
        Burger burger = new Burger();
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Ingredient ingredient = data.getRandomIngredient();
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.ingredients.contains(ingredient));
    }

    //сделать параметризованный? добавить несколько и проверить удаление и разной позиции?
    @Test
    public void removeIngredientTest(){
        Ingredient ingredient = data.getRandomIngredient();
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertEquals(0, burger.ingredients.size());
    }
    @Test
    public void moveIngredientTest(){
        Ingredient firstIngredient = data.getRandomIngredient();
        Ingredient secondIngredient = data.getRandomIngredient();
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        //System.out.println("0" + burger.ingredients.get(0).getName());
        //System.out.println("1" + burger.ingredients.get(1).getName());
        burger.moveIngredient(1, 0);
        //System.out.println("0" + burger.ingredients.get(0).getName());
        //System.out.println("1" + burger.ingredients.get(1).getName());
        Assert.assertEquals(secondIngredient.getName(), burger.ingredients.get(0).getName());
        Assert.assertEquals(firstIngredient.getName(), burger.ingredients.get(1).getName());
    }
    @Test
    public void getPriceTest(){
        Burger burger = new Burger();
        Bun bun = data.getRandomBun();
        Ingredient firstIngredient = data.availableIngredients().get(0);
        Ingredient secondIngredient = data.availableIngredients().get(4);
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        float expectedPrice = bun.getPrice() * 2
                            + firstIngredient.getPrice()
                            + secondIngredient.getPrice();
        Assert.assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }
    @Test
    public void getReceiptTest(){
        Burger burger = new Burger();
        Bun bun = data.getRandomBun();
        Ingredient firstIngredient = data.availableIngredients().get(0);
        Ingredient secondIngredient = data.availableIngredients().get(4);
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                "= %s %s =%n" +
                "= %s %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: %f%n",
                bun.getName(),
                firstIngredient.getType().toString().toLowerCase(),
                firstIngredient.getName(),
                secondIngredient.getType().toString().toLowerCase(),
                secondIngredient.getName(),
                bun.getName(),
                bun.getPrice() * 2 + firstIngredient.getPrice() + secondIngredient.getPrice()
        );
        String actualReceipt = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, actualReceipt);
    }
}
