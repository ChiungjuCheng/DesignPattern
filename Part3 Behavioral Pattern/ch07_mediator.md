# Mediator 中介者模式
將物件間的互動封裝到mediator，避免物件互相參考，物件間只知道mediator的存在，並利用mediator互動。

# UML
![mediator UML](/picture/mediator.png)

**Mediator**  
定義一個介面，讓Colleague之間用溝通。  
**ConcreteMediator**  
協調每個Colleague物件，並且知道如何存取。  
**Colleague classes**  
Colleague透過Mediator與另一個Colleague溝通，否則是和另一個Colleague溝通。


[圖片參考](https://ithelp.ithome.com.tw/articles/10225660)