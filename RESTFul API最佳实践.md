# RESTful API 最佳实践
RESETful 是目前最流行的 API 设计规范，用于 Web 数据接口的设计。

## 一、URL 设计
RESTful 的核心思想就是，客户端发出的数据操作指令都是“动词 + 宾语”的结构。比如，GET /articles，GET 是动词，/articles 是宾语。  
### 1.1 动词 + 宾语
动词通常就是五种 HTTP 方法，对应 CRUD 操作。
1. GET: Read
2. POST: Create
3. PUT: Update
4. PATCH: Update，通常是部分更新
5. DEL: Delete

### 1.3 宾语必须是名词
宾语就是 API 的 URL，是 HTTP 动词作用的对象。它应该是名词，不能是动词。

### 1.4 复数 URL
既然 URL 是名词，那么应该是单数还是复数？  
这没有统一的规定，但是常见的操作是读取一个集合，比如读取所有文章名 GET /articles，这里应该是复数。  
为了统一起见，建议都使用复数 URL，比如 GET /articles/2。

### 1.5 避免多级 URL
常见的情况是自由需要多级分类，容易写出多级 URL，比如获取某个作者的某一类文章。  
> GET /authors/12/categories/2

这种 URL 不利于扩展，语义也不明确，更好的做法是，除了第一级，其他级别都用查询字符串表达。  
> GET /authors/12?categories=2  
GET /articles?published=true

## 二、状态码
### 2.1 状态码必须精确
HTTP 状态码分为五个类别：
1. 1xx：相关信息
2. 2xx：操作成功
3. 3xx：重定向
4. 4xx：客户端错误
5. 5xx：服务器错误

### 2.2 2xx 状态码
GET：200 OK  
POST: 201 Created  
PUT：200 OK  
PATCH：200 OK  
DELETE：204 No Content  
202 Accept 表示服务器收到请求但还未处理，会在将来处理，常用于异步操作。  

### 2.3 3xx 状态码
303：See Other，表示参考另一个 URL。和302/307不同，浏览器不好自动跳转。  

### 2.4 4xx 状态码
400：Bad Request  服务器不理解客户端请求，未做处理。  
401：Unauthorized  用户身份认证未通过。  
403：Forbidden  通过了身份认证，但不具有访问资源的权限。  
404：Not Found  请求的资源不存在。  
405：Method Not Allowed  HTTP 方法不再权限之内。  
410：Gone  资源已转移，不可用。  
415：Unsupported Media Type  客户端要求返回的格式不支持。  
422：Unprocessable Entity  上传的附件无法处理，请求失败。  
429：Too Many Requests  客户端请求次数超过限额。  

### 5xx 状态码
500：Internal Server Error  服务器处理时发生了异常。  
503：Service Unavailable  服务器无法处理请求，常用于网站维护状态。  

