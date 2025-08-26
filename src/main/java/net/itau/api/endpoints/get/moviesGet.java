package net.itau.api.endpoints.get;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class moviesGet {

    @GetMapping("/movies")
    public void returnMovies(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
