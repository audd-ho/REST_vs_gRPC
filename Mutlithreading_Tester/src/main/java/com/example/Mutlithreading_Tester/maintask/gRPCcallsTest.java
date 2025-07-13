package com.example.Mutlithreading_Tester.maintask;

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


    // non-print

    public void people_PersonRepo_None_compareVSgrpcurl() {
        System.out.println("Running people_PersonRepo_None_compareVSgrpcurl");

        long time0 = System.currentTimeMillis();
        stubGRPCPeoplePeopleInfoServiceStub.nationality(Nations.SG);
        stubGRPCPeoplePeopleInfoServiceStub.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        stubGRPCPeoplePeopleInfoServiceStub.peopleEmail(Nations.SG);
        for (Nations nat : Nations.values()) {
            stubGRPCPeoplePeopleInfoServiceStub.nationality(nat);
        }
        for (Nations nat : Nations.values()) {
            stubGRPCPeoplePeopleInfoServiceStub.peopleEmail(nat);
        }
        stubGRPCPeoplePeopleInfoServiceStub.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("people_PersonRepo_None_compareVSgrpcurl: " + time_taken);
    }

    public void peopleimage_PersonImageRepo_String_compareVSgrpcurl() {
        System.out.println("Running peopleimage_PersonImageRepo_String_compareVSgrpcurl");

        long time0 = System.currentTimeMillis();
        stubGRPCPeopleimagePeopleInfoServiceStub.nationalityImage(Nations.SG);
        stubGRPCPeopleimagePeopleInfoServiceStub.personImage(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        stubGRPCPeopleimagePeopleInfoServiceStub.peopleEmailImage(Nations.SG);
        for (Nations nat : Nations.values()) {
            stubGRPCPeopleimagePeopleInfoServiceStub.nationalityImage(nat);
        }
        for (Nations nat : Nations.values()) {
            stubGRPCPeopleimagePeopleInfoServiceStub.peopleEmailImage(nat);
        }
        stubGRPCPeopleimagePeopleInfoServiceStub.personImage(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("peopleimage_PersonImageRepo_String_compareVSgrpcurl: " + time_taken);
    }

    public void peopleimage_PersonImageRepo_Bytes_compareVSgrpcurl() {
        System.out.println("Running peopleimage_PersonImageRepo_Bytes_compareVSgrpcurl");

        long time0 = System.currentTimeMillis();
        stubGRPCPeopleimagePeopleInfoServiceStub.nationalityImageBytes(Nations.SG);
        stubGRPCPeopleimagePeopleInfoServiceStub.personImageBytes(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        stubGRPCPeopleimagePeopleInfoServiceStub.peopleEmailImageBytes(Nations.SG);
        for (Nations nat : Nations.values()) {
            stubGRPCPeopleimagePeopleInfoServiceStub.nationalityImageBytes(nat);
        }
        for (Nations nat : Nations.values()) {
            stubGRPCPeopleimagePeopleInfoServiceStub.peopleEmailImageBytes(nat);
        }
        stubGRPCPeopleimagePeopleInfoServiceStub.personImageBytes(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("peopleimage_PersonImageRepo_Bytes_compareVSgrpcurl: " + time_taken);
    }

    public void peopleimage_PersonImageBytesRepo_Bytes_compareVSgrpcurl() {
        System.out.println("Running peopleimage_PersonImageBytesRepo_Bytes_compareVSgrpcurl");

        long time0 = System.currentTimeMillis();
        stubGRPCPeopleimagePeopleInfoBServiceStub.nationalityImageBytes(Nations.SG);
        stubGRPCPeopleimagePeopleInfoBServiceStub.personImageBytes(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        stubGRPCPeopleimagePeopleInfoBServiceStub.peopleEmailImageBytes(Nations.SG);
        for (Nations nat : Nations.values()) {
            stubGRPCPeopleimagePeopleInfoBServiceStub.nationalityImageBytes(nat);
        }
        for (Nations nat : Nations.values()) {
            stubGRPCPeopleimagePeopleInfoBServiceStub.peopleEmailImageBytes(nat);
        }
        stubGRPCPeopleimagePeopleInfoBServiceStub.personImageBytes(new PersonRequestDTO("987-56-3201", "509823641", Nations.US));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("peopleimage_PersonImageBytesRepo_Bytes_compareVSgrpcurl: " + time_taken);
    }


    // println

    public void println_people_PersonRepo_None_compareVSgrpcurl() {
        System.out.println("Running println_people_PersonRepo_None_compareVSgrpcurl");

        long time0 = System.currentTimeMillis();
        System.out.println(stubGRPCPeoplePeopleInfoServiceStub.nationality(Nations.SG));
        System.out.println(stubGRPCPeoplePeopleInfoServiceStub.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        System.out.println(stubGRPCPeoplePeopleInfoServiceStub.peopleEmail(Nations.SG));
        for (Nations nat : Nations.values()) {
            System.out.println(stubGRPCPeoplePeopleInfoServiceStub.nationality(nat));
        }
        for (Nations nat : Nations.values()) {
            System.out.println(stubGRPCPeoplePeopleInfoServiceStub.peopleEmail(nat));
        }
        System.out.println(stubGRPCPeoplePeopleInfoServiceStub.person(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("println_people_PersonRepo_None_compareVSgrpcurl: " + time_taken);
    }

    public void println_peopleimage_PersonImageRepo_String_compareVSgrpcurl() {
        System.out.println("Running println_peopleimage_PersonImageRepo_String_compareVSgrpcurl");

        long time0 = System.currentTimeMillis();
        System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.nationalityImage(Nations.SG));
        System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.personImage(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.peopleEmailImage(Nations.SG));
        for (Nations nat : Nations.values()) {
            System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.nationalityImage(nat));
        }
        for (Nations nat : Nations.values()) {
            System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.peopleEmailImage(nat));
        }
        System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.personImage(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("println_peopleimage_PersonImageRepo_String_compareVSgrpcurl: " + time_taken);
    }

    public void println_peopleimage_PersonImageRepo_Bytes_compareVSgrpcurl() {
        System.out.println("Running println_peopleimage_PersonImageRepo_Bytes_compareVSgrpcurl");

        long time0 = System.currentTimeMillis();
        System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.nationalityImageBytes(Nations.SG));
        System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.personImageBytes(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.peopleEmailImageBytes(Nations.SG));
        for (Nations nat : Nations.values()) {
            System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.nationalityImageBytes(nat));
        }
        for (Nations nat : Nations.values()) {
            System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.peopleEmailImageBytes(nat));
        }
        System.out.println(stubGRPCPeopleimagePeopleInfoServiceStub.personImageBytes(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("println_peopleimage_PersonImageRepo_Bytes_compareVSgrpcurl: " + time_taken);
    }

    public void println_peopleimage_PersonImageBytesRepo_Bytes_compareVSgrpcurl() {
        System.out.println("Running println_peopleimage_PersonImageBytesRepo_Bytes_compareVSgrpcurl");

        long time0 = System.currentTimeMillis();
        System.out.println(stubGRPCPeopleimagePeopleInfoBServiceStub.nationalityImageBytes(Nations.SG));
        System.out.println(stubGRPCPeopleimagePeopleInfoBServiceStub.personImageBytes(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        System.out.println(stubGRPCPeopleimagePeopleInfoBServiceStub.peopleEmailImageBytes(Nations.SG));
        for (Nations nat : Nations.values()) {
            System.out.println(stubGRPCPeopleimagePeopleInfoBServiceStub.nationalityImageBytes(nat));
        }
        for (Nations nat : Nations.values()) {
            System.out.println(stubGRPCPeopleimagePeopleInfoBServiceStub.peopleEmailImageBytes(nat));
        }
        System.out.println(stubGRPCPeopleimagePeopleInfoBServiceStub.personImageBytes(new PersonRequestDTO("987-56-3201", "509823641", Nations.US)));
        long time1 = System.currentTimeMillis();

        long time_taken = time1-time0;
        System.out.println("println_peopleimage_PersonImageBytesRepo_Bytes_compareVSgrpcurl: " + time_taken);
    }

    @Override
    public void run(String... args) throws Exception {
        //if (true) { return;}

        //GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
        //Println_GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);


        /*
        people_PersonRepo_None_compareVSgrpcurl();
        peopleimage_PersonImageRepo_String_compareVSgrpcurl();
        peopleimage_PersonImageRepo_Bytes_compareVSgrpcurl();
        peopleimage_PersonImageBytesRepo_Bytes_compareVSgrpcurl();
        */
        
        //println_people_PersonRepo_None_compareVSgrpcurl();
        //println_peopleimage_PersonImageRepo_String_compareVSgrpcurl();
        //println_peopleimage_PersonImageRepo_Bytes_compareVSgrpcurl();
        //println_peopleimage_PersonImageBytesRepo_Bytes_compareVSgrpcurl();

        System.out.println("Pre-actual, Caching - START");
        GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
        Println_GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
        GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
        Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
        GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
        Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
        GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
        Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
        System.out.println("Pre-actual, Caching - END");

        //

        System.out.println("stubGRPCPeoplePeopleInfoServiceStub P-R");
        GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
        Println_GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
        System.out.println("END");

        System.out.println("stubGRPCPeopleimagePeopleInfoServiceStub PI-R protobuf-String");
        GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
        Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
        System.out.println("END");

        System.out.println("stubGRPCPeopleimagePeopleInfoServiceStub PI-R protobuf-Bytes");
        GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
        Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
        System.out.println("END");

        System.out.println("stubGRPCPeopleimagePeopleInfoBServiceStub PIB-R protobuf-Bytes");
        GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
        Println_GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
        System.out.println("END");
        
    }

}
