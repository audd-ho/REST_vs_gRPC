package com.example.Multithreading_Tester.maintask;

import com.example.Multithreading_Tester.enums.Nations;
import com.example.Multithreading_Tester.service.REST_related.non_protobuf_related.PeopleImageService;
import com.example.Multithreading_Tester.service.REST_related.non_protobuf_related.PeopleService;
import com.example.Multithreading_Tester.service.dto.request.PersonRequestDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RESTcallsTest_nonprotobuf implements CommandLineRunner {

    private final PeopleService peopleService;
    private final PeopleImageService peopleImageService;

    public RESTcallsTest_nonprotobuf(PeopleService peopleService, PeopleImageService peopleImageService) {this.peopleService = peopleService; this.peopleImageService = peopleImageService;};

    public void GeneralTesting(Function<Nations,?> nationality, Function<PersonRequestDTO,?> person, Function<Nations,?> peopleemail) {
        System.out.println("Running GeneralTesting");

        long time0 = System.currentTimeMillis();
        nationality.apply(Nations.SG);
        person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        peopleemail.apply(Nations.SG);
        for (Nations nat : Nations.values()) {
            nationality.apply(nat);
        }
        for (Nations nat : Nations.values()) {
            peopleemail.apply(nat);
        }
        person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("GeneralTesting: " + time_taken);
    }

    public void Println_GeneralTesting(Function<Nations,?> nationality, Function<PersonRequestDTO,?> person, Function<Nations,?> peopleemail) {
        System.out.println("Running Println_GeneralTesting");

        long time0 = System.currentTimeMillis();
        System.out.println(nationality.apply(Nations.SG));
        System.out.println(person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        System.out.println(peopleemail.apply(Nations.SG));
        for (Nations nat : Nations.values()) {
            System.out.println(nationality.apply(nat));
        }
        for (Nations nat : Nations.values()) {
            System.out.println(peopleemail.apply(nat));
        }
        System.out.println(person.apply(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("Println_GeneralTesting: " + time_taken);
    }

    @Override
    public void run(String... args) throws Exception {
        if (true) {return;}
        System.out.println("Pre-actual, Caching - START");
        GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
        //Println_GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
        GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
        //Println_GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
        GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
        //Println_GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
        System.out.println("Pre-actual, Caching - END");
        
        //
        
        // non_protobuf_related
        for (int i = 0; i < 50; i++) {
            System.out.println("peopleService (not all json)");
            GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
            //Println_GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
            System.out.println("END");

            System.out.println("peopleService (JSON)");
            GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
            //Println_GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
            System.out.println("END");

            System.out.println("peopleImageService (JSON)");
            GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
            //Println_GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
            System.out.println("END");
        }
    }
}
