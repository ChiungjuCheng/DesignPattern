# Command 命令模式 指令模式
是一個行為行設計模式。能夠把請求(command)變為一個物件，該物件含有這個請求地所有資訊。這樣子就可以把該請求傳入方法內，並且控制該請求是否要延後、等待或是取消執行。


# 介紹
命令模式能夠把請求操作物件(即呼叫實作商業邏輯的物件方法)和該物件解耦。不讓應用程式本身去操作物件。能夠解耦sender和receiver，sender是指一個物件，能夠觸發執行運算，而reciver則是能夠接受請求並觸發運算。

命令模式包含以下類別
Invoker (sender): 接受command，擁有0-n個command
Command : 介面，有很多實現的介面，並擁有一個Receiver
Receiver : 真正執行邏輯的物件

![command](/picture/command.png)

實作參考網址
https://www.tutorialspoint.com/design_pattern/command_pattern.htm