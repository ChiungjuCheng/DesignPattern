# Memento 備忘錄模式
能夠在不顯示物件實作的情況下，儲存或復原物件的前一個狀態。

# 問題
當物件要回到某個狀態時，物件的內容會暴露於用戶端，且讓用戶端職責過多，需要知道該物件的值。
# UML
![memento UML](/picture/memento.png)

**Memento**  
originator狀態的snapshot。擁有兩種介面，Caretaker使用窄介面，只能將Memento傳給其他物件，Originator則使用寬介面，能夠對Memento做存取。
**Originator**  
主要功能物件，能藉由產生snapshot儲存自身狀態，也能透過snashots回復狀態。  
**Caretaker**  
Caretaker知道甚麼時候和為什麼要捕捉Originator的狀態。Caretaker能夠儲存Originator的歷史狀態，並在originator需要回朔以前狀態時，取出以stack保存的歷史狀態，並且將其傳入Originator的回復方法中。
可以注意的是，Caretaker只保有儲存和取出Memento的邏輯，它不能set或get Memento。