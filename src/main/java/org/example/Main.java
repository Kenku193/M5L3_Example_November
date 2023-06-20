package org.example;

import org.example.congif.AppConfig;
import org.example.entity.Customer;
import org.example.repository.ConnectionPool;
import org.example.repository.impl.CustomerRepo;
import org.example.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {


//      1 - WITHOUT SPRING
//        CustomerService customerService = new CustomerService();
//        ConnectionPool connectionPool = new ConnectionPool();
//        CustomerRepo customerRepo = new CustomerRepo(connectionPool);
//        customerService.setCustomerRepo(customerRepo);
//        Customer customer = customerService.get(1L);
//        System.out.println(customer);

        //      2 - WITH SPRING
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        try (context) {

            Customer customer = context.getBean(CustomerService.class).get(1L);
            System.out.println();
            System.out.println("1 " + customer);

            customer.setPassword("AnotherNewPasswprd");
            System.out.println("2 from entity " + customer);

            context.getBean(CustomerService.class).update(customer);
            Customer updatedCustomer = context.getBean(CustomerService.class).get(1L);

            System.out.println("3 from DB" + updatedCustomer);
        }

    }
}