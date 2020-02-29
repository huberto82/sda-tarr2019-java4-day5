package cities;

import java.util.Objects;

public class City implements Comparable<City>{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId() &&
                Double.compare(city.getLatitude(), getLatitude()) == 0 &&
                Double.compare(city.getLongitude(), getLongitude()) == 0 &&
                getPopulation() == city.getPopulation() &&
                Objects.equals(getName(), city.getName()) &&
                Objects.equals(getCountryCode(), city.getCountryCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLatitude(), getLongitude(), getCountryCode(), getPopulation());
    }

    @Override
    public int compareTo(City o) {
        if (name.compareTo(o.name) == 0){
            return Integer.compare(population, o.population);
        }
        return name.compareTo(o.name);
    }


}
