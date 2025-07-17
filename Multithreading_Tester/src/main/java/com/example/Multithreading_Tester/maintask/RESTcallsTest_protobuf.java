package com.example.Multithreading_Tester.maintask;

import com.example.Multithreading_Tester.enums.Nations;
import com.example.Multithreading_Tester.service.REST_related.protobuf_related.PeopleImageBytesProtoService;
import com.example.Multithreading_Tester.service.REST_related.protobuf_related.PeopleImageStringProtoService;
import com.example.Multithreading_Tester.service.REST_related.protobuf_related.PeopleProtoService;
import com.example.Multithreading_Tester.service.dto.request.PersonRequestDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RESTcallsTest_protobuf implements CommandLineRunner {

    private final PeopleProtoService peopleProtoService;
    private final PeopleImageStringProtoService peopleImageStringProtoService;
    private final PeopleImageBytesProtoService peopleImageBytesProtoService;

    public RESTcallsTest_protobuf(PeopleProtoService peopleProtoService, PeopleImageStringProtoService peopleImageStringProtoService, PeopleImageBytesProtoService peopleImageBytesProtoService) {this.peopleProtoService = peopleProtoService; this.peopleImageStringProtoService = peopleImageStringProtoService; this.peopleImageBytesProtoService = peopleImageBytesProtoService;};

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
        GeneralTesting(peopleProtoService::nationalityJSON, peopleProtoService::personJSON, peopleProtoService::peopleEmailJSON);
        //Println_GeneralTesting(peopleProtoService::nationalityJSON, peopleProtoService::personJSON, peopleProtoService::peopleEmailJSON);
        GeneralTesting(peopleProtoService::protoNationality, peopleProtoService::protoPerson, peopleProtoService::protoPeopleEmail);
        //Println_GeneralTesting(peopleProtoService::protoNationality, peopleProtoService::protoPerson, peopleProtoService::protoPeopleEmail);
        GeneralTesting(peopleImageStringProtoService::nationalityImageJSON, peopleImageStringProtoService::personImageJSON, peopleImageStringProtoService::peopleEmailImageJSON);
        //Println_GeneralTesting(peopleImageStringProtoService::nationalityImageJSON, peopleImageStringProtoService::personImageJSON, peopleImageStringProtoService::peopleEmailImageJSON);
        GeneralTesting(peopleImageStringProtoService::protoNationalityImage, peopleImageStringProtoService::protoPersonImage, peopleImageStringProtoService::protoPeopleEmailImage);
        //Println_GeneralTesting(peopleImageStringProtoService::protoNationalityImage, peopleImageStringProtoService::protoPersonImage, peopleImageStringProtoService::protoPeopleEmailImage);
        GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON, peopleImageBytesProtoService::personImageJSON, peopleImageBytesProtoService::peopleEmailImageJSON);
        //Println_GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON, peopleImageBytesProtoService::personImageJSON, peopleImageBytesProtoService::peopleEmailImageJSON);
        GeneralTesting(peopleImageBytesProtoService::protoNationalityImage, peopleImageBytesProtoService::protoPersonImage, peopleImageBytesProtoService::protoPeopleEmailImage);
        //Println_GeneralTesting(peopleImageBytesProtoService::protoNationalityImage, peopleImageBytesProtoService::protoPersonImage, peopleImageBytesProtoService::protoPeopleEmailImage);
        GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON_PIR, peopleImageBytesProtoService::personImageJSON_PIR, peopleImageBytesProtoService::peopleEmailImageJSON_PIR);
        //Println_GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON_PIR, peopleImageBytesProtoService::personImageJSON_PIR, peopleImageBytesProtoService::peopleEmailImageJSON_PIR);
        GeneralTesting(peopleImageBytesProtoService::protoNationalityImage_PIR, peopleImageBytesProtoService::protoPersonImage_PIR, peopleImageBytesProtoService::protoPeopleEmailImage_PIR);
        //Println_GeneralTesting(peopleImageBytesProtoService::protoNationalityImage_PIR, peopleImageBytesProtoService::protoPersonImage_PIR, peopleImageBytesProtoService::protoPeopleEmailImage_PIR);
        System.out.println("Pre-actual, Caching - END");

        //
        
        // protobuf_related
        for (int i = 0; i < 50; i++) {
            System.out.println("peopleProtoService (JSON send)");
            GeneralTesting(peopleProtoService::nationalityJSON, peopleProtoService::personJSON, peopleProtoService::peopleEmailJSON);
            //Println_GeneralTesting(peopleProtoService::nationalityJSON, peopleProtoService::personJSON, peopleProtoService::peopleEmailJSON);
            System.out.println("END");

            System.out.println("peopleProtoService (Full protobuf)");
            GeneralTesting(peopleProtoService::protoNationality, peopleProtoService::protoPerson, peopleProtoService::protoPeopleEmail);
            //Println_GeneralTesting(peopleProtoService::protoNationality, peopleProtoService::protoPerson, peopleProtoService::protoPeopleEmail);
            System.out.println("END");

            System.out.println("peopleImageStringProtoService (JSON send)");
            GeneralTesting(peopleImageStringProtoService::nationalityImageJSON, peopleImageStringProtoService::personImageJSON, peopleImageStringProtoService::peopleEmailImageJSON);
            //Println_GeneralTesting(peopleImageStringProtoService::nationalityImageJSON, peopleImageStringProtoService::personImageJSON, peopleImageStringProtoService::peopleEmailImageJSON);
            System.out.println("END");

            System.out.println("peopleImageStringProtoService (Full protobuf)");
            GeneralTesting(peopleImageStringProtoService::protoNationalityImage, peopleImageStringProtoService::protoPersonImage, peopleImageStringProtoService::protoPeopleEmailImage);
            //Println_GeneralTesting(peopleImageStringProtoService::protoNationalityImage, peopleImageStringProtoService::protoPersonImage, peopleImageStringProtoService::protoPeopleEmailImage);
            System.out.println("END");

            System.out.println("peopleImageBytesProtoService (JSON send)");
            GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON, peopleImageBytesProtoService::personImageJSON, peopleImageBytesProtoService::peopleEmailImageJSON);
            //Println_GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON, peopleImageBytesProtoService::personImageJSON, peopleImageBytesProtoService::peopleEmailImageJSON);
            System.out.println("END");

            System.out.println("peopleImageBytesProtoService (Full protobuf)");
            GeneralTesting(peopleImageBytesProtoService::protoNationalityImage, peopleImageBytesProtoService::protoPersonImage, peopleImageBytesProtoService::protoPeopleEmailImage);
            //Println_GeneralTesting(peopleImageBytesProtoService::protoNationalityImage, peopleImageBytesProtoService::protoPersonImage, peopleImageBytesProtoService::protoPeopleEmailImage);
            System.out.println("END");

            System.out.println("peopleImageBytesProtoService, PIR (JSON send)");
            GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON_PIR, peopleImageBytesProtoService::personImageJSON_PIR, peopleImageBytesProtoService::peopleEmailImageJSON_PIR);
            //Println_GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON_PIR, peopleImageBytesProtoService::personImageJSON_PIR, peopleImageBytesProtoService::peopleEmailImageJSON_PIR);
            System.out.println("END");

            System.out.println("peopleImageBytesProtoService, PIR (Full protobuf)");
            GeneralTesting(peopleImageBytesProtoService::protoNationalityImage_PIR, peopleImageBytesProtoService::protoPersonImage_PIR, peopleImageBytesProtoService::protoPeopleEmailImage_PIR);
            //Println_GeneralTesting(peopleImageBytesProtoService::protoNationalityImage_PIR, peopleImageBytesProtoService::protoPersonImage_PIR, peopleImageBytesProtoService::protoPeopleEmailImage_PIR);
            System.out.println("END");
        }
    }
}
