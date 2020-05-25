package alipay.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016102200741003";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQZD1ExwA9EUgxs3xY/5zPvNA/BqSuNnbDVEOkmKtSa5wVES/jNYcT5rQPY4p0iBfpLVvfFVAOwzVX5lqTq4kkkbPm2p6JXZTOx/BOG2g42LGIAcYbfKNbmbxWnWvhzi2vXQI9NUZLH4enIgYpWz3qAdBWV3155wKluBx6ArbToFuXluhbKg/ruUYlTiHFpzOL1cQ5B5lDIDt/C3XhMYwjHJ93p41E+pCInFLlty8a/j3rvIs7sjh7RxSL9LsU6for6BVSloEQ4xihvaMJtdEe5R1FF+IgTcRLwqSAa2I+GUDh7GmKmvq+oRQFEMd6335tmBNuOvxZuHU0Tk8NPPtlAgMBAAECggEALHH+fgY3Z5TDQTE7bHW6AiPfghb8Z/ex1OxCKg8oZSVklQOGVwzp+FFCSZ5dRwOBQLBKoD+CNhkWeWkZpmzduRj0ZHIU7LmWjZPzv0cmbKEe/Ht3TIywCAkpdQvd4lLBs0IQ52BXs9YPvn11ohNI2pgWVg12PSjDotkqQtBFEJzJZl5cezIAxsp9aBstw58GQgESxuSCoxv440J/VsSmTfqi75xXbBUW4dVUaMEOxdy6m/tNgnJTu6Nz5+IM2koyO9JiTFgLNAFhqEf68KRkc8bhGaBWrCd73l3G0BaMcLartmRHEo5g7VQ9ETF+WNnuYDr6nhtMShQn84/k+auroQKBgQDwMZl7gcOyrWvw9GhSzJLeSfQcXYDMhzIJs1+L20wAWvlo/jjpWR2YJXbvZdfJ03B5FsWDaG32CLEQy1/xXED8Qot74ied4csbTIOV3exH9F/W/rb6jgcExfHZgaiG0xRO9UTRThHpvdK3OHn5K5bfWFNpwubQ2woSo4MiQ7KXrQKBgQCZ5Le0/HGybDzBv5RGgTkPYnP9UF/igK9KAW9mSErGalUF5Jw/o0TAXGRkgD9W+ofJKbYgYlAfZcq5FCWcuusZ7m4Sgec8ENHxnIZrHyGA1tMHc61Vg2bD6lojFGs2MBwf6bxtF3/l322/KxlK7GKbrheWyPynLoYoF7SXm0BJmQKBgHItEaltEMqxtzrN431gc3wpKVoGdIBEz9teoc+IigMtO5vWJNV/oBq/zF6WSdJNqQxWb8M8c/DdRtYV5uCDmvyO4WEdKvnY6ERsxawK1xOJLbm0UOSfeOSi94kOIj8cvW2fF3xrIQa8Thr8RLbWKpy2jsgadr/XL12WznIOPintAoGAbN1QW5bNc3OAE2tMIAgf7lMuTD9EZT21Qrv7KzbutqfX16BeK+J6qz1ww2u54vS9g7+fGA+UMHHY0859wP61e5WghjUg9+vKKIoayMKv962AgWACeWDIHMW21+QE58Nm0eiOI/ogvuOshjSd4tOP+iNQiplJqZaOzuPeG/V2p9kCgYEAktccVT3AmkPL6SEm4ztgxjHv2Ah2sKMVy7lQEaeVoTgpcrKBBbqVWEp6j+KKQAJE2i4ztsMk0mOkqEL6Sud/f16MgKvOkqVpULZr0J+mS6omUzl1FcPUqAoxu9iNn9I0wMyVqU0iEvHTk1k4bW2jcFeH7OlAqf/ZepvMHgSM+Ec=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/pay_api/notify_url";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:8080/pay_api/return_url";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlrtXeGk398m69Is0Cs0aTs+WYm4NlZtsN2ZX+z9O41M4dqGawugMtmigb7nK0DZd2LXe02yVi+UteJLOUAI+FIwSY0L7SVCMUNFDa74CNbbjXnYWABLNhT+OvepoQ9gboBZrL0FUUO9k+LHP28c+tf0mMEZr8R7lKBxVwng1SU7re3MtMZVXtxiVEux9d8VVQVz/UVtH0EJ5ICvQ6o2bD8iG7St4yKcmcMjPedwyxuYfJPMvwJbJM4Et4JhJ3AiGesAAdc0I6ALyGr/DM6edjUANLQozKvXYNDYmfuu/jWDGP7X9ggceqHOGJdlY9ZhLXzoYDUJI/bpiQso7i2z+vQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
