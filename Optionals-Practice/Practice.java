import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

class Practice{
    
    public static void main(String[] args) {
        
        Practice practice = new Practice();
        
        System.out.println(practice.getEmployeeEmail(101));
        System.out.println(practice.getEmployeeEmail(102));
        System.out.println(practice.getEmployeeEmail(103));
        
        Optional<String> employeeName = Optional.of("Sricharan");
        
        //ifPresent & get 
        employeeName.ifPresent(i -> System.out.println(i)); //consumer 
        employeeName.ifPresent(System.out::println);
        System.out.println(employeeName.get());


        //isPresent & get
        Optional<String> phoneNumber = Optional.of("9876543210");
        if (phoneNumber.isPresent())
            System.out.println(phoneNumber.get());

        //orElse
        Optional<String> department = Optional.empty();
        System.out.println(department.orElse("IT"));
        department = Optional.of("Cse");
        System.out.println(department.orElse("IT"));

        //orElseGet
        Optional<Integer> employeeId = Optional.empty();
        System.out.println(employeeId.orElseGet(() -> new Random().ints(1000,9999).findFirst().getAsInt())); //supplier
        employeeId = Optional.of(1);
        System.out.println(employeeId.orElseGet(() -> new Random().ints(1000,9999).findFirst().getAsInt())); //supplier


        //OrElseThrow 
        Optional<Employee> employee = Optional.ofNullable(null);
        try {
            System.out.println(employee.orElseThrow());
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(employee.orElseThrow(() -> new RuntimeException("Value not found runtime exception"))); // Supplier
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        //Filter
        Optional<Employee> e1=Optional.of(new Employee(1, "rahul", 25));
        System.out.println(e1.filter(i -> i.age >= 20)); // predicate
        System.out.println(e1.filter(i -> i.age >= 26));

        //map
        System.out.println(e1.map(emp -> emp.name.toUpperCase())); //Function

        
        Optional<Student> student = Optional.of(new Student());
        Optional<Department> department1 = Optional.of(new Department());
        department1.ifPresent(department2 -> department2.name = "Java");
        
        student.ifPresent(student1 ->  student1.department = department1);
       
        //flatmap
        System.out.println(student.flatMap(o -> o.department).map(i -> i.name)); //flatmap

        //filter & map
        System.out.println(e1.filter(i -> i.age >= 25).map(i -> i.name));

    }

    public Optional<String> getEmployeeEmail(Integer employeeId) {
        if(employeeId.equals(101))
            return Optional.of("sricharan@gmail.com");

        else if(employeeId.equals(102))
            return Optional.ofNullable(null);
        
        return Optional.empty();
    }    
}

class Employee {
        int id;
        String name;
        int age;
        Employee(int id,String name, int age){
            this.id=id;
            this.name=name;
            this.age=age;
        }
        public String toString(){
            return id+" "+name+" "+age;
        }
}


class Student {
    Optional<Department> department;

    public Optional<Department> getDepartment() {
        return department;
    }
}

class Department {
    String name;

    public String getName() {
        return name;
    }
}