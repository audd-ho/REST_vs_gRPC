package com.example.Multithreading_Tester.maintask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Multithreading_Tester.service.gRPC_related.*;
import com.example.Multithreading_Tester.service.REST_related.protobuf_related.*;
import com.example.Multithreading_Tester.service.REST_related.non_protobuf_related.*;

@Component
public class ConcurrencyCallsTest implements CommandLineRunner {

    private final ConcurrencyCalls concurrencyCalls;

    private final Stub_gRPC_people_PeopleInfo_ServiceStub stubGRPCPeoplePeopleInfoServiceStub;
    private final Stub_gRPC_peopleimage_PeopleInfo_ServiceStub stubGRPCPeopleimagePeopleInfoServiceStub;
    private final Stub_gRPC_peopleimage_PeopleInfoB_ServiceStub stubGRPCPeopleimagePeopleInfoBServiceStub;

    private final PeopleProtoService peopleProtoService;
    private final PeopleImageStringProtoService peopleImageStringProtoService;
    private final PeopleImageBytesProtoService peopleImageBytesProtoService;

    private final PeopleService peopleService;
    private final PeopleImageService peopleImageService;

    public ConcurrencyCallsTest(
            ConcurrencyCalls concurrencyCalls,

            Stub_gRPC_people_PeopleInfo_ServiceStub stubGRPCPeoplePeopleInfoServiceStub,
            Stub_gRPC_peopleimage_PeopleInfo_ServiceStub stubGRPCPeopleimagePeopleInfoServiceStub,
            Stub_gRPC_peopleimage_PeopleInfoB_ServiceStub stubGRPCPeopleimagePeopleInfoBServiceStub,

            PeopleProtoService peopleProtoService,
            PeopleImageStringProtoService peopleImageStringProtoService,
            PeopleImageBytesProtoService peopleImageBytesProtoService,

            PeopleService peopleService,
            PeopleImageService peopleImageService
    ) {
        this.concurrencyCalls = concurrencyCalls;

        this.stubGRPCPeoplePeopleInfoServiceStub = stubGRPCPeoplePeopleInfoServiceStub;
        this.stubGRPCPeopleimagePeopleInfoServiceStub = stubGRPCPeopleimagePeopleInfoServiceStub;
        this.stubGRPCPeopleimagePeopleInfoBServiceStub = stubGRPCPeopleimagePeopleInfoBServiceStub;

        this.peopleProtoService = peopleProtoService;
        this.peopleImageStringProtoService = peopleImageStringProtoService;
        this.peopleImageBytesProtoService = peopleImageBytesProtoService;

        this.peopleService = peopleService;
        this.peopleImageService = peopleImageService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Pre-actual, Caching - START");
        
        concurrencyCalls.GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
        concurrencyCalls.GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
        concurrencyCalls.GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
        concurrencyCalls.GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);

        concurrencyCalls.GeneralTesting(peopleProtoService::nationalityJSON, peopleProtoService::personJSON, peopleProtoService::peopleEmailJSON);
        concurrencyCalls.GeneralTesting(peopleProtoService::protoNationality, peopleProtoService::protoPerson, peopleProtoService::protoPeopleEmail);
        concurrencyCalls.GeneralTesting(peopleImageStringProtoService::nationalityImageJSON, peopleImageStringProtoService::personImageJSON, peopleImageStringProtoService::peopleEmailImageJSON);
        concurrencyCalls.GeneralTesting(peopleImageStringProtoService::protoNationalityImage, peopleImageStringProtoService::protoPersonImage, peopleImageStringProtoService::protoPeopleEmailImage);
        concurrencyCalls.GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON, peopleImageBytesProtoService::personImageJSON, peopleImageBytesProtoService::peopleEmailImageJSON);
        concurrencyCalls.GeneralTesting(peopleImageBytesProtoService::protoNationalityImage, peopleImageBytesProtoService::protoPersonImage, peopleImageBytesProtoService::protoPeopleEmailImage);
        concurrencyCalls.GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON_PIR, peopleImageBytesProtoService::personImageJSON_PIR, peopleImageBytesProtoService::peopleEmailImageJSON_PIR);
        concurrencyCalls.GeneralTesting(peopleImageBytesProtoService::protoNationalityImage_PIR, peopleImageBytesProtoService::protoPersonImage_PIR, peopleImageBytesProtoService::protoPeopleEmailImage_PIR);

        concurrencyCalls.GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
        concurrencyCalls.GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
        concurrencyCalls.GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
        
        System.out.println("Pre-actual, Caching - END");
        
        
        /**/


        for (int i = 0; i < 50; i++) {
            System.out.println("stubGRPCPeoplePeopleInfoServiceStub P-R");
            concurrencyCalls.GeneralTesting(stubGRPCPeoplePeopleInfoServiceStub::nationality, stubGRPCPeoplePeopleInfoServiceStub::person, stubGRPCPeoplePeopleInfoServiceStub::peopleEmail);
            System.out.println("END");

            System.out.println("stubGRPCPeopleimagePeopleInfoServiceStub PI-R protobuf-String");
            concurrencyCalls.GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImage, stubGRPCPeopleimagePeopleInfoServiceStub::personImage, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImage);
            System.out.println("END");

            System.out.println("stubGRPCPeopleimagePeopleInfoServiceStub PI-R protobuf-Bytes");
            concurrencyCalls.GeneralTesting(stubGRPCPeopleimagePeopleInfoServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoServiceStub::peopleEmailImageBytes);
            System.out.println("END");

            System.out.println("stubGRPCPeopleimagePeopleInfoBServiceStub PIB-R protobuf-Bytes");
            concurrencyCalls.GeneralTesting(stubGRPCPeopleimagePeopleInfoBServiceStub::nationalityImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::personImageBytes, stubGRPCPeopleimagePeopleInfoBServiceStub::peopleEmailImageBytes);
            System.out.println("END");
            
            //

            System.out.println("peopleProtoService (JSON send)");
            concurrencyCalls.GeneralTesting(peopleProtoService::nationalityJSON, peopleProtoService::personJSON, peopleProtoService::peopleEmailJSON);
            System.out.println("END");

            System.out.println("peopleProtoService (Full protobuf)");
            concurrencyCalls.GeneralTesting(peopleProtoService::protoNationality, peopleProtoService::protoPerson, peopleProtoService::protoPeopleEmail);
            System.out.println("END");

            System.out.println("peopleImageStringProtoService (JSON send)");
            concurrencyCalls.GeneralTesting(peopleImageStringProtoService::nationalityImageJSON, peopleImageStringProtoService::personImageJSON, peopleImageStringProtoService::peopleEmailImageJSON);
            System.out.println("END");

            System.out.println("peopleImageStringProtoService (Full protobuf)");
            concurrencyCalls.GeneralTesting(peopleImageStringProtoService::protoNationalityImage, peopleImageStringProtoService::protoPersonImage, peopleImageStringProtoService::protoPeopleEmailImage);
            System.out.println("END");

            System.out.println("peopleImageBytesProtoService (JSON send)");
            concurrencyCalls.GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON, peopleImageBytesProtoService::personImageJSON, peopleImageBytesProtoService::peopleEmailImageJSON);
            System.out.println("END");

            System.out.println("peopleImageBytesProtoService (Full protobuf)");
            concurrencyCalls.GeneralTesting(peopleImageBytesProtoService::protoNationalityImage, peopleImageBytesProtoService::protoPersonImage, peopleImageBytesProtoService::protoPeopleEmailImage);
            System.out.println("END");

            System.out.println("peopleImageBytesProtoService, PIR (JSON send)");
            concurrencyCalls.GeneralTesting(peopleImageBytesProtoService::nationalityImageJSON_PIR, peopleImageBytesProtoService::personImageJSON_PIR, peopleImageBytesProtoService::peopleEmailImageJSON_PIR);
            System.out.println("END");

            System.out.println("peopleImageBytesProtoService, PIR (Full protobuf)");
            concurrencyCalls.GeneralTesting(peopleImageBytesProtoService::protoNationalityImage_PIR, peopleImageBytesProtoService::protoPersonImage_PIR, peopleImageBytesProtoService::protoPeopleEmailImage_PIR);
            System.out.println("END");
            
            //

            System.out.println("peopleService (not all json)");
            concurrencyCalls.GeneralTesting(peopleService::nationality, peopleService::person, peopleService::peopleEmail);
            System.out.println("END");

            System.out.println("peopleService (JSON)");
            concurrencyCalls.GeneralTesting(peopleService::nationalityJSON, peopleService::person, peopleService::peopleEmailJSON);
            System.out.println("END");

            System.out.println("peopleImageService (JSON)");
            concurrencyCalls.GeneralTesting(peopleImageService::nationalityImageJSON, peopleImageService::personImage, peopleImageService::peopleEmailImageJSON);
            System.out.println("END");
        }


    }

}
