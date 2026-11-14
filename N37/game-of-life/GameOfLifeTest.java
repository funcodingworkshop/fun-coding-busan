public class GameOfLifeTest {
    public static void main(String[] args) {
        // test_underpopln();
        // test_survaval();
        // test_overpop();
        // test_random();
    }

    static void test_underpopln(){
        Cell temp_cell = new Cell();
        temp_cell.update_alive(1);
        testIsEqual("Underpopulation", temp_cell.is_alive, false);
    }

    static void test_survaval(){
        Cell temp_cell = new Cell();
        temp_cell.update_alive(2);
        testIsEqual("Survival2", temp_cell.is_alive, true);
        temp_cell.update_alive(3);
        testIsEqual("Survival3", temp_cell.is_alive, true);
    }

    static void test_overpop(){
        Cell temp_cell = new Cell();
        temp_cell.update_alive(4);
        testIsEqual("Overpop", temp_cell.is_alive, false);
    }

    static void testIsEqual(String test_name, boolean current, boolean expected) {
        System.out.println(test_name + " - " + (current == expected ? "ok" : "FAIL"));
    }

    static void seedTheBoard() {

    }

    static void test_random() {
        UtilClass ran = new UtilClass();
        for (int i=0; i<100; i++) {
            boolean r = ran.rand();
            testIsEqual("test_random " + i, r, true);
        }
    }
 }
