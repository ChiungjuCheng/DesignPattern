# Visitor 訪問者模式
表示一個作用於某物件個元素的運算。能夠在不改變類別結構下，增加對該類別的運算。  
將運算傳入某類別結構裡面後，該類別會將自身傳入該運算方法。

# 問題
需要在某個類別的子類別們增加多個不同種類的運算，依照該類別或其狀態來決定要施行哪個運算，這個過程會造成原本的子類別結構被破壞，和與各個不同的操作耦合。  
例如有隻鳥，會依照他的狀態來執行不一樣的dosomething方法，若有增加狀態則需要在方法內再多增加判斷，若是有其他運算需求，則還要增加方法。
```java
class Bird{

    State state;
    public void doSomething(){
        if(state == "sleeping"){
            // .....
        }else{
            // ....
        }
    }
}

```

# UML
![Visitor UML](/picture/visitor.png)

**Visitor**  
能宣告多個能將物件結構的元素當作參數傳入的方法。  
會有多個visit方法，若程式語言支援overloading，可以使用相同的名稱。
  
**Element**  
必須宣告一個可以傳入visitor的方法  
  
**Concrete Element**
必須要實作接受visitor的方法，主要功能是重導回呼叫visitor的方法，必須注意的是，就算一個base element這個方法，其子類別還是需要override它，才能呼叫到正確的visitor方法。  
  
**Client**  
client端通常會使用到的是collection或是一些更複雜的物件，例如composit tree。使得client不會查覺到所有的element實作類別。