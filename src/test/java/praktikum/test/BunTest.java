package praktikum.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Database;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class BunTest {
    private String name;
    private float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        Object[][] data = new Object[buns.size()][2];

        for (int i = 0; i < buns.size(); i++) {
            data[i][0] = buns.get(i).getName();
            data[i][1] = buns.get(i).getPrice();
        }
        return Arrays.asList(data);
    }

    @Test
    public void bunNameTest(){
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.getName());
    }
    @Test
    public void bunPriceTest(){
        Bun bun = new Bun(name, price);
        Assert.assertEquals(price, bun.getPrice(), 0.001);
    }
}
