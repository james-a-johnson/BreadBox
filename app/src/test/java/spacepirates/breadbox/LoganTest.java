package spacepirates.breadbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.DonationItemDatabase;

import static org.junit.Assert.*;

public class LoganTest {
    @Test
    public void getItemsByCategory_isCorrect() {
        DonationItemDatabase dd = new DonationItemDatabase();
        List<DonationItem> d = new ArrayList<>();
        DonationItem item = new DonationItem();
        item.setName("Shirt");
        item.setCategory(Category.APPAREL);
        d.add(item);
        item = new DonationItem();
        item.setName("Shorts");
        item.setCategory(Category.APPAREL);
        d.add(item);
        item = new DonationItem();
        item.setName("TV");
        item.setCategory(Category.ELECTRONICS);
        d.add(item);
        item = new DonationItem();
        item.setName("Whisk");
        item.setCategory(Category.KITCHENWARE);
        Queue<DonationItem> filter = dd.getItemsByCategory(d, Category.APPAREL);
        assertEquals(filter.poll().getName(), "Shirt");
        assertEquals(filter.poll().getName(), "Shorts");
        assertTrue(filter.isEmpty());
    }
}
