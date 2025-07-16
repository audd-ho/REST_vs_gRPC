package com.example.Multithreading_Tester.maintask;

import com.example.Multithreading_Tester.enums.Nations;
import com.example.Multithreading_Tester.service.dto.request.PersonRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

@Component
public class ConcurrencyCallsTest {

    private final ExecutorService executorService;
    public ConcurrencyCallsTest() {
        this.executorService = Executors.newFixedThreadPool(20);
    }

    public <NationalityResponse,PersonResponse,PeopleemailResponse> void GeneralTesting(Function<Nations,NationalityResponse> nationality, Function<PersonRequestDTO,PersonResponse> person, Function<Nations,PeopleemailResponse> peopleemail) {
        System.out.println("Running GeneralTesting Concurrently");

        List<CompletableFuture<NationalityResponse>> nationalityResponses = new ArrayList<>();
        List<CompletableFuture<PersonResponse>> personResponses = new ArrayList<>();
        List<CompletableFuture<PeopleemailResponse>> peopleemailResponses = new ArrayList<>();

        long time0 = System.currentTimeMillis();

        nationalityResponses.add(CompletableFuture.supplyAsync(() -> nationality.apply(Nations.SG), this.executorService));
        personResponses.add(CompletableFuture.supplyAsync(() -> person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)), this.executorService));
        peopleemailResponses.add(CompletableFuture.supplyAsync(() -> peopleemail.apply(Nations.SG), this.executorService));
        for (Nations nat : Nations.values()) {
            nationalityResponses.add(CompletableFuture.supplyAsync(() -> nationality.apply(nat), this.executorService));
        }
        for (Nations nat : Nations.values()) {
            peopleemailResponses.add(CompletableFuture.supplyAsync(() -> peopleemail.apply(nat), this.executorService));
        }
        personResponses.add(CompletableFuture.supplyAsync(() -> person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)), this.executorService));

        // type inference using var, still statically typed but inferred during compile time here using "var"
        var nationalityDone = CompletableFuture.allOf(nationalityResponses.toArray(new CompletableFuture[0]));
        var personDone = CompletableFuture.allOf(personResponses.toArray(new CompletableFuture[0]));
        var peopleemailDone = CompletableFuture.allOf(peopleemailResponses.toArray(new CompletableFuture[0]));

        try {
            CompletableFuture.allOf(nationalityDone, personDone, peopleemailDone).join();
        } catch (CompletionException e) {
            Throwable cause = e.getCause();
            System.out.println(cause.getMessage());
            System.exit(1);
        }

        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("GeneralTesting Concurrently: " + time_taken);
    }

    public <T> T Println_SideEffect(T result) {
        System.out.println(result);
        return result;
    }

    public <NationalityResponse,PersonResponse,PeopleemailResponse> void Println_GeneralTesting(Function<Nations,NationalityResponse> nationality, Function<PersonRequestDTO,PersonResponse> person, Function<Nations,PeopleemailResponse> peopleemail) {
        System.out.println("Running Println_GeneralTesting Concurrently");

        List<CompletableFuture<NationalityResponse>> nationalityResponses = new ArrayList<>();
        List<CompletableFuture<PersonResponse>> personResponses = new ArrayList<>();
        List<CompletableFuture<PeopleemailResponse>> peopleemailResponses = new ArrayList<>();

        long time0 = System.currentTimeMillis();

        nationalityResponses.add(CompletableFuture.supplyAsync(() -> nationality.apply(Nations.SG), this.executorService)
                .thenApply(this::Println_SideEffect));
        personResponses.add(CompletableFuture.supplyAsync(() -> person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)), this.executorService)
                .thenApply(this::Println_SideEffect));
        peopleemailResponses.add(CompletableFuture.supplyAsync(() -> peopleemail.apply(Nations.SG), this.executorService)
                .thenApply(this::Println_SideEffect));
        for (Nations nat : Nations.values()) {
            nationalityResponses.add(CompletableFuture.supplyAsync(() -> nationality.apply(nat), this.executorService)
                    .thenApply(this::Println_SideEffect));
        }
        for (Nations nat : Nations.values()) {
            peopleemailResponses.add(CompletableFuture.supplyAsync(() -> peopleemail.apply(nat), this.executorService)
                    .thenApply(this::Println_SideEffect));
        }
        personResponses.add(CompletableFuture.supplyAsync(() -> person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)), this.executorService)
                .thenApply(this::Println_SideEffect));

        CompletableFuture<Void> nationalityDone = CompletableFuture.allOf(nationalityResponses.toArray(new CompletableFuture[0]));
        CompletableFuture<Void> personDone = CompletableFuture.allOf(personResponses.toArray(new CompletableFuture[0]));
        CompletableFuture<Void> peopleemailDone = CompletableFuture.allOf(peopleemailResponses.toArray(new CompletableFuture[0]));

        try {
            CompletableFuture.allOf(nationalityDone, personDone, peopleemailDone).join();
        } catch (CompletionException e) {
            Throwable cause = e.getCause();
            System.out.println(cause.getMessage());
            System.exit(1);
        }

        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("Println_GeneralTesting Concurrently: " + time_taken);
    }


}
