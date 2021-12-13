# Iterator 迭代器模式
提供一種方法遍歷(traversal)Container中各個元素，但不會暴露出Container的結構，使得Container與遍歷方法解耦。

# 問題
有許多不同的資料結構，擁有不同遍歷的方式，當 traversal algorithms越來越多時會產生以下的問題:
* 模糊Collection負責儲存資料的責任。
* 有些traversal algorithms適用於一些特殊的應用，因此不適合將其放入泛型的class。
* 不同Collection提供不同的traversal algorithms，會使得Client的業務邏輯和特定的collection類別耦合。

# UML
![Iterator UML](/picture/iterator.png)

**Iterator Interface**
需要包含以下方法:  
1. 依序取得Container裡面的元素
2. 取得目前遍歷到的位置
3. 重新開始遍歷

**Concrete Iterators**
為特定的collection實現一種traversal algorithms，iterator必須要能夠追蹤自身遍歷的進度，這樣子能夠讓相同collection的iterators相互獨立。

**Collection and Concrete Collections**
必須提供一個到N個拿到iterators的方法。

[Guru](https://refactoring.guru/design-patterns/iterator)

# Demo  
TODO
# 優點
1. 不會暴露出Collection的結構，Iterator只要Client端呼叫該方法，就會給出下一個元素。

# Real world Source Code 
TODO 
java.util.Iterator