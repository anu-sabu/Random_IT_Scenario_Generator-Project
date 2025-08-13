public class Main {
    public static void main(String[] args) {
        Server.startServer(8080);
        System.out.println("API is running!");
        System.out.println("Test with: curl -X POST http://localhost:8080/scenario or use postman ");
    }

}