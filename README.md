## web服务
简单springboot服务，慢慢补充相关内容
- 自定义配置文件加载环境变量
- 使用security给http请求增加权限认证

### 关于服务启动说明
1、自定义配置文件加载环境变量
```yaml
datasource:
  username: root
  password: root
  host: 127.0.0.1:3306
redis:
  domain: 127.0.0.1
  password: 6379
http:
  security:
    user: root
    password: root
    matches: /api/*,/apis/*
```

2、启动配置
```shell
-Dcustom.env.path=/data/config.yml
```
