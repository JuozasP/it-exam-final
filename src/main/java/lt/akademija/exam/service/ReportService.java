package lt.akademija.exam.service;

import lt.akademija.exam.client.Client;
import lt.akademija.exam.client.ClientRepository;
import lt.akademija.exam.inventory.InventoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class to perform various
 *
 * Created by Juozas on 2017-06-22.
 */
@Service
public class ReportService {

    @Autowired
    private ClientRepository clientRepository;

    public Map<Client, Double> findFiveClientsWithLargestSumWeight(){
        Map<Client, Double> mapClientWeight = new HashMap();
        for(Client client: clientRepository.findAll()){
            Double wholeWeight = 0.0;
            for(InventoryEntity inventoryEntity: client.getInventoryEntity()){
                wholeWeight += inventoryEntity.getWeight();
                mapClientWeight.put(client, wholeWeight);
            }
        }
        Map<Client, Double> sortedMap =
                mapClientWeight.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }

    public Map<Client, Integer> findFiveClientsWithSumIventoryCount()
    {
        Map<Client, Integer> mapClientCount = new HashMap();
        for(Client client: clientRepository.findAll()){
            int count = 0;
            for(InventoryEntity inventoryEntity: client.getInventoryEntity()){
                count += 1;
                mapClientCount.put(client, count);
            }
        }
        Map<Client, Integer> sortedMap =
                mapClientCount.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }

    public Map<Client, Integer> findFiveClientsWithLargestCount(){
        Map<Client, Integer> mapClientCount = new HashMap();
        for(Client client: clientRepository.findAll()){
            int count = 0;
            for(InventoryEntity inventoryEntity: client.getInventoryEntity()){
                if(client.getInventoryEntity().contains(inventoryEntity)){
                    count += 1;
                }
                mapClientCount.put(client, count);
            }
        }
        Map<Client, Integer> sortedMap =
                mapClientCount.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }

    public Map<Client, Double> findFiveClientsWithLargestWeight(){
        Map<Client, Double> mapClientWeight = new HashMap();
        for(Client client: clientRepository.findAll()){
            Double maxWeight = 0.0;
            for(InventoryEntity inventoryEntity: client.getInventoryEntity()){
                if(maxWeight>inventoryEntity.getWeight()){
                    mapClientWeight.put(client, maxWeight);
                }
            }
        }
        Map<Client, Double> sortedMap =
                mapClientWeight.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }

    public Integer findClientCount(Client client){
        if(findFiveClientsWithSumIventoryCount().containsKey(client)){
            return findFiveClientsWithSumIventoryCount().get(client);
        }
        return 0;
    }

}
