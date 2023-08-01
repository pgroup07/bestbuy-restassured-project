package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2. Extract the total
    @Test
    public void extractTotal() {
        List<String> listOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3. Extract the name of 5th store
    @Test
    public void extractName5Store() {
        String storeName = response.extract().path("data[5].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store Name is : " + storeName);
        System.out.println("------------------End of Test---------------------------");

    }

    // 4. Extract the names of all the store
    @Test
    public void extractAllStoreName() {
        List<String> allStore = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All Store Name are : " + allStore);
        System.out.println("------------------End of Test---------------------------");

    }

    // 5. Extract the storeId of all the store
    @Test
    public void extractStoreId() {
        List<Integer> storeIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All Store Id are :" + storeIds);
        System.out.println("------------------End of Test---------------------------");

    }

    // 6. Print the size of the data list
    @Test
    public void printSize() {
        List<Object> objectData = response.extract().path("data");
        int sizeOfData = objectData.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + sizeOfData);
        System.out.println("------------------End of Test---------------------------");

    }

    // 7. Get all the value of the store where store name = St Cloud
    @Test
    public void getAllValueStCloud() {
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for of the store where store name = St Cloud is: " + value);
        System.out.println("------------------End of Test---------------------------");

    }

    // 8. Get the address of the store where store name = Rochester
    @Test
    public void getAddressRochester() {
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name=='Rochester'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for of the store where store name = Rochester is: " + address);
        System.out.println("------------------End of Test---------------------------");

    }

    // 9. Get all the services of 8th store
    @Test
    public void getAllServices8thStore() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of 8th store is: " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10. Get storeservices of the store where service name = Windows Store
    @Test
    public void storeServicesWindowsStore() {
        List<HashMap<String, ?>> storeServicesWindowsStore = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store where service name = Windows Store is: " + storeServicesWindowsStore);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11. Get all the storeId of all the store
    @Test
    public void allStoreId() {
        List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store are: " + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12. Get id of all the store
    @Test
    public void GetIdOfAllStore() {
        List<Integer> allId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Id of all the store are: " + allId);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void stateNameND() {
        List<String> stateName = response.extract().path("data.findAll{it.state =='ND'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state = ND is : " + stateName);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void totalNumberOfServices() {
        List<Object> totalNumber = response.extract().path("data.findAll{it.name =='Rochester'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where name = Rochester is : " + totalNumber.size());
        System.out.println("------------------End of Test---------------------------");
    }


    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void createdAtWindowsStore() {
        List<HashMap<String, ?>> createdAtListMap = response.extract().path("data.findAll{it.name == 'Windows Store'}");
        HashMap<String, ?> createdAtMap = createdAtListMap.get(0);
        String name = (String) createdAtMap.get("name");
        String createdAt = (String) createdAtMap.get("createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services whose name 'Windows Store' is : " + name);
        System.out.println("------------------End of Test---------------------------");

    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void nameFargo() {
        List<Object> storeName = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name = “Fargo” is  : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void storeZip() {
        List<Object> ZipOfStores = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name is : " + ZipOfStores);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void zipRoseville() {
        List<Object> zipOfStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville is : " + zipOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void storeservicesMagnolia() {
        List<Object> storeService = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater is : " + storeService);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void storesLat() {
        List<Integer> latList =response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores are : " + latList);
        System.out.println("------------------End of Test---------------------------");
    }
}
