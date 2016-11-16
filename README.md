[![JitPack](https://img.shields.io/github/tag/yongjhih/gson-adapters.svg?label=JitPack)](https://jitpack.io/#yongjhih/gson-adapters)
[![javadoc](https://img.shields.io/github/tag/yongjhih/gson-adapters.svg?label=javadoc)](https://jitpack.io/com/github/yongjhih/gson-adapters/-SNAPSHOT/javadoc/)
[![Build Status](https://travis-ci.org/yongjhih/gson-adapters.svg)](https://travis-ci.org/yongjhih/gson-adapters)
[![Coverage Status](https://coveralls.io/repos/github/yongjhih/gson-adapters/badge.svg?branch=master)](https://coveralls.io/github/yongjhih/gson-adapters?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/64490a4beab54dcaa8f5b23022d607d5)](https://www.codacy.com/app/yongjhih/gson-adapters)
<!--[![javadoc.io](https://javadocio-badges.herokuapp.com/com.infstory/rxparse/badge.svg)](http://www.javadoc.io/doc/com.infstory/rxparse/)-->
<!--[![Coveralls](https://img.shields.io/coveralls/yongjhih/RxParse.svg)](https://coveralls.io/github/yongjhih/RxParse)-->

# Gson Adapters

Collect useful gson adapters

## Usage

Avoid `JsonSyntaxException` while parsing `""` empty string for `List<T>` field, such like the following:

```java
public class Post {
  List<Comment> comments;
}
```

```
{
  msg: "Hello, world!",
  comments: ""
}
```

```java
Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(List.class, new EmptyListDeserializer()).create();
Post post = gson.fromJson(json, Post.class);
```

## Installation

via jitpack.io

```gradle
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.yongjhih:gson-adapters:-SNAPSHOT'
}
```

## LICENSE

```
Copyright 2016 8tory, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
