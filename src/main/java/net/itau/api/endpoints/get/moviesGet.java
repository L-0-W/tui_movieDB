package net.itau.api.endpoints.get;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.movies;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class moviesGet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/movies")
    public ResponseEntity<List<Map<String, Object>>> returnFullMovies(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        ClassPathResource resource = new ClassPathResource("movies.json");
        InputStream input = resource.getInputStream();

        List<Map<String, Object>> data = objectMapper.readValue(input, new TypeReference<>() {
        });

        return ResponseEntity.ok(data);
    }

    @GetMapping("/movies/search")
    public ResponseEntity<Optional<Map<String, Object>>> returnMovie(@RequestParam("id") Optional<String> item, @RequestParam("ratings") Optional<String> ratings, HttpServletResponse response) throws IOException {
        System.out.println(item);
        System.out.println(ratings);

        Optional<Map<String, Object>> newData = Optional.empty();

        if (item.isPresent()) {
            ClassPathResource resource = new ClassPathResource("movies.json");
            InputStream input = resource.getInputStream();

            List<Map<String, Object>> data = objectMapper.readValue(input, new TypeReference<>() {
            });

            newData = data.stream().filter(map -> item.equals(map.get("id"))).findFirst();
        }

        if (ratings.isPresent()){

            ClassPathResource resource = new ClassPathResource("movies.json");
            InputStream input = resource.getInputStream();

            List<Map<String, Object>> data = objectMapper.readValue(input, new TypeReference<>() {
            });

            newData = data.stream().filter(map -> ratings.equals(map.get("rating"))).findFirst();
        }


        if (newData.isPresent()) {
            System.out.println(newData);
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseEntity.ok(newData);
        }

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return null;
    }

}
