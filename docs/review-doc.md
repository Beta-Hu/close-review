## 查看等待审阅的列表
#### URL
```
/review/list/{conferenceId}
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
  "data": [{
    "submissionId": 0,
    "title": "xxx"
  }]
}
```
###### fail
```json
{
  "status": 400001,
  "msg": "list submission (to be review) failed",
  "data": []
}
```

## 查看提交详情
#### URL
```
/review/view/{conferenceId}/{submissionId}
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
    "submissionId": 0,
    "title": "xxx",
    "abstract": "xxxx",
    "file": "file://xx.pdf",
    "supportMaterial": "file://xxxx.pdf",
    "initialScore": 0,
    "initialComments": ["xx", "xx"],
    "finalScore": 0,
    "finalComments": "xxxxx"
  }
}
```
###### fail
```json
{
  "status": 400002,
  "msg": "check submission details failed",
  "data": []
}
```

## 初审
#### URL
```
/review/initial/{conferenceId}/{submissionId}
```

#### Method
``` 
POST
```

#### Param
``` 
initialScore: Integer
initialComments: List<String>
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
  "status": 400003,
  "msg": "submit initial review failed",
  "data": []
}
```

## 终审
#### URL
```
/review/final/{conferenceId}/{submissionId}
```

#### Method
``` 
POST
```

#### Param
``` 
finalScore: Integer
finalComments: String
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
  "status": 400003,
  "msg": "submit initial review failed",
  "data": []
}
```

