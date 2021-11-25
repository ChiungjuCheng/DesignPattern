# Flyweight 享元模式
運用共用技術有效地支援大量細粒度的物件[DP]。  
多個物件共享同一份狀態，利用共享節省RAM的空間。  
這個設計模式僅適用於當有大量物件造成RAM過多消耗時適用，需要確定問題沒有更有意義的解決方式後才適用flyweight。
# 問題

# UML
![flyweight](/picture/flyweight.png)


**Flyweight class**  
擁有物件部分的狀態，這些狀態是可以被其他物件共用的，因此同樣一個Flyweight 物件可以被多個Context使用，存在Flyweight中不會變動的狀態稱為<mark>intrinsic</mark>，傳入flyweight方法的可變動的狀態則是<mark>extrinsic</mark>。  

<br>

**Flyweight Factory**  
負責管理flyweight物件群，依照傳入的參數來創造和回傳該物件，或是直接回傳已經創建好的物件。

<br>

**Context**  
擁有extrinsic state(是每個物件自己擁有的)，會和一個flywight物件成對，以成為一個擁有完整state的物件(full state of the original object)。  

**Context operation**
通常original object的行為會包在flyweight方法內，當要使用該方法時只要傳入extrinsic state即可。

**Client端**
負責計算或儲存需要的extrinsic state，對於使用者來說flyweight物件是個在Rumtime中傳入各種狀態(contextual data )到他的方法的template物件。


資料參考  
https://refactoring.guru/design-patterns/flyweight

# Demo

# 優缺點
* 可能在呼叫flyweight method時，有些contextual data需要經過複雜的計算，而造成CPU過量使用。
* CODE變得複雜，讓人難以理解為什麼一個物件要被拆成兩個狀態。 


#  Flyweight V.S. Cache
Flyweight目的為降低RAM的使用量，Cache則是為了降低運算時間