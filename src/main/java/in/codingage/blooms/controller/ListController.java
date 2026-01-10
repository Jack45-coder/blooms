package in.codingage.blooms.controller;

import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.models.Category;

import java.util.ArrayList;
import java.util.List;

public class ListController {
    public static void main(String[] args) {
        // string data type ke element ko add kar sakte ho
        // JCF - java Collection Framework
        List<String> items = new ArrayList<>();
        items.add("Jackey");
        items.add("Simpal");
        items.add("Pragya");
        items.add("Mukul");
        items.add("Deepak");


        for(String item : items){
            System.out.println(item);
        }

        // method overloading
        items.remove(0);

        // IOUB
        // Exception Handling

        try{
            System.out.println(items.get(0));
        }catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bounds exception caught: " + e.getMessage());
        }
        for(String itm : items){
            System.out.println(itm);
        }

        List<CategoryResponse> categoryResponses = new ArrayList<>();

        categoryResponses.add(new CategoryResponse("1", "Technology A", "All about technology", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyFSDvZA27CPTHnxlnq8vd-pfw0vcsNGFafA&s"));
        categoryResponses.add(new CategoryResponse("2", "Technology B", "All about technology", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyFSDvZA27CPTHnxlnq8vd-pfw0vcsNGFafA&s"));
        categoryResponses.add(new CategoryResponse("3", "Technology C", "All about technology", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyFSDvZA27CPTHnxlnq8vd-pfw0vcsNGFafA&s"));

        for(CategoryResponse cat : categoryResponses){
            System.out.println("Category ID: " + cat.getId() + ", Name: " + cat.getTitle());
        }

        String idToFind = "20";

        for (CategoryResponse cat : categoryResponses) {
            if (cat.getId().equals(idToFind)) {
                System.out.println("Found Category with ID " + idToFind + ": " + cat.getTitle());
            }
        }

        String idToRemove = "2";

        for (CategoryResponse cat : categoryResponses) {
            if (cat.getId().equals(idToRemove)) {
                categoryResponses.remove(cat);
                break;
            }
        }

        for (CategoryResponse cat : categoryResponses) {
            System.out.println("Category ID: " + cat.getId() + ", Name: " + cat.getTitle());
        }
    }
}
