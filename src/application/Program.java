package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.println("Enter full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				double salary = Double.parseDouble(fields[2]);
				list.add(new Employee(name, email, salary));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salMin = sc.nextDouble();
			//imprimir a lista dos e-mails em ordem alfabética
			System.out.println("E-mail of people whose salary is more than " + salMin + ":");
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			//precisa ser list pois vai imprimir uma lista
			List<String> emailList = list.stream()
					.filter(e -> e.getSalary() > salMin)
					.map(e -> e.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			
			emailList.forEach(System.out::println);
			
			//soma de sálarios de pessoas com "M", sendo double pois só vai mostrar um resultado double
			double salaryM = list.stream()
					.filter(m -> m.getName().charAt(0) == 'M')
					.map(m -> m.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", salaryM));

			
		} catch (IOException e){
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();

	}

}
