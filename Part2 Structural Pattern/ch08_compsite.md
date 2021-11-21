# Composite 組合模式
1. Compose objects into tree structure to represent part-whole hierarchies. Composite lets client treat individual objects and compositions of objects uniformly[Gof]。
2. 宣告一個介面，並由composit class和single object class去實現，而使用者統一操作該介面，並不會察覺自己使用的是composit或single。
# Tree structure object
composite物件含有composite物件，整個構造就像一棵樹一樣。呼叫一個物件後，該物件會深入去呼叫其有的物件擁有的物件方法，直到真的實現方法的物件，當結果回傳後，composite物件會立即做出處哩，並回傳給呼叫他的應用程式。
composite物件本身並不用在意其呼叫的物件是否為composite，他只要專心去使用其擁有的物件們，並計算全部物件回傳的結果後再回傳給應用程式。

除了讓每個類別繼承相同的類別或介面外，可以讓類別implement不同的介面來區分其為internal node和leaf。
Composite需要注意物件間組合成的順序。

# 問題

# UML
![CompsitePattern](/picture/composite.png)

截圖Guru
# Demo

# 應用時機


# Compsite vs Decorator vs Chain
Compsite是把各種物件的行為組裝起來，Decorator是擴充物件的行為，chain讓多個物件都有機會處理某一訊息。

# Real world examples
Container
https://github.com/frohoff/jdk8u-jdk/blob/master/src/share/classes/java/awt/Container.java
Component
https://github.com/frohoff/jdk8u-jdk/blob/master/src/share/classes/java/awt/Component.java
Component是一個java類別，其底下有各種實現不同功能的subclass，其中有個invalidate()會檢查其ancestors，在每次移除或添加Component時都會被呼叫。而Container繼承Component，裏頭有個Component的集合。

參考資料
https://codinghelmet.com/articles/reduce-cyclomatic-complexity-composite-design-pattern
https://refactoring.guru/design-patterns/composite
https://www.youtube.com/watch?v=EWDmWbJ4wRA