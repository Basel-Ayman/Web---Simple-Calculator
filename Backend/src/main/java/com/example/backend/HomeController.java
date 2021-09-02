package com.example.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {

    @GetMapping("/percent")
    public String percent(double num1) {
        return ((num1) / 100) + "";
    }

    @GetMapping("/inverse")
    public String inverse(double num1) {
        if (num1 == 0) {
            return "E";
        }else {
            return (1 / num1) + "";
        }
    }

    @GetMapping("/square")
    public String square(double num1) {
        return (num1 * num1) + "";
    }

    @GetMapping("/squareRoot")
    public String squareRoot(double num1) {
        return Math.sqrt(num1) + "";
    }

    @GetMapping("/divide")
    public String divide(double num1, double num2) {
        if (num2 == 0) {
            return "E";
        }else {
            return (num1 / num2) + "";
        }
    }

    @GetMapping("/multiply")
    public String multiply(double num1, double num2) {
        return (num1 * num2) + "";
    }

    @GetMapping("/subtract")
    public String subtract(double num1, double num2) {
        return (num1 - num2) + "";
    }

    @GetMapping("/add")
    public String add(double num1, double num2) {
        return (num1 + num2) + "";
    }

    @GetMapping("/sign")
    public String sign(double num1) {
        return (-1 * num1) + "";
    }
}
