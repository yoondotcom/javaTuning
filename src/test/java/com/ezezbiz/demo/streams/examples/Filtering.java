package com.ezezbiz.demo.streams.examples;

import com.ezezbiz.demo.streams.beans.Car;
import com.ezezbiz.demo.streams.mockdata.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {

    @Test
    @DisplayName("filter 테스트. 가격 99,000 이상, 노란차만 가져오기")
    public void filter() throws Exception {
        List<Car> cars = MockData.getCars();

        List<Car> result = cars.stream()
                .filter(car -> car.getPrice() > 0)
                .filter(car->car.getColor().equals("Yellow"))
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }

    @Test
    @DisplayName("filter 테스트. Predicate 를 활용하여 가격 99,000 이상, 노란차만 가져오기")
    public void filterUsePredicate() throws Exception {
        List<Car> cars = MockData.getCars();

        Predicate<Car> carPredicate = car -> car.getPrice() > 99000;
        Predicate<Car> yellow = car -> car.getColor().equals("Yellow");

        List<Car> result2 = cars.stream()
                .filter(carPredicate)
                .filter(yellow)
                .collect(Collectors.toList());

        result2.forEach(System.out::println);
    }

    @Test
    public void takeWhile(){
        System.out.println("using filter");
        Stream.of(2, 4, 5, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.println(n + " "));
        System.out.println();
        System.out.println("using take while");
        Stream.of(2, 4, 5, 6, 8, 9, 10, 12)
                .takeWhile(n-> n % 2 ==0)
                .forEach(n -> System.out.println(n + " "));

    }

    @Test
    public void dropWhile() {
        System.out.println("using filter");
        Stream.of(2, 4, 5, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.println(n + " "));
        System.out.println();
        System.out.println("using drop while");
        Stream.of(2, 4, 5, 6, 8, 9, 10, 12)
                .dropWhile(n-> n % 2 ==0)
                .forEach(n -> System.out.println(n + " "));
    }

    @Test
    @DisplayName("stream 에 첫 번째 값을 리턴 한다.")
    public void findFirst() {
        int[] numbers = {1,2,3,4,5,6,7,8,10};
        int result = Arrays.stream(numbers).filter(n-> n == 4)
                .findFirst()
                .orElse(-1);

        System.out.println(result);
    }

    @Test
    @DisplayName("")
    public void findAny() {
        int[] numbers = {1,2,3,4,5,6,7,8,10};
        int result = Arrays.stream(numbers).filter(n-> n > 4)
                .findAny()
                .orElse(-1);

        System.out.println(result);
    }

    @Test
    @DisplayName("")
    public void allMatch() {
        int[] numbers = {2, 4, 6, 8, 10};
        boolean result = Arrays.stream(numbers).allMatch(n-> n % 2 == 0);

        System.out.println(result);
    }
}
