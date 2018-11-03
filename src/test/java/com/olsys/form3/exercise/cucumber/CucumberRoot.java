package com.olsys.form3.exercise.cucumber;

import com.olsys.form3.exercise.ExerciseApplication;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExerciseApplication.class, webEnvironment = DEFINED_PORT)
@ContextConfiguration
@Ignore
public class CucumberRoot {

    private static final HttpClient HTTP_CLIENT = HttpClientBuilder.create().build();

    @Autowired protected TestRestTemplate restTemplate;

    public CucumberRoot() {

    }
}
