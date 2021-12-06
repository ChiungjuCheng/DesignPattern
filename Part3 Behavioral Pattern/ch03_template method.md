# Template method 

Abstrac Class 
有一個TemplateMethod 會以固定的順序呼叫各種方法來達成目的，其中會呼叫一些還不能定義的方法(可能有多種實現的方法或是還沒有辦法決定該怎麼實現)，並暫時使用abstract方法，並交給Concrete class去實作。

Concrete Class 

hook operation
是一個optional的方法，Abstrac Class有default的方法，Concrete Class 可以決定要不要使用它來達到延伸的功能。

能夠利用子類別改變abstract的行為，但不會改變abstract class的程式碼。

# UML
![Template method UML](/picture/template_method.png)

圖片來自GURU
# Template method V.S general interface implement 
這個design pattern基本上是仰賴繼承來實現的，因為不是用composite的方法，所以在決定使用這個pattern要謹慎。

通常繼承的目的是為了實現多型，讓類別之間解耦，但是這個pattern則是必須仰賴父類別的TemplateMethod，因為他已經定義了一連串固定的邏輯和方法呼叫的順序，因此當使用時，必須先確定邏輯上的順序是否為固定的。

影片 22:00

# Hollywood principle
Concrete Class定義好方法後，交給Abstrac Class來呼叫
https://www.newton.com.tw/wiki/%E5%A5%BD%E8%90%8A%E5%A1%A2%E5%8E%9F%E5%89%87

# 影片參考
https://www.youtube.com/watch?v=7ocpwK9uesw&t=2522s