package com.example.Multithreading_Tester.maintask;

import com.example.Multithreading_Tester.entity.Person;
import com.example.Multithreading_Tester.enums.Nations;
import com.example.Multithreading_Tester.service.dto.request.PersonRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

@Component
public class ConcurrencyCallsTest {

    private final ExecutorService executorService;
    public ConcurrencyCallsTest() {
        this.executorService = Executors.newFixedThreadPool(20);
    }

    public <NationalityResponse,PersonResponse,PeopleemailResponse> void GeneralTesting(Function<Nations,NationalityResponse> nationality, Function<PersonRequestDTO,PersonResponse> person, Function<Nations,PeopleemailResponse> peopleemail) {
        System.out.println("Running GeneralTesting Concurrently");

        List<Future<NationalityResponse>> nationalityResponses = new ArrayList<>();
        List<Future<PersonResponse>> personResponses = new ArrayList<>();
        List<Future<PeopleemailResponse>> peopleemailResponses = new ArrayList<>();

        long time0 = System.currentTimeMillis();

        nationalityResponses.add(this.executorService.submit(() -> nationality.apply(Nations.SG)));
        personResponses.add(this.executorService.submit(() -> person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US))));
        peopleemailResponses.add(this.executorService.submit(() -> peopleemail.apply(Nations.SG)));
        for (Nations nat : Nations.values()) {
            nationalityResponses.add(this.executorService.submit(() -> nationality.apply(nat)));
        }
        for (Nations nat : Nations.values()) {
            peopleemailResponses.add(this.executorService.submit(() -> peopleemail.apply(nat)));
        }
        personResponses.add(this.executorService.submit(() -> person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US))));

        for (Future<NationalityResponse> futureNatRes : nationalityResponses) {
            try {
                futureNatRes.get();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        for (Future<PersonResponse> futurePerRes : personResponses) {
            try {
                futurePerRes.get();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        for (Future<PeopleemailResponse> futurePeoRes : peopleemailResponses) {
            try {
                futurePeoRes.get();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("GeneralTesting Concurrently: " + time_taken);
    }

    public <NationalityResponse,PersonResponse,PeopleemailResponse> void Println_GeneralTesting(Function<Nations,NationalityResponse> nationality, Function<PersonRequestDTO,PersonResponse> person, Function<Nations,PeopleemailResponse> peopleemail) {
        System.out.println("Running Println_GeneralTesting Concurrently");

        List<Future<NationalityResponse>> nationalityResponses = new ArrayList<>();
        List<Future<PersonResponse>> personResponses = new ArrayList<>();
        List<Future<PeopleemailResponse>> peopleemailResponses = new ArrayList<>();

        long time0 = System.currentTimeMillis();

        nationalityResponses.add(this.executorService.submit(() -> nationality.apply(Nations.SG)));
        personResponses.add(this.executorService.submit(() -> person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US))));
        peopleemailResponses.add(this.executorService.submit(() -> peopleemail.apply(Nations.SG)));
        for (Nations nat : Nations.values()) {
            nationalityResponses.add(this.executorService.submit(() -> nationality.apply(nat)));
        }
        for (Nations nat : Nations.values()) {
            peopleemailResponses.add(this.executorService.submit(() -> peopleemail.apply(nat)));
        }
        personResponses.add(this.executorService.submit(() -> person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US))));

        for (Future<NationalityResponse> futureNatRes : nationalityResponses) {
            try {
                System.out.println(futureNatRes.get());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        for (Future<PersonResponse> futurePerRes : personResponses) {
            try {
                System.out.println(futurePerRes.get());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        for (Future<PeopleemailResponse> futurePeoRes : peopleemailResponses) {
            try {
                System.out.println(futurePeoRes.get());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("Println_GeneralTesting Concurrently: " + time_taken);
    }


}
