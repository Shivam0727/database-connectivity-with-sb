
# Spring Boot REST API with JPA and MySQL Connectivity

This README file provides step-by-step instructions for creating a Spring Boot project that includes REST API endpoints for CRUD operations (Create, Read, Update, Delete). Additionally, the project will utilize JPA for data persistence and MySQL as the database. The guide will also cover configuring the MySQL database connection in the application.properties file and adding the MySQL Connector JAR file to the Eclipse project build path.

## Prerequisites

Make sure you have the following installed before starting the project:

### Java Development Kit (JDK)
### Eclipse IDE (or any preferred IDE)
### MySQL Database Server

## Step 1: Create a Spring Boot Project
  
- Open Eclipse IDE and go to File -> New -> Spring Starter Project.
- 
- Enter the project name, select the desired package, and choose the required dependencies: Spring Web, Spring Data JPA, and MySQL Driver.
- 
- Click Finish to create the project.

## Step 2: Configure MySQL Database

Open the src/main/resources/application.properties file and configure the MySQL database connection properties:

    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.hibernate.ddl-auto=update

Replace your_database_name, your_username, and your_password with your actual MySQL database details.

## Step 3: Implement REST API Endpoints

Create a package for your controllers (e.g., com.example.demo.controller) and create a class for your REST API endpoints:

        @RestController
        @RequestMapping("/api/items")
        public class ItemController {

        @Autowired
        private ItemRepository itemRepository;

        @GetMapping
        public List<Item> getAllItems() {
            return itemRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Item> getItemById(@PathVariable Long id) {
            Optional<Item> optionalItem = itemRepository.findById(id);
            return optionalItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public Item createItem(@RequestBody Item item) {
            return itemRepository.save(item);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
           
            Optional<Item> optionalItem = itemRepository.findById(id);
        
            if (optionalItem.isPresent()) {
                Item existingItem = optionalItem.get();
                existingItem.setName(updatedItem.getName());
                existingItem.setDescription(updatedItem.getDescription());
                // Update other fields as needed
                return ResponseEntity.ok(itemRepository.save(existingItem));
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
            if (itemRepository.existsById(id)) {
                itemRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
    
## Step 4: Add External MySQL JAR to Eclipse Build Path

- Download the MySQL Connector JAR file from the official MySQL website.
  
- In Eclipse, right-click on your project and select Build Path -> Configure Build Path.
  
- Go to the Libraries tab, click Add External JARs, and select the downloaded MySQL Connector JAR file.
  
- Click Apply and Close to save the changes.
  
Now, you have a Spring Boot project with REST API endpoints, JPA for data persistence, and MySQL as the database. The MySQL Connector JAR is added to the Eclipse build path, and the database connection details are configured in the application.properties file. You can further customize the project based on your specific requirements.
