package tw.com.ispan.service;

import java.net.URI;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {
    private static final String POKE_API_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/";
    private RestTemplate template = new RestTemplate();

    public String pokemon(Integer id) {
        if(id!=null) {
            URI uri = URI.create(POKE_API_ENDPOINT+id);
            RequestEntity<Void> request = RequestEntity.get(uri).build();
            try {
                ResponseEntity<String> response = template.exchange(request, String.class);
                if(response.getStatusCode().is2xxSuccessful()) {
                    return response.getBody();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
