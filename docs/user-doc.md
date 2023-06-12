## 登录
#### URL
```
/user/signin
```

#### Method
``` 
POST
```

#### Param
``` 
email: String
password: String
```

#### Response
###### success
```json
{
  "status": 0,
  "msg": "success",
  "data": []
}
```
###### fail
```json
{
  "status": 100001,
  "msg": "signin failed",
  "data": []
}
```

## 注册
#### URL
``` 
/user/signup
```

#### Method
```
POST
```

#### Param
``` 
username: String
password: String
email: String
department: Integer
```


## 获取已登录的用户
#### URL
``` 
/user
```

#### Method
```
GET
```

#### Param
``` 
```

#### Response
###### success
```json
{
  "status": 0,
  "msg": "success",
  "data": []
}
```
###### fail
```json
{
  "status": 100002,
  "msg": "signup failed",
  "data": []
}
```

## 退出登录
#### URL
``` 
/user/signout
```

#### Method
```
GET
```

#### Param
``` 
```

#### Response
###### success
```json
{
  "status": 0,
  "msg": "success",
  "data": []
}
```
###### fail
```json
{
  "status": 100003,
  "msg": "signout failed",
  "data": []
}
```

## 查看主页
#### URL
```
/user/profile
```

#### Method
``` 
GET
```

#### Param
``` 
```

#### Response
###### success
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "userId": 0,
    "username": "xx",
    "email": "xx",
    "organization": "xxx",
    "acceptedSubmissions": [{
      "title": "xx",
      "author": ["xx", "xx", "xx"],
      "conference": "xxx",
      "year": 2000
    }] 
  }
}
```
###### fail
```json
{
  "status": 100001,
  "msg": "signin failed",
  "data": []
}
```