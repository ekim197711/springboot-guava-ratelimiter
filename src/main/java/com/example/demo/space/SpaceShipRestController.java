package com.example.demo.space;

import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/space")
@RequiredArgsConstructor
@Slf4j
public class SpaceShipRestController {
    private final RateLimiter rateLimiter;

    @GetMapping("/ship")
    public ResponseEntity<SpaceShip> spaceShip(HttpServletRequest request) {
        log.info("The ip is {}", request.getRemoteAddr());
        boolean oktogo = rateLimiter.tryAcquire();
        if (oktogo) {
            return new ResponseEntity<SpaceShip>(
                    new SpaceShip("round", 55),
                    HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping("/ship/wait")
    public SpaceShip spaceShipWithWait() {
        rateLimiter.acquire();
        return new SpaceShip("round", 55);
    }
}
