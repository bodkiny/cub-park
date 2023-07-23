package runner;

import pattern.CarMultiton;

public class CarMultitonRunner {
    public static void main(String[] args) {
        CarMultiton car1 = CarMultiton.getInstance("MNO345");
        System.out.println("Successfully created an instance of a car: " + car1);
        CarMultiton car2 = CarMultiton.getInstance("BDC891");
        System.out.println("Successfully created an instance of a car: " + car2);
        CarMultiton car3 = CarMultiton.getInstance("LSA802");
        System.out.println("Successfully created an instance of a car: " + car3);
        CarMultiton car4 = CarMultiton.getInstance("MNO346");
        System.out.println("Successfully created an instance of a car: " + car4);

    }
}
