# Composite 組合模式
宣告一個介面，並由composit class和single object class 去實現，而使用者統一使用該頁面做操作，並不會察覺自己使用的是哪個class。

# Tree structure object
composite物件含有composite物件，整個構造就像一棵樹一樣。呼叫一個物件後，該物件會深入去呼叫其有的物件擁有的物件方法，直到真的實現方法的物件，當結果回傳後，composite物件會立即做出處哩，並回傳給呼叫他的應用程式。
composite物件本身並不用在意其呼叫的物件是否為composite，他只要專心去使用其擁有的物件們，並計算全部物件回傳的結果後再回傳給應用程式。

除了讓每個類別繼承相同的類別或介面外，可以讓類別implement不同的介面來區分有其為internal node和leaf。

# Compsite vs Decorator vs Chain

17:02
https://www.youtube.com/watch?v=EWDmWbJ4wRA