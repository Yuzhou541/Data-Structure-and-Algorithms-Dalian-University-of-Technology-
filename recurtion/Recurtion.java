package recurtion;

public class Recurtion {
    public static void main(String[] args) {
        System.out.println("fibonacci: ");
        System.out.println(new Recurtion().fibonacci(4));

        System.out.println("Hanoi Tower: ");
        new Recurtion().HanoiTower(3, 'a', 'b', 'c');
    }

    // Fibonacci: F0=1, F1=1, F2=2,... 
    int fibonacci(int num){
        if(num == 0)    return 1;
        if(num == 1)    return 1;
        return fibonacci(num - 1) + fibonacci(num - 2);
    }

    // Hanoi Tower
    void HanoiTower(int num, char from, char aux, char to){
        if(num == 1){
            System.out.printf("Move disk 1 from %c to %c\n", from, to);
            return;
        }
        HanoiTower(num - 1, from, to, aux);
        System.out.printf("Move disk %d from %c to %c\n", num, from, to);
        HanoiTower(num - 1, aux, from, to);
    }
}
