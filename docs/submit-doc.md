## 摇号
#### URL
```
/conference/submit/{conferenceId}/addSubmission
```

#### Method
``` 
POST
```

#### Param
``` 
title: String
abstract: String
authors: List<String>
```

#### Response
###### success
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "submissionId": 0
  }
}
```
###### fail
```json
{
  "status": 300001,
  "msg": "add submission failed",
  "data": []
}
```

## 提交或更新
#### URL
```
/conference/submit/{conferenceId}
```

#### Method
``` 
POST
```

#### Param
``` 
submissionId: Integer
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
/conference/withdraw/{conferenceId}/{submissionId}
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
