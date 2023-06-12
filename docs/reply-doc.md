## 查看审稿意见
#### URL
```
/reply/view/{conferenceId}/{submissionId}
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
    "initialScore": [0, 0, 0],
    "initialComments": [["xx", "xx"], ["xx", "xx"], ["xx", "xx"]],
    "finalScore": [0, 0, 0],
    "finalComments": ["xxxxx", "xxxx", "xxxx"]
  }
}
```
###### fail
```json
{
  "status": 500001,
  "msg": "get comments detail failed",
  "data": []
}
```

## 答复
#### URL
```
/reply/{conferenceId}/{submissionId}
```

#### Method
``` 
POST
```

#### Param
``` 
rebuttal: List<List<String>>
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
  "status": 500002,
  "msg": "reply failed",
  "data": []
}
```