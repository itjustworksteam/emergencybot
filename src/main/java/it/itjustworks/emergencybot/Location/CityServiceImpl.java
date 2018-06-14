package it.itjustworks.emergencybot.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

  Map<String, List<City>> cities = new HashMap<String, List<City>>();
  
  public CityServiceImpl() throws UnexpectedInputException, ParseException, Exception{
    
    FlatFileItemReader<City> itemReader = new FlatFileItemReader<City>();
    itemReader.setResource(new FileSystemResource("src/main/resources/cities1000.txt"));
    itemReader.setLinesToSkip(1);
    
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);
    lineTokenizer.setNames(new String[] {"geonameid",
                                         "name",
                                         "asciiname",
                                         "alternatenames",
                                         "latitude",
                                         "longitude",
                                         "feature class",
                                         "feature code",
                                         "country code",
                                         "cc2",
                                         "admin1 code",
                                         "admin2 code",
                                         "admin3 code",
                                         "admin4 code",
                                         "population",
                                         "elevation",
                                         "dem",
                                         "timezone",
                                         "modification date"});
    
    
    DefaultLineMapper<City> lineMapper = new DefaultLineMapper<City>();
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(new CityFieldSetMapper());
    
    itemReader.setLineMapper(lineMapper);
    itemReader.open(new ExecutionContext());
        
    City readCity;
    boolean isException;
    
    do {
      
      readCity = null;
      isException = false;
      
      try { 
        readCity = itemReader.read();
        
        if (readCity != null){
          addtoMap(readCity);
        }
        
      } catch (FlatFileParseException e) {
        isException = true;
      }
      
    } while ( (readCity !=  null) || (isException) ); 
    
    
  }
  
  @Override
  public City findByLatLong(Double latitude, Double longitude){
    return getFromMap(latitude, longitude);
  }

  /* Adds the given city to the hashmap with location based index*/
  private void addtoMap(City city){
    
    String index = Utils.LOCINDEX(city.getLatitude(), city.getLongitude());
    
    List<City> sameIndexCities = cities.get( index );
    
    if(sameIndexCities == null) 
      sameIndexCities = new ArrayList<City>();
    
    //Add the new city into the list of cities sharing the same index.
    sameIndexCities.add(city);
    //Add the list into the hashmap
    cities.put( index, sameIndexCities);
  }
  
  
  /* Gets a city from the hashmap for given latitude and longitude */
  private City getFromMap(Double latitude, Double longitude){
    
    City result = null;
    
    String[] indexes = Utils.INDEXESAROUND(latitude, longitude);
    
    for(String index: indexes){
      
      List<City> citiesForIndex = cities.get( index );
      
      if ( citiesForIndex != null ){
        for (City city: citiesForIndex){
          
          if (result == null)
            result = city;
          else {
             float resultDistance = Utils.getDistance(latitude, longitude, result.getLatitude(), result.getLongitude());
             float cityDistance = Utils.getDistance(latitude, longitude, city.getLatitude(), city.getLongitude());
             
             if ( cityDistance < resultDistance ) //if this city is closer than earlier result
               result = city;
          }
        }
      }   
    }
    
    return result;
  }
  
}
