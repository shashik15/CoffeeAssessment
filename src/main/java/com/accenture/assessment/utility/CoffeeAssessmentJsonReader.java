package com.accenture.assessment.utility;

import com.accenture.assessment.exception.DrinkSizeNotFoundException;
import com.accenture.assessment.model.Order;
import com.accenture.assessment.model.Payment;
import com.accenture.assessment.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author Shashi Kumari
 *
 * This class reads the input values from json files and loads it in memory
 */

@Slf4j
public class CoffeeAssessmentJsonReader {

    public static final String PRODUCTS_JSON_PATH = "products.json";
    public static final String ORDERS_JSON_PATH = "orders.json";
    public static final String PAYMENTS_JSON_PATH = "payments.json";

    private static List<Product> productList;
    private static List<Payment> paymentList;
    private static List<Order> orderList;

    /**
     * loads the input data from json files
     */
    static {
         loadInputData();
    }

    /**
     *
     * @return the details of user orders
     */
    public static Map<String, Double> getUserOrderDetails() {

        Map<String, Double> userOrderDetails = new HashMap<>();

        for (Order order : orderList) {
            double totalOrderAmountPerUser = 0;
            try {
                totalOrderAmountPerUser = getDrinkPrice(order.getDrink(), order.getSize());
            }catch(DrinkSizeNotFoundException exception)
            {
                //logging the excpetion
                log.error(exception.getMessage());
            }
            String user = order.getUser();
            if (userOrderDetails.containsKey(user)) {
                totalOrderAmountPerUser += userOrderDetails.get(user);
            }
            userOrderDetails.put(order.getUser(), totalOrderAmountPerUser);
        }
        return userOrderDetails;
    }

    /**
     *
     * @return the user payment details
     */
    public static Map<String, Double> getUserPaymentDetails() {

        Map<String, Double> userPaymentDetails = new HashMap<>();
        for (Payment payment : paymentList) {
            double totalAmountPaidByUser = payment.getAmountPaid();
            String user = payment.getUser();
            if (userPaymentDetails.containsKey(user)) {
                totalAmountPaidByUser += userPaymentDetails.get(user);
            }
            userPaymentDetails.put(user, totalAmountPaidByUser);
        }
        return userPaymentDetails;
    }

    /**
     *
     * @param drinkName
     * @param size
     * @return this method returns the price for particular drink size
     *
     * @throws DrinkSizeNotFoundException
     *
     */
    public static double getDrinkPrice(String drinkName, String size) throws DrinkSizeNotFoundException {
        Optional<Product> product = productList.stream().
                filter(p -> p.getDrinkName().
                        equals(drinkName)).findFirst();

        Product prod = product.orElseThrow(() -> new DrinkSizeNotFoundException("Size" + size +
                " not found for Drink " + drinkName));
        return prod.getPrices().get(size);
    }

    /**
     * This method reads the json files and loads the input data in memory
     *
     */
    private static void loadInputData() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            File productJson = new File(classLoader.getResource(PRODUCTS_JSON_PATH).getFile());
            File orderJson = new File(classLoader.getResource(ORDERS_JSON_PATH).getFile());
            File paymentJson = new File(classLoader.getResource(PAYMENTS_JSON_PATH).getFile());

            productList = Arrays.asList(mapper.readValue(productJson, Product[].class));
            orderList = Arrays.asList(mapper.readValue(orderJson, Order[].class));
            paymentList = Arrays.asList(mapper.readValue(paymentJson, Payment[].class));

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
