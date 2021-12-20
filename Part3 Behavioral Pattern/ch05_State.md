# State 狀態模式
能夠讓物件隨著自身狀態改變而更換行為。

# 問題
1. 一個物件的行為在run-time的時候會因為自身狀態不同而改變，每一次呼叫方法都要確認一次物件的狀態，造成確認的狀態的程式重複
2. 有大量的判斷條件依賴著物件的狀態，當要增加新的狀態時，變得難以維護
3. 物件目前的狀態可能無法轉換成某些狀態

# UML
![State UML](/picture/state.png)

**Context**  
擁有一個state物件，負責藉由state interface調用state的方法。有一個public的setter，可以傳入新的state物件來改變狀態。
  
**Concrete States**  
可以擁有一個Context物件參考，以便拿到需要的資訊來初始化state transitions。

**Transitions**  
轉換狀態的規則，也是屬於有限和可以預先決定的，context和states都可以設定context的下一個狀態和執行Transitions。

# Demo  
TODO