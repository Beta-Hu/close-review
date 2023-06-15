[//]: # (## 摇号)

[//]: # (#### URL)

[//]: # (```)

[//]: # (/conference/submit/{conferenceId}/addSubmission)

[//]: # (```)

[//]: # ()
[//]: # (#### Method)

[//]: # (``` )

[//]: # (POST)

[//]: # (```)

[//]: # ()
[//]: # (#### Param)

[//]: # (``` )

[//]: # (title: String)

[//]: # (abstract: String)

[//]: # (authors: List<String>)

[//]: # (```)

[//]: # ()
[//]: # (#### Response)

[//]: # (###### success)

[//]: # (```json)

[//]: # ({)

[//]: # (  "status": 0,)

[//]: # (  "msg": "success",)

[//]: # (  "data": {)

[//]: # (    "submissionId": 0)

[//]: # (  })

[//]: # (})

[//]: # (```)

[//]: # (###### fail)

[//]: # (```json)

[//]: # ({)

[//]: # (  "status": 300001,)

[//]: # (  "msg": "add submission failed",)

[//]: # (  "data": [])

[//]: # (})

[//]: # (```)

## 创建、提交或更新
#### URL
```
/submit/{conferenceId}
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
