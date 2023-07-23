package impl;

import interfaces.InstanceLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class CsvInstanceLoader implements InstanceLoader {
    private static final int MANUFACTURER_INDEX = 1;
    private static final int MODEL_INDEX = 2;
    private static final int PASSENGER_CAPACITY_INDEX = 3;

    public CsvInstanceLoader() {
    }

    @Override
    public String parseManufacturer(String licencePlate) {
        return getInfoPart(licencePlate, MANUFACTURER_INDEX);
    }

    @Override
    public String parseModel(String licencePlate) {
        return getInfoPart(licencePlate, MODEL_INDEX);
    }

    @Override
    public int parsePassengersCapacity(String licencePlate) {
        return Integer.parseInt(Objects.requireNonNull(getInfoPart(licencePlate, PASSENGER_CAPACITY_INDEX)));
    }

    private String getInfoPart(String licencePlate, int indexToExtract) {
        try {
            String carInfo = findCarByLicensePlate(licencePlate);
            if(carInfo != null) {
                return parsePartByIndex(carInfo, indexToExtract);
            }else {
                throw new IllegalArgumentException("Invalid or non-existent license plate number: \"" + licencePlate + "\"");
            }
        }catch (IOException exception){
            System.err.println(exception.getStackTrace());
        }
        return null;
    }

    public static String findCarByLicensePlate(String licensePlate) throws IOException {
        String filePath = "csv-sources/cub-park.csv";
        String result = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] parts = line.split(",");
                String currentLicensePlate = parts[0];

                if (currentLicensePlate.equals(licensePlate)) {
                    result = line;
                    break;
                }
            }
        }

        return result;
    }

    private String parsePartByIndex(String line, int index){
        return line.split(",")[index];
    }
}
