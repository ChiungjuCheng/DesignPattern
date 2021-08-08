# 原型模式 (Prototype pattern)
Also known as: Clone
複製已經存在的物件，不用重複呼叫該類別的contructor和各個setter

### 問題
複製時需要將物件的Filds複製給另一個物件，但有些物件的Filds是屬於private或是protected的，外界是無法存取的，另外一個問題是當需要複製時，我們必須知道該物件的實體類別，但如果物件來自於factory或是builder，要真的知道該物件類別會多很麻煩，需要再另外映射出來，且會讓使用者自己的程式碼與該物件類別產生依賴。

### 解決方法 - clone
在java中，要有clone方法的類別需要去繼承clone並且override它，若沒有overrivde則會拋出例外，但其實也可以不使用該interface也能實現clone。

首先需要產生一個可以被clone的interface，並且鑽寫具體類別，最重要的是該類別需要改寫clone，內容則是要回傳一個複製後的物件。

TODO 一樣用shape好了

```java

```