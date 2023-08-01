package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.util.Objects;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    // 21. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // 22. Extract the total
    @Test
    public void extractTotal() {
        List<String> listOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    // 23. Extract the name of 5th product
    @Test
    public void extractName5Product() {
        String productName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + productName);
        System.out.println("------------------End of Test---------------------------");

    }

    // 24. Extract the names of all the products
    @Test
    public void extractAllProductsName() {
        List<String> allProducts = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Thenames of all the products is : " + allProducts);
        System.out.println("------------------End of Test---------------------------");

    }

    // 25. Extract the productId of all the products
    @Test
    public void extractProductsId() {
        List<Integer> productsIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The productId of all the products is :" + productsIds);
        System.out.println("------------------End of Test---------------------------");

    }

    // 26. Print the size of the data list
    @Test
    public void printSize() {
        List<HashMap<String, ?>> sizeOfAllProduct = response.extract().path("data.");
        int listSize = sizeOfAllProduct.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + listSize);
        System.out.println("------------------End of Test---------------------------");

    }

    // 27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test07() {
        List<HashMap<String, ?>> productValue = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product where product name = Energizer - MAX Batteries AA (4-Pack) is : " + productValue);
        System.out.println("------------------End of Test---------------------------");
    }

    // 28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test08() {
        List<String> productName = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product where product name = Energizer - N Cell E90 Batteries (2- Pack) is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 29. Get all the categories of 8th products
    @Test
    public void test09() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of 8th products) is : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 30. Get categories of the store where product id = 150115
    @Test
    public void test10() {
        List<String> categories = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store where product id = 15011) is : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 31. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<String> allDescription = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products is : " + allDescription);
        System.out.println("------------------End of Test---------------------------");
    }

    // 32. Get id of all the all categories of all the products
    @Test
    public void test12() {
        List<String> allCategories = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products is : " + allCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 33. Find the product names Where type = HardGood
    @Test
    public void test13() {
        List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test14() {
        List<Objects> numberOfCategories = response.extract().path("data.findAll{it.name =='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}");
        int totalCategories = numberOfCategories.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) is : " + totalCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test15() {
        List<String> productName = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test16() {
        List<String> productName = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)” is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 37. Find the manufacturer of all the products
    @Test
    public void test17() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all the products is : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    // 38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test18() {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of products whose manufacturer is = Energizer is : " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    // 39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test19() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 is : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 40. Find the url of all the product
    @Test
    public void test20() {
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The uri of all the product is : " + url);
        System.out.println("------------------End of Test---------------------------");
    }
}

