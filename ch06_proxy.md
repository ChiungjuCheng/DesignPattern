### proxy pattern 代理人
屬於structural design pattern，在真正執行方法的物件和client端間建立一個物件，該物件擁有執行方法物件，能夠控制該物件的存取或是在真正執行方法前後去執行其他方法。

<mark>**代理物件和真正執行方法的物件會擁有相同的interface**</mark>，該代理為其他物件提供一種代理以控制對這個物件的存取[DP]。

### 應用

* Virtual proxy 虛擬代理
  若有物件需要消耗大量的資源，去執行方法並回傳結果給client端時，virtual proxy會先回傳一個結果給client端，當真正的物件執行結果完成後，再把真正的結果傳給client端。另外，若client端真正用到該物件時，virtula proxy才會去初始化這個物件。

*  Access control (protection proxy) 安全代理
用來控制該物件的存取權，例如使用proxy來過濾掉不符合權限的請求。

* remote proxy 遠端代理
  當service objce在遠端時，remote proxy可以處理關於網路上的設定，並發送請求到遠端。

* logging proxy
  紀錄每一個request

* caching proxy
  當有數個請求一直產生相同的結果時，proxy可以藉由request的參數，回傳快取，並且管理快取的生命週期。

* Smart reference 智慧參考
  proxy會定期去檢查是否有client端使用背袋裡的物件，並且記錄該物件是否有被修改過，若被代理物件沒有人使用，則proxy就有可能去銷毀該物件以釋放資源。
  