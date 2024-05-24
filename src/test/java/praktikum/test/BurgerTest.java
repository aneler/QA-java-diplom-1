package praktikum.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.Database;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Database data = new Database();
    @Mock
    Bun bunMock;
    @Mock
    Ingredient firstIngredientMock;
    @Mock
    Ingredient secondIngredientMock;


    @Test
    public void setBunsTest(){
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        when(bunMock.getName()).thenReturn(data.availableBuns().get(1).getName());
        when(bunMock.getPrice()).thenReturn(data.availableBuns().get(1).getPrice());
        assertEquals(data.availableBuns().get(1).getName(), burger.bun.getName());
        assertEquals(data.availableBuns().get(1).getPrice(), burger.bun.getPrice(), 0.001);
    }
    @Test
    public void addIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(firstIngredientMock);
        when(firstIngredientMock.getType()).thenReturn(data.availableIngredients().get(2).getType());
        when(firstIngredientMock.getName()).thenReturn(data.availableIngredients().get(2).getName());
        when(firstIngredientMock.getPrice()).thenReturn(data.availableIngredients().get(2).getPrice());
        assertEquals(data.availableIngredients().get(2).getType(), burger.ingredients.get(0).getType());
        assertEquals(data.availableIngredients().get(2).getName(), burger.ingredients.get(0).getName());
        assertEquals(data.availableIngredients().get(2).getPrice(), burger.ingredients.get(0).getPrice(), 0.001);
    }
    @Test
    public void removeIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(firstIngredientMock);

        assertEquals(1, burger.ingredients.size());

        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());

    }
    @Test
    public void moveIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        String firstIngredientName = burger.ingredients.get(0).getName();
        String secondIngredientName = burger.ingredients.get(1).getName();

        assertEquals(2, burger.ingredients.size());
        burger.moveIngredient(0, 1);
        assertEquals(firstIngredientName, burger.ingredients.get(1).getName());
        assertEquals(secondIngredientName, burger.ingredients.get(0).getName());
    }
    @Test
    public void getPriceTest(){
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        float bunPrice = burger.bun.getPrice();
        float firstIngredientPrice = burger.ingredients.get(0).getPrice();
        float secondIngredientPrice = burger.ingredients.get(1).getPrice();

        float expectedBurgerPrice = bunPrice * 2 + firstIngredientPrice + secondIngredientPrice;
        assertEquals(expectedBurgerPrice, burger.getPrice(), 0.001);
    }
    @Test
    public void getReceiptTest(){
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        when(bunMock.getName()).thenReturn(data.availableBuns().get(1).getName());
        when(bunMock.getPrice()).thenReturn(data.availableBuns().get(1).getPrice());
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        when(firstIngredientMock.getName()).thenReturn(data.availableIngredients().get(0).getName());
        when(secondIngredientMock.getName()).thenReturn(data.availableIngredients().get(1).getName());

        when(firstIngredientMock.getPrice()).thenReturn(data.availableIngredients().get(0).getPrice());
        when(secondIngredientMock.getPrice()).thenReturn(data.availableIngredients().get(1).getPrice());

        when(firstIngredientMock.getType()).thenReturn(data.availableIngredients().get(0).getType());
        when(secondIngredientMock.getType()).thenReturn(data.availableIngredients().get(1).getType());

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                "= %s %s =%n" +
                "= %s %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: %f%n",
                burger.bun.getName(),
                burger.ingredients.get(0).getType().toString().toLowerCase(),
                burger.ingredients.get(0).getName(),
                burger.ingredients.get(1).getType().toString().toLowerCase(),
                burger.ingredients.get(1).getName(),
                burger.bun.getName(),
                burger.bun.getPrice() * 2 + burger.ingredients.get(0).getPrice() + burger.ingredients.get(1).getPrice()
        );
        String actualReceipt = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, actualReceipt);
    }
}
