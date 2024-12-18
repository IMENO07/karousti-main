package edu.usdb.cs.karousti.testsession;

import org.springframework.stereotype.Service;

import edu.usdb.cs.karousti.testsession.requests.TestCreationRequest;
import edu.usdb.cs.karousti.testsession.responses.TestCreationResponse;

@Service
public class TestService {

    private TestRepository testRepository;

    public TestCreationResponse createCar(TestCreationRequest request) {
        var test = Test.builder()
                .date(request.getDate())
                .time(request.getTime())
                .build();

        testRepository.save(test);

        return null;
    }

}
