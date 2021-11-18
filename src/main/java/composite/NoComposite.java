package composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoComposite {

	public static void main(String[] args) {
		Order order = new Order();
		order.purchaseItem(new Paper());
		System.out.println(order.discountTotalPrice);
		System.out.println(order.totalPrice);
	}

}

/** 訂單 client */
class Order {
	// 原價
	double totalPrice = 0;
	// 折價後總價
	double discountTotalPrice = 0;
	// 擁有的Discount集合
	List<Discount> discountList = new ArrayList<>();
	// 拿Discount的規則
	DiscountRule discountRule = new DiscountRule();

	void purchaseItem(Item item) {
		totalPrice += item.price;
		// 確認有沒有新的折價券
		checkNewDiscount();
		// 計算折價後的$$
		applyDiscount();
	}

	void checkNewDiscount() {
		discountRule.createDiscount(this).forEach(newDiscount -> discountList.add(newDiscount));
	}

	// (若改用Item做集合，不同種類的Item可以使用不同的折價券，可以讓不同種類的Item變成各個集合)
	// 計算折價 必須要跑迴圈去處理所有折價券，使用composit就可以不用使用這個迴圈
	void applyDiscount() {
		double discountTotalPrice = this.totalPrice;
		for (Discount discount : discountList) {
			discountTotalPrice = discount.apply(discountTotalPrice);
		}
		this.discountTotalPrice = discountTotalPrice;
	}
}


/** 折扣 ，使用單一個component表示一個Discount */
abstract class Discount {
	abstract double apply(double price);
}


// 減去100元
class ReduceOneHundred extends Discount {

	@Override
	double apply(double price) {
		return price - 100;
	}
}

// 減去50元
class ReduceFifty extends Discount {

	@Override
	double apply(double price) {

		return price - 50;
	}
}

//依照目前的Price或規則回傳Discount
class DiscountRule {

	Set<String> unUsedDiscounts = new HashSet<>(Arrays.asList("ReduceOneHundred", "ReduceFifty"));

	// 簡單做一下...有不合邏輯但先忽略
	List<Discount> createDiscount(Order order) {
		double totalPrice = order.totalPrice;
		List<Discount> list = new ArrayList<>();
		if (totalPrice > 50 && unUsedDiscounts.contains("ReduceFifty")) {
			unUsedDiscounts.remove("ReduceFifty");
			list.add(new ReduceFifty());
		}

		if (totalPrice > 200 && unUsedDiscounts.contains("ReduceOneHundred")) {
			unUsedDiscounts.remove("ReduceOneHundred");
			list.add(new ReduceOneHundred());
		}

		return list;
	}
}

/** 物品 */
abstract class Item {
	public double price = 0;

}

class Paper extends Item {
	public Paper() {
		this.price = 1000;
	}
}
