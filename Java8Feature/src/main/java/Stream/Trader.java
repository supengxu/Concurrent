package Stream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Trader {

    public static void main(String[] args) {
        List<Transaction> transactions = null;
        Trader1 raoul = new Trader1("Raoul", "Cambridge");
        Trader1 mario = new Trader1("Mario", "Milan");
        Trader1 alan = new Trader1("Alan", "Cambridge");
        Trader1 brian = new Trader1("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(alan , 2012, 950));

        // ①找出2011年发生的所有交易， 并按交易额排序（从低到高）
        // 方式一：
        Long begin = System.currentTimeMillis();
        List<Transaction> newTr = transactions.stream()
                .filter(tran -> tran.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        Long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - begin) + " " + newTr);

        // 方式二: 差距是35倍左右！
        Long begin2 = System.currentTimeMillis();
        List<Transaction> collect = transactions.stream()
                .filter(tran -> tran.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        Long end2 = System.currentTimeMillis();

        System.out.println("耗时: " + (end2 - begin2) + " " + collect);

        // ②交易员都在哪些不同的城市工作过？
        // 方式一:
//        transactions.stream()
//                .filter(StreamUtil.distinctByKey(tran -> tran.getTrader().getCity()))
//                .collect(Collectors.toList())
//                .forEach(t -> System.out.println(t.getTrader().getCity()));

        // 方式二:
        List<String> collCityTwo = transactions.stream()
                .map(e -> e.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("城市为: " + collCityTwo);

        //③查找所有来自剑桥的交易员，并按姓名排序
        List<Trader1> collPerson = transactions.stream().filter(tran -> tran.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader1::getName))
                .collect(Collectors.toList());
        System.out.println(collPerson);

        // ⑤有没有交易员是在米兰工作的？
        long count = transactions.stream().filter(tran -> tran.getTrader().getCity().equals("Milan")).count();
        System.out.println("是否有人在米兰工作: " + (count > 0));

        // ⑥打印生活在剑桥的交易员的所有交易额总和
        int sum = transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println("总额为: " + sum);

        // ⑦所有交易中，最高的交易额是多少
        int max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .getAsInt();
        System.out.println("最大值是: " + max);

        // ⑧找到交易额最小的交易
        Transaction transaction = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .get();
        System.out.println("最小值交易是: " + transaction);
    }
}


class Trader1 {
    private String name;
    private String city;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "Trader [name=" + name + ", city=" + city + "]";
    }
    public Trader1(String name, String city) {
        super();
        this.name = name;
        this.city = city;
    }
}

class Transaction {
    private Trader1 trader1;
    private int year;
    private int value;
    public Trader1 getTrader() {
        return trader1;
    }
    public void setTrader(Trader1 trader1) {
        this.trader1 = trader1;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "Transaction [trader=" + trader1 + ", year=" + year + ", value=" + value + "]";
    }
    public Transaction(Trader1 trader1, int year, int value) {
        super();
        this.trader1 = trader1;
        this.year = year;
        this.value = value;
    }
}
