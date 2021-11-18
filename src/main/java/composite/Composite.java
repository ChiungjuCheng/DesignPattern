package composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Composite {
	public static void main(String[] args) {

		Composite compositeDemo = new Composite();

		Composite.Order order = compositeDemo.new Order();
		Composite.Paper paper = compositeDemo.new Paper();
		order.purchaseItem(paper);
		System.out.println(order.discountTotalPrice);
		System.out.println(order.totalPrice);
	}

	/** 訂單 client */
	class Order {
		// 原價
		double totalPrice = 0;
		// 折價後總價
		double discountTotalPrice = 0;
		// 擁有的Discount集合
		Discounts discounts = new Discounts();
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
			discountRule.createDiscount(this).forEach(newDiscount -> discounts.add(newDiscount));
		}

		// (若改用Item做集合，不同種類的Item可以使用不同的折價券，可以讓不同種類的Item變成各個集合)
		// 使用Discount，client不用在乎其是composite 或single object
		void applyDiscount() {
			discountTotalPrice = discounts.apply(this.totalPrice);
		}
	}

	/** 折扣 ，使用單一個component表示一個Discount */
	abstract class Discount {
		abstract double apply(double price);

		abstract void add(Discount discount);

		abstract void remove(Discount discount);
	}

	// 減去100元 leaf
	class ReduceOneHundred extends Discount {

		@Override
		double apply(double price) {
			return price - 100;
		}

		@Override
		void add(Discount discount) {
			throw new UnsupportedOperationException();

		}

		@Override
		void remove(Discount discount) {
			throw new UnsupportedOperationException();
		}
	}

	// 減去50元 leaf
	class ReduceFifty extends Discount {

		@Override
		double apply(double price) {

			return price - 50;
		}

		@Override
		void add(Discount discount) {
			throw new UnsupportedOperationException();
		}

		@Override
		void remove(Discount discount) {
			throw new UnsupportedOperationException();
		}
	}

	// composite
	class Discounts extends Discount {

		List<Discount> discounts = new ArrayList<>();

		@Override
		double apply(double originPrice) {
			double discountTotalPrice = originPrice;
			for (Discount discount : discounts) {
				discountTotalPrice = discount.apply(discountTotalPrice);
			}

			return discountTotalPrice;
		}

		@Override
		void add(Discount discount) {
			this.discounts.add(discount);

		}

		// 暫時忽略實現equals
		@Override
		void remove(Discount discount) {
			this.discounts.remove(discount);

		}

	}

	// 依照目前的Price或規則回傳Discount
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

}
