# Bridge pattern 橋接模式
一種結構型模式，將一個大類別或是一系列相關的類別拆成兩條繼承關係(hierarchies)。在一個抽象類別或介面中，擁有多種不同的子類別，若新增一個新功能到抽象或介面中，則需要可能需要改寫原本既有的子類別和新增類別。Bridge pattern把繼承轉為Object composition，將某個功能切割成另一個類別，並使原本的類別能夠擁有該物件參考，這樣能夠降低抽象類別(或介面)與實作類別的耦合性。


https://refactoring.guru/design-patterns/bridge
