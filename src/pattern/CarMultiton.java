package pattern;

import impl.CsvInstanceLoader;
import interfaces.InstanceLoader;

import java.util.HashMap;

public final class CarMultiton {
    private static final HashMap<String, CarMultiton> INSTANCES = new HashMap<>();
    private final String licencePlate;
    private final String manufacturer;
    private final String model;
    private final int passengerCapacity;
    private static final InstanceLoader instanceLoader = new CsvInstanceLoader();

    private CarMultiton(String licencePlate, String manufacturer, String model, int passengerCapacity) {
        this.licencePlate = licencePlate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.passengerCapacity = passengerCapacity;
    }

    public static CarMultiton getInstance(String licencePlate) {
        if (!INSTANCES.containsKey(licencePlate)) {
            String manufacturer = instanceLoader.parseManufacturer(licencePlate);
            String model = instanceLoader.parseModel(licencePlate);
            int passengerCapacity = instanceLoader.parsePassengersCapacity(licencePlate);
            INSTANCES.put(licencePlate, new CarMultiton(licencePlate, manufacturer, model, passengerCapacity));
        }

        return INSTANCES.get(licencePlate);
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarMultiton{");
        sb.append("licencePlate='").append(licencePlate).append('\'');
        sb.append(", manufacturer='").append(manufacturer).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", passengerCapacity=").append(passengerCapacity);
        sb.append('}');
        return sb.toString();
    }
}
