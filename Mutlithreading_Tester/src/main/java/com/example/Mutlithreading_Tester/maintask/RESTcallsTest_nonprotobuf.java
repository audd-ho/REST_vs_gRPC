package com.example.Mutlithreading_Tester.maintask;

import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.service.REST_related.non_protobuf_related.PeopleImageService;
import com.example.Mutlithreading_Tester.service.REST_related.non_protobuf_related.PeopleInfoService;
import com.example.Mutlithreading_Tester.service.REST_related.non_protobuf_related.PeopleService;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RESTcallsTest_nonprotobuf implements CommandLineRunner {
    private final PeopleService peopleService;
    //public RESTcallsTest(PeopleService peopleService) {this.peopleService = peopleService;};

    private final PeopleImageService peopleImageService;
    //public RESTcallsTest(PeopleImageService peopleImageService) {this.peopleImageService = peopleImageService;};

    private final PeopleInfoService peopleInfoService;
    //public RESTcallsTest(peopleService peopleService) {this.peopleService = peopleService;};

    public RESTcallsTest_nonprotobuf(PeopleService peopleService, PeopleInfoService peopleInfoService, PeopleImageService peopleImageService) {this.peopleService = peopleService; this.peopleInfoService = peopleInfoService; this.peopleImageService = peopleImageService;};

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


    public void testcall1 () {
        //System.out.println(peopleService.nationality(Nations.SG));
        //System.out.println(peopleService.peopleEmail(Nations.SG));

        //System.out.println(peopleService.nationality(Nations.SG));
        //System.out.println(peopleService.peopleEmail(Nations.SG));

        //System.out.println(peopleService.nationalityJSON(Nations.SG));
        //System.out.println(peopleService.peopleEmailJSON(Nations.SG));

        //System.out.println(peopleService.nationalityJSON(Nations.SG));
        //System.out.println(peopleService.peopleEmailJSON(Nations.SG));

    }
    public void println_compareVScurl() {
        System.out.println("Running compareVScurl");

        long time0 = System.currentTimeMillis();
        System.out.println(peopleService.nationality(Nations.SG));
        System.out.println(peopleService.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        System.out.println(peopleService.peopleEmail(Nations.SG));
        for (Nations nat : Nations.values()) {
            System.out.println(peopleService.nationality(nat));
        }
        for (Nations nat : Nations.values()) {
            System.out.println(peopleService.peopleEmail(nat));
        }
        System.out.println(peopleService.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("println_compareVScurl: " + time_taken);
    }

    public void println_compareVScurlJSON() {
        System.out.println("Running compareVScurlJSON");

        long time0 = System.currentTimeMillis();
        System.out.println(peopleService.nationalityJSON(Nations.SG));
        System.out.println(peopleService.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        System.out.println(peopleService.peopleEmailJSON(Nations.SG));
        for (Nations nat : Nations.values()) {
            System.out.println(peopleService.nationalityJSON(nat));
        }
        for (Nations nat : Nations.values()) {
            System.out.println(peopleService.peopleEmailJSON(nat));
        }
        System.out.println(peopleService.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("println_compareVScurlJSON: " + time_taken);
    }


    public void compareVScurl() {
        System.out.println("Running compareVScurl");

        long time0 = System.currentTimeMillis();
        peopleService.nationality(Nations.SG);
        peopleService.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        peopleService.peopleEmail(Nations.SG);
        for (Nations nat : Nations.values()) {
            peopleService.nationality(nat);
        }
        for (Nations nat : Nations.values()) {
            peopleService.peopleEmail(nat);
        }
        peopleService.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("compareVScurl: " + time_taken);
    }

    public void compareVScurlJSON() {
        System.out.println("Running compareVScurlJSON");

        long time0 = System.currentTimeMillis();
        peopleService.nationalityJSON(Nations.SG);
        peopleService.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        peopleService.peopleEmailJSON(Nations.SG);
        for (Nations nat : Nations.values()) {
            peopleService.nationalityJSON(nat);
        }
        for (Nations nat : Nations.values()) {
            peopleService.peopleEmailJSON(nat);
        }
        peopleService.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("compareVScurlJSON: " + time_taken);
    }

    @Override
    public void run(String... args) throws Exception {
        //if (true) { return;}

        //testcall1();

        //compareVScurl();
        //compareVScurlJSON();



        //println_compareVScurl();
        //println_compareVScurlJSON();

        System.out.println("Pre-actual, Caching - START");
        GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
        Println_GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
        GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
        Println_GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
        GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
        Println_GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
        System.out.println("Pre-actual, Caching - END");
        
        //
        
        // non_protobuf_related

        System.out.println("peopleService (not all json)");
        GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
        Println_GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
        System.out.println("END");

        System.out.println("peopleService (JSON)");
        GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
        Println_GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
        System.out.println("END");

        System.out.println("peopleImageService (JSON)");
        GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
        Println_GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
        System.out.println("END");

    }
}
