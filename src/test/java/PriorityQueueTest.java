
import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{5, 4, 2, 3}, new int[]{2, 3, 4, 5}),
                Arguments.of(new int[]{10, 100000, 1000, 0, 1}, new int[]{0, 1, 10, 1000, 100000}),
                Arguments.of(new int[]{336, 214, Integer.MAX_VALUE, Integer.MIN_VALUE}, new int[]{ Integer.MIN_VALUE, 214, 336, Integer.MAX_VALUE}),
                Arguments.of(new int[]{0, 0, 0}, new int[]{0, 0, 0}),
                Arguments.of(new int[]{-165115454, -45, -1, -50}, new int[]{-165115454, -50, -45, -1})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        Integer s;
        int[] result = new int[random_array.length];

        for (int element : random_array)
            test.add(element);

        for (int index = 0; index < result.length; index++) {
            s = test.poll();
            result[index] = s;
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExceptionThrown_thenOfferEisNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<>();
            test.add(null);
        });
    }

    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(-1);
        });
    }
    //
    @Test
    public void whenExceptionThrown_thenNoElementCanRemove() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            List<Integer> l = null;
            test.removeAll(l);
        });
    }
}
