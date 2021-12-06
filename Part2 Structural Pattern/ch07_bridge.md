# Bridge pattern 橋接模式
主要功能是降低抽象類別和實作類別的耦合性。
將一個大類別或是一系列相關的類別拆成兩條繼承關係(hierarchies)。在一個抽象類別或介面中，擁有多種不同的子類別，若新增一個新功能到抽象或介面中，則需要可能需要改寫原本既有的子類別和新增類別。Bridge pattern把繼承轉為Object composition，將某個功能切割成另一個類別，並使原本的類別能夠擁有該物件參考，這樣能夠降低抽象類別(或介面)與實作類別的耦合性。

# 問題
假設某個作業系統A，擁有一個遊戲「小朋友下樓梯」和行事曆A，而另一個作業系統B，擁有另一個遊戲「小朋友上樓梯」和行事曆B。此時依照繼承的概念，就會產生2*2個實作類別，如下圖，若再增加，新功能或是系統，實作類別就會增加(Cartesian product問題)。

```java
interface OS {
    String name;
    doSomething();
}

interface AOS implements OS{
    ...
}

interface BOS implements OS{
    ...
}

// 實作類別
class Game1AOS implements AOS{
    ...
}

class Schedule1AOS implements AOS{
    ...
}

class Game2BOS implements BOS{
    ...
}

class Schedule2AOS implements BOS{
    ...
}

```

# Bridge pattern UML
![bridge pattern](/picture/bridge.png)

1. Client操作Abstraction
2. Abstraction提供業務邏輯，並調派(delegate)implemntation執行
3. Implementation 則是提供介面給所有的實作類別，而一個Abstraction只能使用一個implementation的實作類別。
4. Advanced Abstraction 若有不同的業務邏輯需求，則可以再讓一個新的類別繼承Abstraction，其繼承的分支和Implementation的繼承分支相互獨立。



# Demo
implementation : 將遊戲和行事曆等功能從原本的類別當中獨立出來，並繼承Sofware
```java
interface Software {
    doSomething();
}

class Game1 implements Software{
    ...
}

class Schedule1 implements Software{
    ...
}

```
Abstraction : 讓原本Software is A OS，轉換成 OS has A Software。

```java
interface OS {
    String name;
    Software software;
    doSomething();
}

class AOS implements OS {

    AOS(Software software)

    doSomething(){
        this.software.doSomething();
    }
}

```
Client : 使用者可以在Runtime時期決定要使用哪一個Software
```java

OS aOS1 = new AOS(new Game1());
aOS1.doSomething();

OS aOS2 = new OS(new Schedule1());
aOS2.doSomething();
```

參考大話設計模式。

# 應用
1. 跨平台

# Bridge v.s Adapter

參考資源  
https://www.youtube.com/watch?v=F1YQ7YRjttI&t=29s
https://refactoring.guru/design-patterns/bridge
https://stacktraceguru.com/bridge-design-pattern/