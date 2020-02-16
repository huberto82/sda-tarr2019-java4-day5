package cities;

public class City {
    private final int id;
    private final String name;
    private final double latitude;
    private final double longitude;
    private final String countryCode;
    private final int population;

    public City(int id, String name, double latitude, double longitude, String countryCode, int population) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryCode = countryCode;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getPopulation() {
        return population;
    }
}
