### Strategy pattern 策略模式
策略模式是一種行為型設計模式。定義一個使用者可以操作的介面，並讓不同的類別用不同的演算法去實現這個介面，使用者只需要傳入特定的物件就能夠替換掉不同的演算法。

modern java in action裡面的behavior parameterization，就類似這個觀念，傳入特定的code避免過多的if-else判斷句。Guru也有提到，某些程式語言是First-class Function，就可以做到這個設計模式想要達成的概念: 傳入參數以控制要執行的演算法，因此應避免使用策略模式產生不必要的介面或是類別。