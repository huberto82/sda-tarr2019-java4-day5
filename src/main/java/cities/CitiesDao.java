package cities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CitiesDao implements Dao<City> {
    private List<City> cities;

    public CitiesDao(String pathName){
        cities = new ArrayList<>();
        Path path = Paths.get(pathName);
        try {
            List<String> lines= Files.readAllLines(path);
            cities = lines.stream()
                    .map(line -> {
                        String[] tokens = line.split("\\t");
                        int id = Integer.parseInt(tokens[0]);
                        String name = tokens[1];
                        double latitude = Double.parseDouble(tokens[4]);
                        double longitude = Double.parseDouble(tokens[5]);
                        String countryCode = tokens[8];
                        int population = Integer.parseInt(tokens[14]);
                        City city = new City(id, name, latitude,longitude,countryCode,population);
                        return city;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
        }
    }

    public CitiesDao(){
        cities = Collections.EMPTY_LIST;
    }


    @Override
    public List<City> findAll() {
        return cities;
    }

    @Override
    public Optional<City> findBy(int id) {
        return cities.stream().filter(city -> city.getId() == id).findAny();
    }
}
