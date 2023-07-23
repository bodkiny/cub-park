package interfaces;

public interface InstanceLoader {
    String parseManufacturer(String licencePlate);
    String parseModel(String licencePlate);
    int parsePassengersCapacity(String licencePlate);
}
