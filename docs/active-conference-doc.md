## 列举全部会议
#### URL
```
/conference/list
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
    "id": 0,
    "name": "xx xxxx xxx",
    "acronym": "XX",
    "year": 2000
  }, {}]
}
```
###### fail
```json
{
  "status": 200001,
  "msg": "failed to list conferences",
  "data": []
}
```

## 列举特定会议的录用结果
#### URL
```
/conference/{conferenceId}
```

#### Method
``` 
GET
```

#### Param
``` 
conferenceId: Integer
```

#### Response
###### success
```json
{
  "status": 0,
  "msg": "success",
  "data": [{
    "id": 0,
    "title": "xx xxxx xxx",
    "author": [{
      "username": "xx",
      "emial": "xxx",
      "organization": "xxx"
    }]
  }, {}]
}
```
###### fail
```json
{
  "status": 200002,
  "msg": "failed to list articles in this conference",
  "data": []
}
```

## 查看录用文章详情
#### URL
```
/submission/{submissionId}
```

#### Method
``` 
GET
```

#### Param
``` 
conferenceId: Integer
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
  "status": 200003,
  "msg": "failed to open this submission",
  "data": []
}
```
