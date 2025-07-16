package com.example.Mutlithreading_Tester.maintask;

import com.example.Mutlithreading_Tester.entity.PersonImageBytes;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.service.gRPC_related.Stub_gRPC_people_PeopleInfo_ServiceStub;
import com.example.Mutlithreading_Tester.service.gRPC_related.Stub_gRPC_peopleimage_PeopleInfoB_ServiceStub;
import com.example.Mutlithreading_Tester.service.gRPC_related.Stub_gRPC_peopleimage_PeopleInfo_ServiceStub;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class gRPCcallsTest implements CommandLineRunner {

    private final Stub_gRPC_people_PeopleInfo_ServiceStub stubGRPCPeoplePeopleInfoServiceStub;
    private final Stub_gRPC_peopleimage_PeopleInfo_ServiceStub stubGRPCPeopleimagePeopleInfoServiceStub;
    private final Stub_gRPC_peopleimage_PeopleInfoB_ServiceStub stubGRPCPeopleimagePeopleInfoBServiceStub;

    public gRPCcallsTest(Stub_gRPC_people_PeopleInfo_ServiceStub stubGRPCPeoplePeopleInfoServiceStub, Stub_gRPC_peopleimage_PeopleInfo_ServiceStub stubGRPCPeopleimagePeopleInfoServiceStubb, Stub_gRPC_peopleimage_PeopleInfoB_ServiceStub stubGRPCPeopleimagePeopleInfoBServiceStub) {
        this.stubGRPCPeoplePeopleInfoServiceStub = stubGRPCPeoplePeopleInfoServiceStub;
        this.stubGRPCPeopleimagePeopleInfoServiceStub = stubGRPCPeopleimagePeopleInfoServiceStubb;
        this.stubGRPCPeopleimagePeopleInfoBServiceStub = stubGRPCPeopleimagePeopleInfoBServiceStub;
    }

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

        System.out.println("Pre-actual, Caching - START");
        GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
        //Println_GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
        GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
        //Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
        GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
        //Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
        GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
        //Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
        System.out.println("Pre-actual, Caching - END");

        //
        for (int i = 0; i < 50; i++) {
            System.out.println("stubGRPCPeoplePeopleInfoServiceStub P-R");
            GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
            //Println_GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
            System.out.println("END");

            System.out.println("stubGRPCPeopleimagePeopleInfoServiceStub PI-R protobuf-String");
            GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
            //Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
            System.out.println("END");

            System.out.println("stubGRPCPeopleimagePeopleInfoServiceStub PI-R protobuf-Bytes");
            GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
            //Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
            System.out.println("END");

            System.out.println("stubGRPCPeopleimagePeopleInfoBServiceStub PIB-R protobuf-Bytes");
            GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
            //Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
            System.out.println("END");
        }
    }

}
