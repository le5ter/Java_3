package ComparisonTests;

import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import Comparison.Comparison;
import static org.junit.Assert.*;

public class ComparisonTests {

    @Test
    public void testTime() {
        Long time = Comparison.time(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        assertTrue(time >= 1000);
    }

    @Test
    public void testAdd() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Comparison.testAdd(arrayList, 1000);
        assertEquals(1000, arrayList.size());
    }

    @Test
    public void testDelete() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            linkedList.add(i);
        }
        Comparison.testDelete(linkedList, 500);
        assertEquals(500, linkedList.size());
    }
}