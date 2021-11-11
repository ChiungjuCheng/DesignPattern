# Bridge pattern 橋接模式
主要功能是降低抽象類別和實作類別的耦合性。
一種結構型模式，將一個大類別或是一系列相關的類別拆成兩條繼承關係(hierarchies)。在一個抽象類別或介面中，擁有多種不同的子類別，若新增一個新功能到抽象或介面中，則需要可能需要改寫原本既有的子類別和新增類別。Bridge pattern把繼承轉為Object composition，將某個功能切割成另一個類別，並使原本的類別能夠擁有該物件參考，這樣能夠降低抽象類別(或介面)與實作類別的耦合性。

# 問題
Cartesian product 

# Bridge pattern UML
![bridge pattern](/picture/bridge.png)

1. Client操作Abstraction
2. Abstraction提供業務邏輯，並調派(delegate)implemntation執行
3. Implementation 則是提供介面給所有的實作類別，而一個Abstraction只能使用一個implementation的實作類別。
4. Advanced Abstraction 若有不同的業務邏輯需求，則可以再讓一個新的類別繼承Abstraction，其繼承的分支和Implementation的繼承分支相互獨立。
# Demo
```java
public interface 
```


# Bridge v.s Adapter

參考資源
https://www.youtube.com/watch?v=F1YQ7YRjttI&t=29s
https://refactoring.guru/design-patterns/bridge
https://stacktraceguru.com/bridge-design-pattern/