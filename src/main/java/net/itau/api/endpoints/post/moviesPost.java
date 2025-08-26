package net.itau.api.endpoints.post;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class moviesPost {
        @PostMapping("/movies")
        public void returnMovies(HttpServletResponse response){
            response.setStatus(HttpServletResponse.SC_OK);
        }
}
