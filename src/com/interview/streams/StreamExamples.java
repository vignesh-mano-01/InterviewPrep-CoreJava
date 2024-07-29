package com.interview.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExamples {

    public static void main(String[] args) {
	// write your code here
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
        empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
        empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));
        empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
        empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
        empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
        empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandrum", 2015));
        empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));

        //Question 1: Grouping Employees by City

        // Define a map to store employees grouped by city
        Map<String, List<Employee>> empByCity;
        empByCity =empList.stream().collect(Collectors.groupingBy(Employee::getCity));

        //Question 2: Finding the Count of Male and Female Employees
        Map<String, Long> noOfMaleAndFemaleEmployees = empList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        //Question 3: Printing Names of All Departments
        empList.stream()
                .map(Employee::getDeptName)
                .distinct()
                .forEach(System.out::println);

        //Question 4: Printing Employee Details by Age Criteria
        empList.stream()
                .filter(e -> e.getAge() > 28) // Filtering employees based on age criteria
                .forEach(System.out::println); // Printing employee details

        //Question 5: Finding Maximum Age of Employee
        OptionalInt max = empList.stream()
                .mapToInt(Employee::getAge) // Map employee ages to IntStream
                .max(); // Find the maximum age
        if (max.isPresent()) {
            System.out.println("Maximum age of Employee: " + max.getAsInt());
        }

        //Question 6: Printing Average Age of Male and Female Employees
        Map<String, Double> avgAge = empList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getGender, // Group employees by gender
                        Collectors.averagingInt(Employee::getAge) // Compute average age for each gender
                ));

        // Question 7: Finding the Oldest Employee
        Optional<Employee> oldestEmp = empList.stream()
                .max(Comparator.comparingInt(Employee::getAge));
        if (oldestEmp.isPresent()) {
            Employee oldestEmployee = oldestEmp.get();
        }

        //Question 8: Finding the Youngest Female Employee
        Optional<Employee> youngestEmp = empList.stream()
                .filter(e -> e.getGender().equals("F")) // Filter by gender (female)
                .min(Comparator.comparingInt(Employee::getAge)); // Find the minimum age
        if (youngestEmp.isPresent()) {
            Employee youngestEmployee = youngestEmp.get();
        }

        //Question 9: Finding Employees by Age Range
        System.out.println("Answer");
        // Partition employees by age using Collectors.partitioningBy()
        Map<Boolean, List<Employee>> partitionEmployeesByAge = empList.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 30));
        System.out.println("partitionEmployeesByAge " +partitionEmployeesByAge);
        // Iterate through the partitioned map and print the results
        Set<Map.Entry<Boolean, List<Employee>>> empSet = partitionEmployeesByAge.entrySet();
        for (Map.Entry<Boolean, List<Employee>> entry : empSet) {
            if (Boolean.TRUE.equals(entry.getKey())) {
                // Employees greater than 30 years old
                System.out.println("Employees greater than 30 years ::" + entry.getValue());
            } else {
                // Employees less than 30 years old
                System.out.println("Employees less than 30 years ::" + entry.getValue());
            }
        }


       // 10: Finding Department with Highest Number of Employees
        // Find the department name with the highest number of employees
        Map.Entry<String, Long> maxNoOfEmployeesInDept = empList.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get();
// Print the department with the highest number of employees
        System.out.println("Max no of employees present in Dept :: " + maxNoOfEmployeesInDept.getKey());

        //11: Finding Employees from HR Department
        Optional<Employee> emp = empList.stream()
                .filter(e -> e.getDeptName().equalsIgnoreCase("HR"))
                .findAny();
// Check if an employee from the HR department is found
        emp.ifPresent(employee -> System.out.println("Found employees from HR department: " + employee));

        // Question 12: Finding Departments with Over 3 Employees
        empList.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 3)
                .forEach(System.out::println);

        // Question 13: Sorting Employees by Name and Age
        // Comparator for sorting by name
        Comparator<Employee> comparator1 = Comparator.comparing(Employee::getName);
// Comparator for sorting by age
        Comparator<Employee> comparator2 = Comparator.comparingInt(Employee::getAge);
// Sorting the stream of employees using comparator chaining
        empList.stream().sorted(comparator1.thenComparing(comparator2)).forEach(System.out::println);

        // Question 14: Finding the Highest Experienced Employee
        Optional<Employee> seniorEmp = empList.stream()
                .sorted(Comparator.comparingInt(Employee::getYearOfJoining))
                .findFirst();
        System.out.println("Senior Employee Details: " + seniorEmp.get());

        //15: Printing Average and Total Salary of the Organization

        DoubleSummaryStatistics empSalary = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Average Salary in the organisation = " + empSalary.getAverage());
        System.out.println("Total Salary in the organisation = " + empSalary.getSum());

        //16 : Printing Average Salary of Each Department
        Map<String, Double> avgSalary = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.averagingDouble(Employee::getSalary)));
        Set<Map.Entry<String, Double>> entrySet = avgSalary.entrySet();
        for (Map.Entry<String, Double> entry : entrySet) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        //Question 17: Finding the Highest Salary in the Organization
        Optional<Employee> empHighest = empList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .findFirst();
        System.out.println("Highest Salary in the organization : " + empHighest.get().getSalary());

        //Question 18: Finding the Second Highest Salary in the Organization
        Optional<Employee> emp2 = empList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1)
                .findFirst();
        System.out.println("Second Highest Salary in the organisation : " + emp2.get().getSalary());

        //Question 19: Finding the Nth Highest Salary in the Organization
        int n = 10; // Represents the Nth highest salary to find
        Optional<Employee> empNthHighest = empList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(n - 1)
                .findFirst();
        System.out.println("Nth Highest Salary in the organisation : " + empNthHighest.get().getSalary());


        //Question 20: Finding the Highest Paid Salary in the Organization Based on Gender

        Map<String, Optional<Employee>> highestPaidMFEmployee = empList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.maxBy((t1, t2) -> (int) (t1.getSalary() - t2.getSalary()))));
        System.out.println("Highest paid male and female employee : " + highestPaidMFEmployee);

        //Question 21: Finding the Lowest Paid Salary in the Organization Based on Gender
        Map<String, Optional<Employee>> lowestPaidMFEmployee = empList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.minBy((t1, t2) -> (int) (t1.getSalary() - t2.getSalary()))));
        System.out.println("Lowest paid male and female employee : " + lowestPaidMFEmployee);

        //Question 22: Finding the Highest Salary Based on Department:
        empList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDeptName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().max(Comparator.comparingDouble(Employee::getSalary))
                        )
                ));

        //Question 23: Sorting the Employees' Salary in Each Department in Ascending Order:
        Map<String, Stream<Employee>> sortedEmployeeAsc = empList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDeptName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary))
                        )
                ));


    }
}
