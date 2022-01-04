# 設計模式介紹
設計模式是用來解決撰寫程式時遇上的問題，他是一個概念，你是不能完全照抄的，只能利用該設計模式的概念，和細節，來實現出適合你現行正在鑽寫的coding的解決方法。

### SOLID
設計模式其實就是物件導向原則的實踐，遵守這些原則去避免增加程式碼的開發或維護的成本。  

**Single-responsiblity Principle**  
一個類別只能有一個理由去做變更，表示該類別只能擁有一個職責。  

**Open-Closed Principle**  
一個類別必須要能夠不修改自身而擴展功能。  

**Liskov Substitution Principle**  
當子類別替換到父類別時，其功能不會受到影響。  

**Interface Segregation Principle**  
不能強迫client端去實現它用不到的介面，或是強迫client依賴於它用不到的方法。  

**Dependency Inversion Principle**
1. 高層次的模組不應該依賴於低層次的模組，兩者都應該依賴於抽象介面。  
2. 抽象介面不應該依賴於具體實現。而具體實現則應該依賴於抽象介面。
### 缺點
設計模式有其缺點，若使用者選擇使用一個缺少抽象層面的語言或技術，就會增加對於設計模式的需求，此時他就會變成kludge。
或是當他只變成為了符合規定而未適用其自身專案時，可能會造成效能的下降。
最後則是有可能因為使用錯誤而導致效能降低。

### 設計模式分類
1. Creational patterns : 提供創造物件的機制，使其物件能夠重複使用或是增加既有層式的彈性。
2. Structual patterns: 解釋如何讓物件集結起來，並且在較大的結構中使用，同時保持該結構的彈性和效能。
3. Behaviorl pattern: 維護物件之間的溝通，以及職責的分配。

出自Guru網頁
Remember that a pattern is more than just a certain way to structure your classes. It may also communicate intent and a problem being addressed.

### java 實例
https://stackoverflow.com/questions/1673841/examples-of-gof-design-patterns-in-javas-core-libraries
參考:
https://refactoring.guru/design-patterns/classification

