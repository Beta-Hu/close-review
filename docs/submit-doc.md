## 查看
#### URL
``` 
/submission/submitting/{submissionId}
```

#### Desc
``` 
查看提交的submission。
```

#### Method
```
GET
```

#### Param
``` 
submissionId: Integer
```

#### Response
###### success
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "id": 0,
    "title": "xx xxxx xxx",
    "abstracts": "xxx",
    "corresponding": 0,
    "author": ["xx", "xx", "xx"],
    "file": "file://xxx.pdf",
    "supportMaterial": "file://xxx.pdf",
    "conference": "xxxx",
    "comments": [{
      "reviewer": "xx",
      "initialComments": ["xx", "xx"],
      "rebuttal": ["xx", "xx"],
      "initialScore": 0.0,
      "finalComments": "xxx",
      "finalScore": 0.0
    }]
  }
}
```
###### fail
```json
{
  "status": 300001,
  "msg": "permission died",
  "data": []
}
```

## 创建、提交或更新
#### URL
```
/submission/submitting/{submissionId}/submit
```

#### Desc
``` 
在active会议中创建一个submission。首次创建时，submissionId为-1，提交后服务器返回正式的submissionId。提交后跳转回submission列表页面
```

#### Method
``` 
POST
```

#### Param
``` 
submissionId: Integer / none
title: String
abstract: String
authors: List<String>
file: File
supportMaterial: File
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
  "status": 300002,
  "msg": "submit failed",
  "data": []
}
```

## 删除或撤稿
#### URL
```
/withdraw/{conferenceId}/{submissionId}
```

#### Desc
``` 
撤除一个submission。撤除后返回submission列表
```

#### Method
``` 
GET
```

#### Param
```
submissionId: Integer
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
  "status": 300003,
  "msg": "withdraw failed",
  "data": []
}
```
