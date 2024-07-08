package exam04;

public class Order implements Seller, Buyer {
    @Override
    public void buy() {
        System.out.println("구매");
    }

    @Override
    public void sell() {
        System.out.println("판매");
    }

    public void order() {
        Buyer.super.order();
        // 헷갈리지 않도록 명확하게 정의하는 것이 필요
    }
}
