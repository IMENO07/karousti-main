package edu.usdb.cs.karousti.testsession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.usdb.cs.karousti.testsession.requests.TestCreationRequest;
import edu.usdb.cs.karousti.testsession.responses.TestCreationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping("add")
    public TestCreationResponse addCar(@RequestBody TestCreationRequest entity) {
        return testService.createCar(entity);
    }

}
